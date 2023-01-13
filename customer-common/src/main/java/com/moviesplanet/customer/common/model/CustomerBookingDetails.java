package com.moviesplanet.customer.common.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "bookingDetails")
public class CustomerBookingDetails {

    @MongoId(FieldType.TIMESTAMP)
    private String id;

    private String theaterName;

    private String movieName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date movieShowDate;

    private String timeOfTheShow;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;

    private String screenName;

    private String seatsSelected;

    private int noOfSeatsBooked;

    private Boolean paymentCompleted;

    private String customerMobile;

    private String customerEmail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date insertDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNoOfSeatsBooked() {
        return noOfSeatsBooked;
    }

    public void setNoOfSeatsBooked(int noOfSeatsBooked) {
        this.noOfSeatsBooked = noOfSeatsBooked;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }




    public String getTimeOfTheShow() {
        return timeOfTheShow;
    }

    public void setTimeOfTheShow(String timeOfTheShow) {
        this.timeOfTheShow = timeOfTheShow;
    }




    public String getSeatsSelected() {
        return seatsSelected;
    }

    public void setSeatsSelected(String seatsSelected) {
        this.seatsSelected = seatsSelected;
    }

    public Boolean getPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(Boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Date getMovieShowDate() {
        return movieShowDate;
    }

    public void setMovieShowDate(Date movieShowDate) {
        this.movieShowDate = movieShowDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
