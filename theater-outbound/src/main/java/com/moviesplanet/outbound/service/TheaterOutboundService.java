package com.moviesplanet.outbound.service;

import com.moviesplanet.common.exception.CustomException;
import com.moviesplanet.common.util.CommonUtil;
import com.moviesplanet.outbound.controller.TheaterOutboundControllerAdvice;
import com.moviesplanet.theater.dto.MovieSearchRequest;
import com.moviesplanet.theater.dto.MovieSearchResponse;
import com.moviesplanet.theater.dto.MovieValidationRequest;
import com.moviesplanet.theater.model.MovieDetails;
import com.moviesplanet.theater.model.TheaterEntity;
import com.moviesplanet.theater.repo.TheaterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterOutboundService {

    Logger logger = LoggerFactory.getLogger(TheaterOutboundService.class);

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TheaterEntity> findAll() {

        return theaterRepository.findAll();

    }

    public TheaterEntity findTheater(String theaterName) {

        Optional<TheaterEntity> existingTheater = theaterRepository.findByTheaterName(theaterName);

        if (existingTheater.isPresent()) {
            TheaterEntity existingEntity = existingTheater.get();
            logger.info("Existing theater found and id is " + existingEntity.getId());
            return existingEntity;
        } else {

            throw new CustomException("Unable to find theater with theater Name " + theaterName);

        }

    }

    public List<MovieSearchResponse> searchMovies(MovieSearchRequest request) {

//        List<TheaterEntity> theaterEntityList = theaterRepository.findByMovieName(request.getMovieName());
        Query andQuery = new Query();
        Criteria andCriteria = new Criteria();
        List<Criteria> andExpression = new ArrayList<>();


        if (StringUtils.hasLength(request.getMovieName())) {
            Criteria expression = new Criteria();
            expression.and("movieShows.movieName").is(request.getMovieName());
            andExpression.add(expression);

        }
        if (StringUtils.hasLength(request.getLanguage())) {
            Criteria expression = new Criteria();
            expression.and("movieShows.language").is(request.getLanguage());
            andExpression.add(expression);
        }
        if (StringUtils.hasLength(request.getGenre())) {
            Criteria expression = new Criteria();
            expression.and("movieShows.genre").is(request.getGenre());
            andExpression.add(expression);
        }

        if (StringUtils.hasLength(request.getCity())) {
            Criteria expression = new Criteria();
            expression.and("address.city").is(request.getCity());
            andExpression.add(expression);
        }

        andQuery.addCriteria(andCriteria.andOperator(andExpression.toArray(new Criteria[andExpression.size()])));


        List<TheaterEntity> theaterEntityList = mongoTemplate.find(andQuery, TheaterEntity.class);

        List<MovieSearchResponse> responses = new ArrayList<>();

        if (!CollectionUtils.isEmpty(theaterEntityList)) {
            logger.info("Found total list of theaters  --> " + theaterEntityList.size());

            theaterEntityList.forEach(entity -> {
                entity.getMovieShows().forEach(movie -> {
                    if ((StringUtils.hasLength(request.getMovieName()) && request.getMovieName().equals(movie.getMovieName())) &&
                            (StringUtils.hasLength(request.getLanguage()) && request.getLanguage().equals(movie.getLanguage())) &&
                            (StringUtils.hasLength(request.getGenre()) && request.getGenre().equals(movie.getGenre())) &&
                            (StringUtils.hasLength(request.getCity()) && request.getCity().equals(entity.getAddress().getCity()))) {

                        MovieSearchResponse response = new MovieSearchResponse();
                        response.setMovieName(movie.getMovieName());
                        response.setLanguage(movie.getLanguage());
                        response.setMovieDuration(movie.getMovieDuration());
                        response.setSubTitle(movie.getSubtitle());
                        response.setTheaterName(entity.getTheaterName());
                        response.setTheaterAddress(entity.getAddress().toString());
                        responses.add(response);
                    } else if ((StringUtils.hasLength(request.getMovieName()) && request.getMovieName().equals(movie.getMovieName()))) {
                        MovieSearchResponse response = new MovieSearchResponse();
                        response.setMovieName(movie.getMovieName());
                        response.setLanguage(movie.getLanguage());
                        response.setMovieDuration(movie.getMovieDuration());
                        response.setSubTitle(movie.getSubtitle());
                        response.setTheaterName(entity.getTheaterName());
                        response.setTheaterAddress(entity.getAddress().toString());
                        responses.add(response);

                    }

                });
            });


        } else {
            logger.info("No Theaters found with the search criteria");
        }

        return responses;


    }

    public TheaterEntity validateMovieShow(MovieValidationRequest request) {

        Query andQuery = new Query();
        Criteria andCriteria = new Criteria();
        List<Criteria> andExpression = new ArrayList<>();


        if (StringUtils.hasLength(request.getMovieName())) {
            Criteria expression = new Criteria();
            expression.and("movieShows.movieName").is(request.getMovieName());
            andExpression.add(expression);

        }
        if (StringUtils.hasLength(request.getTheaterName())) {
            Criteria expression = new Criteria();
            expression.and("theaterName").is(request.getTheaterName());
            andExpression.add(expression);
        }
        if (StringUtils.hasLength(request.getDateOfShow())) {
            Criteria expression = new Criteria();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date showDate = df.parse(request.getDateOfShow());
                expression.and("movieShows.showStartDate").lte(showDate).and("movieShows.showEndDate").gte(showDate);
                andExpression.add(expression);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

        if (StringUtils.hasLength(request.getCity())) {
            Criteria expression = new Criteria();
            expression.and("address.city").is(request.getCity());
            andExpression.add(expression);
        }

        andQuery.addCriteria(andCriteria.andOperator(andExpression.toArray(new Criteria[andExpression.size()])));


        List<TheaterEntity> theaterEntityList = mongoTemplate.find(andQuery, TheaterEntity.class);


        if (!CollectionUtils.isEmpty(theaterEntityList)) {
            logger.info("Match found  --> " + theaterEntityList.size());
            return theaterEntityList.get(0);

        } else {
            logger.info("No Theaters found with the search criteria");
            throw new CustomException("Unable to find any theater");
        }


    }

}
