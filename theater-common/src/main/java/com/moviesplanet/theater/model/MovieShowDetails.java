package com.moviesplanet.theater.model;

public class MovieShowDetails {
    private String showTime;
    private int discountOnWeekDays;
    private int discountOnWeekends;
    private String screenName;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getDiscountOnWeekDays() {
        return discountOnWeekDays;
    }

    public void setDiscountOnWeekDays(int discountOnWeekDays) {
        this.discountOnWeekDays = discountOnWeekDays;
    }

    public int getDiscountOnWeekends() {
        return discountOnWeekends;
    }

    public void setDiscountOnWeekends(int discountOnWeekends) {
        this.discountOnWeekends = discountOnWeekends;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
