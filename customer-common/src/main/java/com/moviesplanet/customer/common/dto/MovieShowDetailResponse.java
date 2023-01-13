package com.moviesplanet.customer.common.dto;

import com.moviesplanet.common.dto.MovieValidationRequest;

import java.util.List;

public class MovieShowDetailResponse extends MovieValidationRequest {

    private List<ShowDetails> showDetails;

    public List<ShowDetails> getShowDetails() {
        return showDetails;
    }

    public void setShowDetails(List<ShowDetails> showDetails) {
        this.showDetails = showDetails;
    }
}
