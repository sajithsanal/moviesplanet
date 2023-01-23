package com.moviesplanet.customer.outbound.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moviesplanet.common.dto.MovieValidationRequest;
import com.moviesplanet.customer.common.dto.*;
import com.moviesplanet.customer.outbound.service.CustomerOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/customer")
public class CustomerOutboundController {

    @Autowired
    private CustomerOutboundService customerOutboundService;


    @PostMapping(value = "/movie-booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody MovieShowDetailResponse getMovieBookingDetails(@RequestBody MovieValidationRequest request) throws JsonProcessingException {

        return customerOutboundService.getMovieShowDetails(request);
    }

    @PostMapping(value = "/seat-availability", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody SeatAvailabilityResponse getSeatDetails(@RequestBody SeatAvailabilityRequest request) throws JsonProcessingException {

        return customerOutboundService.getSeatAvailabilityDetails(request);
    }

    @PostMapping(value = "/book-ticket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TicketBookingResponse bookTicket(@RequestBody TicketBookingReq request) throws JsonProcessingException, ParseException {

        return customerOutboundService.bookTicket(request);
    }

    @GetMapping(value = "/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketBookingResponse getBookingDetails(@RequestParam(required = false) String mobile, @RequestParam(required = false) String email){

        return customerOutboundService.getBookingDetails(email,mobile);

    }



}
