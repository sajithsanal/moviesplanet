package com.moviesplanet.theater.repo;

import com.moviesplanet.theater.model.TheaterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TheaterRepository extends MongoRepository<TheaterEntity, String> {

    Optional<TheaterEntity> findByTheaterName(String theaterName);
}
