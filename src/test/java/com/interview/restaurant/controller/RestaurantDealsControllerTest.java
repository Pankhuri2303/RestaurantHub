package com.interview.restaurant.controller;

import com.interview.restaurant.response.RestaurantDeals;
import com.interview.restaurant.service.RestaurantDealsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantDealsControllerTest {

    @Mock
    private RestaurantDealsService restaurantDealsService;

    @InjectMocks
    private RestaurantDealsController restaurantDealsController;

    @Test
    void getDeals() {
        RestaurantDeals restaurantDeals  = new RestaurantDeals();
        restaurantDeals.setRestaurantName("Test Restaurant");

        when(restaurantDealsService.getDealsForTimeOfDay(any(String.class)))
                .thenReturn(List.of(restaurantDeals));

        assertEquals("Test Restaurant",
                restaurantDealsController.getDeals("10:00AM").get(0).getRestaurantName());

    }
}