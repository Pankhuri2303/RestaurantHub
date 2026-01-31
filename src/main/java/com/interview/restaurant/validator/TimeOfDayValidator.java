package com.interview.restaurant.validator;

import com.interview.restaurant.util.DateTimeParserUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Slf4j
public class TimeOfDayValidator implements ConstraintValidator<ValidTimeOfDay,String> {

    @Override
    public boolean isValid(String val, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validation started for timeofday {}",val);

        try {
            // Define the desired format with strict resolution
            DateTimeFormatter timeFormatter = DateTimeParserUtil.customDateTimeFormatter;
            // Attempt to parse the string
            LocalTime parsedTime = LocalTime.parse(val.toUpperCase().trim(), timeFormatter);

            log.info("Provided timeOfDay is valid: {} (Parsed as: {})", val, parsedTime);
            return true;

        } catch (DateTimeParseException | NullPointerException e) {
            // Catch specific exceptions indicating invalid format or value
            log.info("Provided timeOfDay is INVALID {}", val);

            return false;
        }
    }
}
