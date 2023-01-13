package com.moviesplanet.common.dto;

public class MovieValidationRequest {

    private String movieName;
    private String theaterName;
    private String city;
    private String dateOfShow;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateOfShow() {
        return dateOfShow;
    }

    public void setDateOfShow(String dateOfShow) {
        this.dateOfShow = dateOfShow;
    }
}
