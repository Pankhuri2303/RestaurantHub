package com.interview.restaurant.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class RestaurantErrorResponse {
    private int status;
    private String message;
    private String invalidValue;
    private String timestamp;

    public RestaurantErrorResponse(int status, String message, String invalidValue, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.invalidValue = invalidValue;
        this.timestamp = timestamp.toString();
    }
}
