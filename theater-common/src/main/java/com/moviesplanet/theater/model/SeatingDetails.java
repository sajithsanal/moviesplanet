package com.moviesplanet.theater.model;

public class SeatingDetails {
    private String rowName;
    private int noOfSeatsInTheRow;
    private int positionFromTheScreen;
    private String seatType;

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public int getNoOfSeatsInTheRow() {
        return noOfSeatsInTheRow;
    }

    public void setNoOfSeatsInTheRow(int noOfSeatsInTheRow) {
        this.noOfSeatsInTheRow = noOfSeatsInTheRow;
    }

    public int getPositionFromTheScreen() {
        return positionFromTheScreen;
    }

    public void setPositionFromTheScreen(int positionFromTheScreen) {
        this.positionFromTheScreen = positionFromTheScreen;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}
