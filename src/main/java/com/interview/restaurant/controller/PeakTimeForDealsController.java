package com.interview.restaurant.controller;

import com.interview.restaurant.response.PeakTime;
import com.interview.restaurant.service.PeakTimeForDealsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/peakTimeForDeals")
public class PeakTimeForDealsController {

    private final PeakTimeForDealsService peakTimeForDealsService;

    public PeakTimeForDealsController(PeakTimeForDealsService peakTimeForDealsService) {
        this.peakTimeForDealsService = peakTimeForDealsService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PeakTime> getPeakTimeForDeals() {
        return new ResponseEntity<>(peakTimeForDealsService.getPeakTimeForDeals(), HttpStatus.OK);
    }
}
