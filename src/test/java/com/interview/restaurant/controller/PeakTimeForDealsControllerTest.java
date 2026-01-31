package com.interview.restaurant.controller;

import com.interview.restaurant.response.PeakTime;
import com.interview.restaurant.service.PeakTimeForDealsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeakTimeForDealsControllerTest {

    @Mock
    private PeakTimeForDealsService peakTimeForDealsService;

    @InjectMocks
    private PeakTimeForDealsController peakTimeForDealsController;

    @Test
    public void testGetPeakTimeForDeals() {
        when(peakTimeForDealsService.getPeakTimeForDeals()).thenReturn(new PeakTime());
        ResponseEntity<PeakTime> response = peakTimeForDealsController.getPeakTimeForDeals();
        assertNotNull(response.getBody());
        assertEquals("200 OK", response.getStatusCode().toString());
    }
}
