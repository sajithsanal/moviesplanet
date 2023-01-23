package com.moviesplanet.theater.inbound.controller;

import com.moviesplanet.theater.inbound.service.TheaterInboundService;
import com.moviesplanet.common.model.TheaterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theater-inbound")
public class TheaterInboundController {


    @Autowired
    private TheaterInboundService theaterInboundService;

    @PostMapping(value = "/theater", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTheaterDetails(@RequestBody TheaterEntity theaterEntity){

        return theaterInboundService.createTheater(theaterEntity);

    }


    @PutMapping(value = "/theater", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTheaterDetails(@RequestBody TheaterEntity theaterEntity){

        return theaterInboundService.updateTheaterDetails(theaterEntity);

    }

    @DeleteMapping(value = "/theater", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTheaterDetails(@RequestParam("theaterName") String theaterName){

        return theaterInboundService.deleteTheaterDetails(theaterName);

    }



}
