package com.interview.restaurant.dao;

import com.interview.restaurant.response.RestaurantDeals;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class RestaurantDealsDb {

    private final ObjectMapper objectMapper;

    public RestaurantDealsDb(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<RestaurantDeals> getAllDealsFromDB() {
        log.info("Fetching all restaurant deals from DB");
        // Mock implementation - in a real scenario, this would query a database
        try(InputStream is = new ClassPathResource("restaurant_deals.json").getInputStream()) {
            return List.of(objectMapper.readValue(is, RestaurantDeals[].class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load restaurant deals from DB", e);
        }
    }
}
