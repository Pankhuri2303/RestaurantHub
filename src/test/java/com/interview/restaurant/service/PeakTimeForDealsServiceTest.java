package com.interview.restaurant.service;

import com.interview.restaurant.dao.RestaurantDealsDb;
import com.interview.restaurant.response.PeakTime;
import com.interview.restaurant.response.RestaurantDeals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeakTimeForDealsServiceTest {

    @Mock
    private RestaurantDealsDb restaurantDealsDb;

    @InjectMocks
    private PeakTimeForDealsService peakTimeForDealsService;

    private final List<RestaurantDeals> dealList= new ArrayList<>();

    @BeforeEach
    public void setUp() {
        RestaurantDeals deals = new RestaurantDeals();
        deals.setRestaurantOpen("3:00pm");
        deals.setRestaurantClose("6:00pm");
        dealList.add(deals);

        RestaurantDeals deals1 = new RestaurantDeals();
        deals1.setRestaurantOpen("5:00pm");
        deals1.setRestaurantClose("8:00pm");
        dealList.add(deals1);

    }

    @Test
    public void testGetPeakTimeForDeals() {
        when(restaurantDealsDb.getAllDealsFromDB()).thenReturn(dealList);
        PeakTime peakTime = peakTimeForDealsService.getPeakTimeForDeals();

        assertEquals("5:00PM", peakTime.getPeakTimeStart());
        assertEquals("6:00PM", peakTime.getPeakTimeEnd());
    }
}
