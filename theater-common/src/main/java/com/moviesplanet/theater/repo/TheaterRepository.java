package com.moviesplanet.theater.repo;

import com.moviesplanet.theater.model.MovieDetails;
import com.moviesplanet.theater.model.TheaterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends MongoRepository<TheaterEntity, String>{

    Optional<TheaterEntity> findByTheaterName(String theaterName);

    @Query("{ 'movieShows.movieName' : ?0 }")
    List<TheaterEntity> findByMovieName(String name);

    List<TheaterEntity> findByMovieShows(MovieDetails movieDetails);
}
