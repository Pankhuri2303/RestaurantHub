package com.interview.restaurant.controller;


import com.interview.restaurant.response.RestaurantDeals;
import com.interview.restaurant.service.RestaurantDealsService;
import com.interview.restaurant.validator.ValidTimeOfDay;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/deals")
public class RestaurantDealsController {


    private final RestaurantDealsService restaurantDealsService;

    public RestaurantDealsController(RestaurantDealsService restaurantDealsService) {
        this.restaurantDealsService = restaurantDealsService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public List<RestaurantDeals> getDeals(@RequestParam(value = "timeOfDay") @ValidTimeOfDay String timeOfDay) {
        return restaurantDealsService.getDealsForTimeOfDay(timeOfDay);
    }
}
