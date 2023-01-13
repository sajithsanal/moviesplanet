package com.moviesplanet.customer.common.dto;

import com.moviesplanet.common.model.SeatingDetails;

import java.util.List;
import java.util.Map;

public class SeatAvailabilityResponse {

    private List<SeatingDetails> seatingDetails;
    private List<String> bookedSeats;
    private Map<String, String> ticketRate;

    public List<SeatingDetails> getSeatingDetails() {
        return seatingDetails;
    }

    public void setSeatingDetails(List<SeatingDetails> seatingDetails) {
        this.seatingDetails = seatingDetails;
    }

    public List<String> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<String> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Map<String, String> getTicketRate() {
        return ticketRate;
    }

    public void setTicketRate(Map<String, String> ticketRate) {
        this.ticketRate = ticketRate;
    }
}
