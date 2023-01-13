package com.moviesplanet.common.model;

import java.util.List;

public class TicketRate {

    private List<SeatTypesAndRates> weekdays;
    private List<SeatTypesAndRates> weekends;

    public List<SeatTypesAndRates> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<SeatTypesAndRates> weekdays) {
        this.weekdays = weekdays;
    }

    public List<SeatTypesAndRates> getWeekends() {
        return weekends;
    }

    public void setWeekends(List<SeatTypesAndRates> weekends) {
        this.weekends = weekends;
    }
}
