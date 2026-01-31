package com.interview.restaurant.service;

import com.interview.restaurant.dao.RestaurantDealsDb;
import com.interview.restaurant.response.RestaurantDeals;
import com.interview.restaurant.util.DateTimeParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class RestaurantDealsService {

    private final RestaurantDealsDb restaurantDealsDb;

    private final DateTimeFormatter formatter = DateTimeParserUtil.customDateTimeFormatter;


    public RestaurantDealsService(RestaurantDealsDb restaurantDealsDb) {
        this.restaurantDealsDb = restaurantDealsDb;
    }

    public List<RestaurantDeals> getDealsForTimeOfDay(String timeOfDay) {
        List<RestaurantDeals> allDeals = restaurantDealsDb.getAllDealsFromDB();

        return getFilteredDeals(timeOfDay, allDeals);

    }

    private List<RestaurantDeals> getFilteredDeals(String timeOfDay, List<RestaurantDeals> allDeals) {
        log.info("Parsing restaurant deals for time of day: {}", timeOfDay);

        return allDeals.stream()
                .filter(deal -> isDealActiveAtTime(deal, timeOfDay))
                .toList();

    }

    private boolean isDealActiveAtTime(RestaurantDeals deal, String timeOfDay) {
        try {
            LocalTime dealRequestedTime = LocalTime.parse(timeOfDay.toUpperCase(), formatter);
            LocalTime dealStartTime = LocalTime.parse(deal.getRestaurantOpen().toUpperCase(), formatter);
            LocalTime dealEndTime = LocalTime.parse(deal.getRestaurantClose().toUpperCase(), formatter);

            if (dealEndTime.isBefore(dealStartTime)) {
                return !dealRequestedTime.isBefore(dealStartTime) || !dealRequestedTime.isAfter(dealEndTime);
            } else {
                return !dealRequestedTime.isBefore(dealStartTime) && !dealRequestedTime.isAfter(dealEndTime);
            }
        } catch (Exception e) {
            log.error("Error parsing time for deal: {}. Error: {}", deal.getRestaurantName(), e.getMessage());
            return false;
        }
    }

}
