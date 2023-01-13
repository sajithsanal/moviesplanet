package com.moviesplanet.outbound.controller;

import com.moviesplanet.outbound.service.TheaterOutboundService;
import com.moviesplanet.theater.dto.MovieSearchRequest;
import com.moviesplanet.theater.dto.MovieSearchResponse;
import com.moviesplanet.theater.dto.MovieValidationRequest;
import com.moviesplanet.theater.model.TheaterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterOutboundController {

    @Autowired
    private TheaterOutboundService theaterOutboundService;

    @GetMapping(value = "/api/theaters", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TheaterEntity> findAll(){

        return theaterOutboundService.findAll();

    }

    @GetMapping(value = "/api/theater", produces = MediaType.APPLICATION_JSON_VALUE)
    public TheaterEntity findTheaterDetails(@RequestParam("theaterName") String theaterName){

        return theaterOutboundService.findTheater(theaterName);

    }

    @PostMapping(value = "/api/movies", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<MovieSearchResponse> searchMovies(@RequestBody MovieSearchRequest request){

        return theaterOutboundService.searchMovies(request);

    }

    @PostMapping(value = "/api/movie/validate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TheaterEntity validateMovies(@RequestBody MovieValidationRequest request){

        return theaterOutboundService.validateMovieShow(request);

    }
}
