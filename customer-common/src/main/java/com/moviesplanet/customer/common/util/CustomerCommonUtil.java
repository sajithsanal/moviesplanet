package com.moviesplanet.customer.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesplanet.common.dto.MovieValidationRequest;
import com.moviesplanet.common.exception.CustomException;
import com.moviesplanet.common.model.TheaterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

public class CustomerCommonUtil {

    static Logger logger = LoggerFactory.getLogger(CustomerCommonUtil.class);


    public static TheaterEntity callTheaterValidateService(MovieValidationRequest request) throws JsonProcessingException {

        TheaterEntity theaterEntity;
        RestTemplate restTemplate = new RestTemplate();
        String theaterOutboundServiceUrl = System.getenv().get("THEATER_OUTBOUND_SERVICE_URL");
        String finalUrl = theaterOutboundServiceUrl + "/api/movie/validate";
        logger.info("final URL is " + finalUrl);
        ResponseEntity<String> response = restTemplate.postForEntity(finalUrl, request, String.class);
        String responseBody = response.getBody();
        logger.info("Response  theater service --> " + responseBody);
        if (StringUtils.hasLength(responseBody) && responseBody.contains("ERROR")) {
            throw new CustomException("Movie booking details are not correct");
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            theaterEntity = objectMapper.readValue(responseBody, TheaterEntity.class);
        }

        return theaterEntity;


    }
    public static TheaterEntity callTheaterService(String theaterName) throws JsonProcessingException {

        TheaterEntity theaterEntity;
        RestTemplate restTemplate = new RestTemplate();
        String theaterOutboundServiceUrl = System.getenv().get("THEATER_OUTBOUND_SERVICE_URL");
        String finalUrl = theaterOutboundServiceUrl + "/api/theater?theaterName={theaterName}";
        logger.info("final URL is " + finalUrl);
        ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class, theaterName);
        String responseBody = response.getBody();
        logger.info("Response  theater service --> " + responseBody);
        if (StringUtils.hasLength(responseBody) && responseBody.contains("ERROR")) {
            throw new CustomException("Unable to find the theater");
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            theaterEntity = objectMapper.readValue(responseBody, TheaterEntity.class);
        }

        return theaterEntity;


    }
}
