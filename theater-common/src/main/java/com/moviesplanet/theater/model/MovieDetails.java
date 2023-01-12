package com.moviesplanet.theater.model;

import java.util.List;

public class MovieDetails {
    private String movieName;
    private String showStartDate;
    private String showEndDate;
    private String language;
    private String genre;
    private String subtitle;
    private String movieDuration;
    private TicketRate ticketRate;
    private List<MovieShowDetails> showDetails;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getShowStartDate() {
        return showStartDate;
    }

    public void setShowStartDate(String showStartDate) {
        this.showStartDate = showStartDate;
    }

    public String getShowEndDate() {
        return showEndDate;
    }

    public void setShowEndDate(String showEndDate) {
        this.showEndDate = showEndDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public TicketRate getTicketRate() {
        return ticketRate;
    }

    public void setTicketRate(TicketRate ticketRate) {
        this.ticketRate = ticketRate;
    }

    public List<MovieShowDetails> getShowDetails() {
        return showDetails;
    }

    public void setShowDetails(List<MovieShowDetails> showDetails) {
        this.showDetails = showDetails;
    }
}
