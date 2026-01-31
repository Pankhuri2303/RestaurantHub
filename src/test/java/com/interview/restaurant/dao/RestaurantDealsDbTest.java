package com.interview.restaurant.dao;

import com.interview.restaurant.response.RestaurantDeals;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

class RestaurantDealsDbTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    public RestaurantDealsDb classUnderTest = new RestaurantDealsDb(objectMapper);

    @Test
    public void getAllDealsFromDB() {
        assertNotNull(classUnderTest.getAllDealsFromDB());
    }

    @Test
    public void throwExceptionWhenFileNotFound() {
        ObjectMapper mockMapper = Mockito.mock(ObjectMapper.class, withSettings().lenient());
        when(mockMapper.readValue(any(InputStream.class), eq(RestaurantDeals[].class)))
                .thenThrow(new RuntimeException("JSON parsing failed"));

        RestaurantDealsDb testDb = new RestaurantDealsDb(mockMapper);


        Exception exception = assertThrows(RuntimeException.class, testDb::getAllDealsFromDB);

        String expectedMessage = "Failed to load restaurant deals from DB";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}