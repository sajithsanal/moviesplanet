package com.moviesplanet.customer.common.dto;

public class ShowDetails {

    private String showTime;
    private String seatsAvailable;
    private String screenName;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(String seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
