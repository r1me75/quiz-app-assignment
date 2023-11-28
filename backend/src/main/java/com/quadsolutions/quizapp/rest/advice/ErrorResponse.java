package com.quadsolutions.quizapp.rest.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private List<String> errors;

    public ErrorResponse(int statusCode, Date timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
    }
}
