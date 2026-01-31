package com.interview.restaurant.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeOfDayValidatorTest {

    @Test
    void isValid() {
        TimeOfDayValidator validator = new TimeOfDayValidator();

        assertTrue(validator.isValid("09:00AM", null));
        assertTrue(validator.isValid("12:30pm", null));
        assertTrue(validator.isValid("04:45PM", null));
        assertTrue(validator.isValid("04:45", null));
        assertTrue(validator.isValid("14:45", null));

        assertFalse(validator.isValid("24:45", null));
        assertFalse(validator.isValid("25:00AM", null));
        assertFalse(validator.isValid("12:60pm", null));
        assertFalse(validator.isValid("invalidTime", null));
    }
}