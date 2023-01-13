package com.moviesplanet.customer.common.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "bookingDetails")
public class CustomerBookingDetails {

    @MongoId(FieldType.TIMESTAMP)
    private String id;

    private String theaterName;

    private String movieName;

    private Date movieShowDate;

    private String timeOfTheShow;

    private Timestamp bookingDate;

    private String seatsSelected;

    private Boolean paymentCompleted;

    private String customerMobile;

    private String customerEmail;

    private Timestamp insertDate;

    private Timestamp updateDate;


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

    public Date getMovieShowDate() {
        return movieShowDate;
    }

    public void setMovieShowDate(Date movieShowDate) {
        this.movieShowDate = movieShowDate;
    }

    public String getTimeOfTheShow() {
        return timeOfTheShow;
    }

    public void setTimeOfTheShow(String timeOfTheShow) {
        this.timeOfTheShow = timeOfTheShow;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
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

    public Timestamp getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
