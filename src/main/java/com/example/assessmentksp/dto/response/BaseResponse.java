package com.example.assessmentksp.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseResponse {
    public static ResponseEntity<Object> successResponse(String message, HttpStatus status, Object responseData) {
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        mapReturn.put("message", message);
        mapReturn.put("status", status.value());
        mapReturn.put("data", responseData);

        return new ResponseEntity<>(mapReturn, status);
    }
}
