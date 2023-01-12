package com.moviesplanet.theater.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.annotation.processing.Generated;
import java.util.List;

@Document(collection = "theaters")
public class TheaterEntity {

    @MongoId(FieldType.TIMESTAMP)
    private String id;

    private String theaterName;
    private TheaterAddress address;
    private List<MovieDetails> movieShows;
    private List<ScreenDetails> screenDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public TheaterAddress getAddress() {
        return address;
    }

    public void setAddress(TheaterAddress address) {
        this.address = address;
    }

    public List<MovieDetails> getMovieShows() {
        return movieShows;
    }

    public void setMovieShows(List<MovieDetails> movieShows) {
        this.movieShows = movieShows;
    }

    public List<ScreenDetails> getScreenDetails() {
        return screenDetails;
    }

    public void setScreenDetails(List<ScreenDetails> screenDetails) {
        this.screenDetails = screenDetails;
    }
}
