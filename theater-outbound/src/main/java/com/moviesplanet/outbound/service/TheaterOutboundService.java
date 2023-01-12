package com.moviesplanet.outbound.service;

import com.moviesplanet.common.exception.CustomException;
import com.moviesplanet.outbound.controller.TheaterOutboundControllerAdvice;
import com.moviesplanet.theater.model.TheaterEntity;
import com.moviesplanet.theater.repo.TheaterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterOutboundService {

    Logger logger = LoggerFactory.getLogger(TheaterOutboundService.class);

    @Autowired
    private TheaterRepository theaterRepository;

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


}
