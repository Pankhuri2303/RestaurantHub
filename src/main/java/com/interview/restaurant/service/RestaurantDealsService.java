package com.interview.restaurant.service;

import com.interview.restaurant.dao.RestaurantDealsDb;
import com.interview.restaurant.response.RestaurantDeals;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantDealsService {

    private final RestaurantDealsDb restaurantDealsDb;

    public RestaurantDealsService(RestaurantDealsDb restaurantDealsDb) {
        this.restaurantDealsDb = restaurantDealsDb;
    }

    public List<RestaurantDeals> getDealsForTimeOfDay(String timeOfDay) {
        List<RestaurantDeals> allDeals = restaurantDealsDb.getAllDealsFromDB();



        return allDeals;
//        return allDeals.stream()
//                .filter(deal -> deal.getTimeOfDay().equalsIgnoreCase(timeOfDay))
//                .toList();
    }

}
