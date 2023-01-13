package com.moviesplanet.common.model;

import java.util.List;

public class ScreenDetails {

    private String screenName;
    private List<SeatingDetails> seatingDetails;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<SeatingDetails> getSeatingDetails() {
        return seatingDetails;
    }

    public void setSeatingDetails(List<SeatingDetails> seatingDetails) {
        this.seatingDetails = seatingDetails;
    }
}
