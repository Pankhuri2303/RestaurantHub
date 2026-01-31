package com.interview.restaurant.service;

import com.interview.restaurant.dao.RestaurantDealsDb;
import com.interview.restaurant.response.RestaurantDeals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantDealsServiceTest {

    @Mock
    private RestaurantDealsDb restaurantDealsDb;

    private RestaurantDeals restaurantDeals;

    @InjectMocks
    private RestaurantDealsService classUnderTest;

    @BeforeEach
    public void setUp() {
        restaurantDeals = new RestaurantDeals();
        restaurantDeals.setRestaurantOpen("9:00am");
        restaurantDeals.setRestaurantClose("4:00pm");
        restaurantDeals.setRestaurantName("R1");
    }

    @ParameterizedTest
    @ValueSource(strings = {"09:00AM", "04:00pm", "12:00pm","9:00","15:30"})
    public void getDealsForTimeOfDay_whenValidTimeOfDay(String timeOfDay) {

        when(restaurantDealsDb.getAllDealsFromDB()).thenReturn(List.of(restaurantDeals));

        assertEquals(1, classUnderTest.getDealsForTimeOfDay(timeOfDay).size());
    }


    @ParameterizedTest
    @ValueSource(strings = {"08:00AM", "09:00pm", "00:00am","02:00","20:00"})
    public void getDealsForTimeOfDay_whenTimeOfDayBeforeOpeningTime(String timeOfDay) {

        when(restaurantDealsDb.getAllDealsFromDB()).thenReturn(List.of(restaurantDeals));

        assertEquals(0, classUnderTest.getDealsForTimeOfDay(timeOfDay).size());
    }

    @Test
    public void getDealsForTimeOfDay_whenInvalidTimeOfDay() {
        String timeOfDay = "10ama";

        when(restaurantDealsDb.getAllDealsFromDB()).thenReturn(List.of(restaurantDeals));

        assertEquals(0, classUnderTest.getDealsForTimeOfDay(timeOfDay).size());

    }

}
