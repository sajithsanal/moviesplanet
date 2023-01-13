package com.moviesplanet.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommonUtil {

    static Logger logger = LoggerFactory.getLogger(CommonUtil.class);


    public static ResponseEntity<Object> createResponseObject(String status, String message) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status);
        body.put("message", message);

        logger.info(status + " --> " + message);


        return new ResponseEntity<>(body, HttpStatus.OK);


    }


}
