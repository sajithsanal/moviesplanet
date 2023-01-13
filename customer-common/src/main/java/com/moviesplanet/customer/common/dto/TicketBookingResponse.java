package com.moviesplanet.customer.common.dto;

public class TicketBookingResponse extends TicketBookingReq{
    private String bookingConfirmationId;
    private String screenName;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getBookingConfirmationId() {
        return bookingConfirmationId;
    }

    public void setBookingConfirmationId(String bookingConfirmationId) {
        this.bookingConfirmationId = bookingConfirmationId;
    }
}
