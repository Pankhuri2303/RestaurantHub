package com.interview.restaurant.exception;

import com.interview.restaurant.response.RestaurantErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.Instant;
import java.util.Objects;


@RestControllerAdvice
public class RestaurantDealsExceptionHandler {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<RestaurantErrorResponse> handleMethodValidationException(HandlerMethodValidationException ex) {
        String invalidValue = Objects.requireNonNull(
                 ex.getParameterValidationResults()
                .get(0)
                .getArgument())
                .toString();

        RestaurantErrorResponse response = new RestaurantErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                invalidValue,
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestaurantErrorResponse> handleMissingParamException(MissingServletRequestParameterException ex) {
        String missingValue = ex.getParameterName();

        RestaurantErrorResponse response = new RestaurantErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed due to missing parameter",
                missingValue,
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
