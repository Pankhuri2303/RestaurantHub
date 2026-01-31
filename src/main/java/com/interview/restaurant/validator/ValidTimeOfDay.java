package com.interview.restaurant.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeOfDayValidator.class) // Link to the validator class
@Target({ElementType.PARAMETER, ElementType.FIELD}) // Can be used on parameters and fields
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeOfDay {
    String message() default "Invalid value provided"; // Default error message
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] values();
}
