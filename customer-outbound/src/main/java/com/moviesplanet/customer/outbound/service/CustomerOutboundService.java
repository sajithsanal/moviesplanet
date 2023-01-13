package com.moviesplanet.customer.outbound.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moviesplanet.common.dto.MovieValidationRequest;
import com.moviesplanet.common.exception.CustomException;
import com.moviesplanet.common.model.*;
import com.moviesplanet.customer.common.dto.*;
import com.moviesplanet.customer.common.model.CustomerBookingDetails;
import com.moviesplanet.customer.common.repo.CustomerBookingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.awt.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.moviesplanet.customer.common.util.CustomerCommonUtil.callTheaterService;
import static com.moviesplanet.customer.common.util.CustomerCommonUtil.callTheaterValidateService;

@Service
public class CustomerOutboundService {


    private Logger logger = LoggerFactory.getLogger(CustomerOutboundService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CustomerBookingRepo customerBookingRepo;


    public MovieShowDetailResponse getMovieShowDetails(MovieValidationRequest request) throws JsonProcessingException {

        TheaterEntity theaterEntity = callTheaterValidateService(request);
        MovieShowDetailResponse response = new MovieShowDetailResponse();
        if (theaterEntity != null) {
            response.setTheaterName(request.getTheaterName());
            response.setMovieName(request.getMovieName());
            response.setCity(request.getCity());
            response.setDateOfShow(request.getDateOfShow());
            response.setShowDetails(getShowDetails(theaterEntity, request));
        }


        return response;

    }


    public SeatAvailabilityResponse getSeatAvailabilityDetails(SeatAvailabilityRequest request) throws JsonProcessingException {

        TheaterEntity theaterEntity = callTheaterService(request.getTheaterName());
        SeatAvailabilityResponse seatAvailabilityResponse = new SeatAvailabilityResponse();

        if (theaterEntity != null) {

            request.setScreenName(theaterEntity.getMovieShows().stream().filter(movies -> request.getMovieName().equals(movies.getMovieName())).findFirst()
                    .get().getShowDetails().stream().filter(show -> request.getShowTime().equals(show.getShowTime())).findFirst().get().getScreenName());
            Optional<ScreenDetails> optionalScreenDetails = theaterEntity.getScreenDetails().stream().filter(screenDetails -> request.getScreenName().equals(screenDetails.getScreenName())).findFirst();
            seatAvailabilityResponse.setSeatingDetails(optionalScreenDetails.get().getSeatingDetails());
            seatAvailabilityResponse.setBookedSeats(getBookedSeatNumbers(request));
            seatAvailabilityResponse.setTicketRate(calculateTicketRate(theaterEntity, request));
        }

        return seatAvailabilityResponse;

    }

    public TicketBookingResponse bookTicket(TicketBookingReq req) throws JsonProcessingException, ParseException {
        MovieValidationRequest movieValidationRequest = new MovieValidationRequest();
        movieValidationRequest.setMovieName(req.getMovieName());
        movieValidationRequest.setDateOfShow(req.getDate());
        movieValidationRequest.setTheaterName(req.getTheaterName());
        movieValidationRequest.setCity(req.getCity());
        TheaterEntity theaterEntity = callTheaterValidateService(movieValidationRequest);
        CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails();
        customerBookingDetails.setId(String.valueOf(System.nanoTime()));
        customerBookingDetails.setBookingDate(new Timestamp(System.currentTimeMillis()));
        customerBookingDetails.setCustomerEmail(req.getEmail());
        customerBookingDetails.setCustomerMobile(req.getMobile());
        customerBookingDetails.setNoOfSeatsBooked(req.getSeatsSelected().size());
        customerBookingDetails.setMovieName(req.getMovieName());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date showDate = df.parse(req.getDate());
        customerBookingDetails.setMovieShowDate(showDate);
        customerBookingDetails.setSeatsSelected(String.join(",", req.getSeatsSelected()));
        customerBookingDetails.setPaymentCompleted(Boolean.FALSE);
        customerBookingDetails.setScreenName(theaterEntity.getMovieShows().stream().filter(movies -> req.getMovieName().equals(movies.getMovieName())).findFirst()
                .get().getShowDetails().stream().filter(show -> req.getShowTime().equals(show.getShowTime())).findFirst().get().getScreenName());

        customerBookingDetails.setInsertDate(new Timestamp(System.currentTimeMillis()));
        customerBookingDetails.setTheaterName(req.getTheaterName());
        customerBookingDetails.setTimeOfTheShow(req.getShowTime());
        customerBookingDetails.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        customerBookingDetails.setCity(req.getCity());
        validateSelectedSeats(customerBookingDetails, req);
        customerBookingRepo.save(customerBookingDetails);
        TicketBookingResponse ticketBookingResponse = getTicketBookingResponse(customerBookingDetails);

        return ticketBookingResponse;


    }

    public TicketBookingResponse getBookingDetails(String email, String mobile){

        if(StringUtils.hasLength(email)){
            Optional<CustomerBookingDetails>  customerBookingDetailsOptional =  customerBookingRepo.findByCustomerEmail(email);
            if(customerBookingDetailsOptional.isPresent()){
                return getTicketBookingResponse(customerBookingDetailsOptional.get());

            }

        }
        if(StringUtils.hasLength(mobile)){

            Optional<CustomerBookingDetails>  customerBookingDetailsOptional =  customerBookingRepo.findByCustomerMobile(mobile);
            if(customerBookingDetailsOptional.isPresent()){
                return getTicketBookingResponse(customerBookingDetailsOptional.get());

            }
        }

        throw new CustomException("Unable to find the booking details using mobile number or email id provided");



    }


    private TicketBookingResponse getTicketBookingResponse(CustomerBookingDetails customerBookingDetails){

        TicketBookingResponse ticketBookingResponse = new TicketBookingResponse();
        ticketBookingResponse.setBookingConfirmationId(customerBookingDetails.getId());
        ticketBookingResponse.setCity(customerBookingDetails.getCity());
        ticketBookingResponse.setScreenName(customerBookingDetails.getScreenName());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ticketBookingResponse.setDate(df.format(customerBookingDetails.getMovieShowDate()));
        ticketBookingResponse.setEmail(customerBookingDetails.getCustomerEmail());
        ticketBookingResponse.setMobile(customerBookingDetails.getCustomerMobile());
        ticketBookingResponse.setSeatsSelected(Arrays.stream(customerBookingDetails.getSeatsSelected().split(",")).collect(Collectors.toList()));
        ticketBookingResponse.setMovieName(customerBookingDetails.getMovieName());
        ticketBookingResponse.setTheaterName(customerBookingDetails.getTheaterName());
        ticketBookingResponse.setShowTime(customerBookingDetails.getTimeOfTheShow());

        return ticketBookingResponse;
    }


    private List<ShowDetails> getShowDetails(TheaterEntity theaterEntity, MovieValidationRequest request) {

        List<ShowDetails> showDetailsList = new ArrayList<>();

        Optional<MovieDetails> movieDetails = theaterEntity.getMovieShows().stream().filter(movie -> request.getMovieName().equals(movie.getMovieName())).findFirst();

        for (MovieShowDetails movieShowDetails : movieDetails.get().getShowDetails()) {
            ShowDetails showDetails = new ShowDetails();
            showDetails.setShowTime(movieShowDetails.getShowTime());
            showDetails.setScreenName(movieShowDetails.getScreenName());
            String availableSeats = String.valueOf(getTotalNumberOfSeats(theaterEntity, movieShowDetails.getScreenName()) -
                    getTotalNumberOfSeatsBooked(request.getTheaterName(), movieShowDetails.getScreenName(), request.getDateOfShow(), request.getMovieName(), movieShowDetails.getShowTime()));
            showDetails.setSeatsAvailable(availableSeats);
            showDetailsList.add(showDetails);
        }

        return showDetailsList;

    }


    private int getTotalNumberOfSeats(TheaterEntity theaterEntity, String screenName) {
        int totalCount = 0;
        Optional<ScreenDetails> optionalScreenDetails = theaterEntity.getScreenDetails().stream().
                filter(screenDetails -> screenName.equals(screenDetails.getScreenName())).findFirst();
        if (optionalScreenDetails.isPresent()) {
            totalCount = optionalScreenDetails.get().getSeatingDetails().stream().mapToInt(SeatingDetails::getNoOfSeatsInTheRow).sum();

        }

        return totalCount;


    }

    private int getTotalNumberOfSeatsBooked(String theaterName, String screenName, String dateOfShow, String movieName, String movieTime) {

        List<CustomerBookingDetails> customerBookingDetails = getCustomerBookingDetails(theaterName, screenName, dateOfShow, movieName, movieTime);

        return customerBookingDetails.stream().mapToInt(CustomerBookingDetails::getNoOfSeatsBooked).sum();

    }

    private List<CustomerBookingDetails> getCustomerBookingDetails(String theaterName, String screenName, String dateOfShow, String movieName, String movieTime) {
        Query andQuery = new Query();
        Criteria andCriteria = new Criteria();
        List<Criteria> andExpression = new ArrayList<>();


        if (StringUtils.hasLength(movieName)) {
            Criteria expression = new Criteria();
            expression.and("movieName").is(movieName);
            andExpression.add(expression);

        }
        if (StringUtils.hasLength(theaterName)) {
            Criteria expression = new Criteria();
            expression.and("theaterName").is(theaterName);
            andExpression.add(expression);
        }
        if (StringUtils.hasLength(screenName)) {
            Criteria expression = new Criteria();
            expression.and("screenName").is(screenName);
            andExpression.add(expression);
        }
        if (StringUtils.hasLength(dateOfShow)) {
            Criteria expression = new Criteria();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date showDate = df.parse(dateOfShow);
                expression.and("movieShowDate").is(showDate);
                andExpression.add(expression);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

        if (StringUtils.hasLength(movieTime)) {
            Criteria expression = new Criteria();
            expression.and("timeOfTheShow").is(movieTime);
            andExpression.add(expression);
        }

        andQuery.addCriteria(andCriteria.andOperator(andExpression.toArray(new Criteria[andExpression.size()])));


        List<CustomerBookingDetails> customerBookingDetails = mongoTemplate.find(andQuery, CustomerBookingDetails.class);

        return customerBookingDetails;
    }


    private List<String> getBookedSeatNumbers(SeatAvailabilityRequest request) {

        List<CustomerBookingDetails> customerBookingDetails = getCustomerBookingDetails(request.getTheaterName(), request.getScreenName(),
                request.getDate(), request.getMovieName(), request.getShowTime());

        return customerBookingDetails.stream().map(bookingDetails -> bookingDetails.getSeatsSelected()).collect(Collectors.toList());

    }


    private Map<String, String> calculateTicketRate(TheaterEntity entity, SeatAvailabilityRequest request) {

        Map<String, String> ticketRates = new HashMap<>();
        Optional<MovieDetails> movieDetailsOptional = entity.getMovieShows().stream().filter(movies -> request.getMovieName().equals(movies.getMovieName())).findFirst();
        movieDetailsOptional.get().getTicketRate();

        LocalDate localDate = LocalDate.parse(request.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        if (isWeekend(localDate)) {
            List<SeatTypesAndRates> weekends = movieDetailsOptional.get().getTicketRate().getWeekends();
            Optional<MovieShowDetails> showDetailsOptional = movieDetailsOptional.get().getShowDetails().stream().filter(showDetail -> showDetail.getShowTime().equals(request.getShowTime())).findFirst();
            int weekendDiscount = showDetailsOptional.get().getDiscountOnWeekends();
            for (SeatTypesAndRates seatTypesAndRates : weekends) {
                Integer originalPrice = Integer.valueOf(seatTypesAndRates.getPrice().replace("INR", ""));
                int finalPrice = originalPrice;
                if (weekendDiscount != 0)
                    finalPrice = originalPrice - (originalPrice * weekendDiscount) / 100;
                ticketRates.put(seatTypesAndRates.getSeatType(), String.valueOf(finalPrice));
            }
        } else {

            List<SeatTypesAndRates> weekdays = movieDetailsOptional.get().getTicketRate().getWeekdays();
            Optional<MovieShowDetails> showDetailsOptional = movieDetailsOptional.get().getShowDetails().stream().filter(showDetail -> showDetail.getShowTime().equals(request.getShowTime())).findFirst();
            int weekDayDiscount = showDetailsOptional.get().getDiscountOnWeekDays();
            for (SeatTypesAndRates seatTypesAndRates : weekdays) {
                Integer originalPrice = Integer.valueOf(seatTypesAndRates.getPrice().replace("INR", ""));
                int finalPrice = originalPrice;
                if (weekDayDiscount != 0)
                    finalPrice = originalPrice - (originalPrice * weekDayDiscount) / 100;
                ticketRates.put(seatTypesAndRates.getSeatType(), String.valueOf(finalPrice));
            }


        }

        return ticketRates;


    }

    private boolean isWeekend(LocalDate localDate) {
        String dayOfWeek = localDate.getDayOfWeek().toString();
        if ("SATURDAY".equalsIgnoreCase(dayOfWeek) ||
                "SUNDAY".equalsIgnoreCase(dayOfWeek)) {
            return true;
        }
        return false;


    }

    private void validateSelectedSeats(CustomerBookingDetails newBooking, TicketBookingReq req){
        List<CustomerBookingDetails> customerBookingDetails = getCustomerBookingDetails(newBooking.getTheaterName(), newBooking.getScreenName(),
                req.getDate(), newBooking.getMovieName(), newBooking.getTimeOfTheShow());

        String seatsToBook = newBooking.getSeatsSelected();
        Optional<CustomerBookingDetails> customerBookingDetailsOptional = customerBookingDetails.stream().filter(old -> seatsToBook.equals(old.getSeatsSelected())).findFirst();
        if(customerBookingDetailsOptional.isPresent()){
            throw new CustomException(seatsToBook + " already booked. Please select different seats");

        }

    }


}
