package com.moviesplanet.theater.inbound.service;

import com.moviesplanet.common.exception.CustomException;
import com.moviesplanet.common.util.CommonUtil;
import com.moviesplanet.theater.inbound.controller.TheaterInboundControllerAdvice;
import com.moviesplanet.theater.model.TheaterEntity;
import com.moviesplanet.theater.repo.TheaterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TheaterInboundService {

    Logger logger = LoggerFactory.getLogger(TheaterInboundService.class);

    @Autowired
    private TheaterRepository theaterRepository;


    public ResponseEntity<Object> createTheater(TheaterEntity theaterEntity) {

        Optional<TheaterEntity> existingTheater = theaterRepository.findByTheaterName(theaterEntity.getTheaterName());

        if (existingTheater.isPresent()) {

            throw new CustomException("Theater already exists");
        }

        theaterEntity.setId(String.valueOf(System.currentTimeMillis()));
        TheaterEntity entity = theaterRepository.save(theaterEntity);

        return CommonUtil.createResponseObject("SUCCESS", "Created theater with id " + entity.getId());

    }


    public ResponseEntity<Object> updateTheaterDetails(TheaterEntity theaterEntity) {

        Optional<TheaterEntity> existingTheater = theaterRepository.findByTheaterName(theaterEntity.getTheaterName());

        if (existingTheater.isPresent()) {
            TheaterEntity existingEntity = existingTheater.get();
            logger.info("Existing theater found and id is " + existingEntity.getId());
            theaterEntity.setId(existingEntity.getId());
            theaterRepository.save(theaterEntity);

        } else {

            throw new CustomException("Unable to find theater with theater Name " + theaterEntity.getTheaterName() + " for update operation");

        }

        return CommonUtil.createResponseObject("SUCCESS", "Updated theater details with id " + theaterEntity.getId());

    }

    public ResponseEntity<Object> deleteTheaterDetails(String theaterName) {

        Optional<TheaterEntity> existingTheater = theaterRepository.findByTheaterName(theaterName);

        if (existingTheater.isPresent()) {
            TheaterEntity existingEntity = existingTheater.get();
            logger.info("Existing theater found and id is " + existingEntity.getId());
            theaterRepository.delete(existingEntity);

        } else {

            throw new CustomException("Unable to find theater with theater Name " + theaterName+ " for delete operation");

        }

        return CommonUtil.createResponseObject("SUCCESS", "Deleted the details for theater " + theaterName);

    }


}
