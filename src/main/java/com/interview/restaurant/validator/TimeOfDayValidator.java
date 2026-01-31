package com.interview.restaurant.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

@Slf4j
public class TimeOfDayValidator implements ConstraintValidator<ValidTimeOfDay,String> {

    @Override
    public boolean isValid(String val, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validation started for timeofday" + val);

        return true;
    }
}
