package com.moviesplanet.customer.common.dto;

import java.util.List;

public class TicketBookingReq {

    private String movieName;
    private String theaterName;
    private String date;
    private String showTime;
    private List<String> seatsSelected;
    private String email;
    private String mobile;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public List<String> getSeatsSelected() {
        return seatsSelected;
    }

    public void setSeatsSelected(List<String> seatsSelected) {
        this.seatsSelected = seatsSelected;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
