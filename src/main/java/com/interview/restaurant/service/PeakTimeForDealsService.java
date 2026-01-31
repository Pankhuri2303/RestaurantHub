package com.interview.restaurant.service;

import com.interview.restaurant.dao.RestaurantDealsDb;
import com.interview.restaurant.response.PeakTime;
import com.interview.restaurant.response.RestaurantDeals;
import com.interview.restaurant.util.DateTimeParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class PeakTimeForDealsService {

    private final RestaurantDealsDb restaurantDealsDb;
    private final DateTimeFormatter timeFormatter = DateTimeParserUtil.customDateTimeFormatter;

    public PeakTimeForDealsService(RestaurantDealsDb restaurantDealsDb) {
        this.restaurantDealsDb = restaurantDealsDb;
    }

    public PeakTime getPeakTimeForDeals() {
        List<RestaurantDeals> deals = restaurantDealsDb.getAllDealsFromDB();

        return getPeakTime(deals);
    }

    private PeakTime getPeakTime(List<RestaurantDeals> deals) {
        int[] activeDealsPerMinute = new int[1440];
        for (RestaurantDeals deal : deals) {
            try {

                int openMinute = calculateMinutes(deal.getRestaurantOpen());
                int closeMinute = calculateMinutes(deal.getRestaurantClose());

                // Handle case where restaurant closes past midnight (next day)
                if (closeMinute <= openMinute) {
                    for (int minute = openMinute; minute < 1440; minute++) {
                        activeDealsPerMinute[minute]++;
                    }
                    for (int minute = 0; minute <= closeMinute; minute++) {
                        activeDealsPerMinute[minute]++;
                    }
                } else {
                    // Handle normal case: open and close on same day
                    for (int minute = openMinute; minute <= closeMinute; minute++) {
                        activeDealsPerMinute[minute]++;
                    }
                }

            } catch (Exception e) {
                log.error("Invalid time format for deal: {}. Skipping this deal.", deal, e);
            }
        }

        int maxActiveDeals = calculateMaxActiveDeals(activeDealsPerMinute);

        return calculatePeakTime(activeDealsPerMinute, maxActiveDeals);
    }

    private PeakTime calculatePeakTime(int[] activeDealsPerMinute, int maxActiveDeals) {
        int peakStartMinute = 0;
        int peakEndMinute = 0;
        boolean isPeakPeriod = false;
        for (int minute = 0; minute < 1440; minute++) {
            if (activeDealsPerMinute[minute] == maxActiveDeals && !isPeakPeriod) {
                peakStartMinute = minute;
                isPeakPeriod = true;
            } else if (activeDealsPerMinute[minute] < maxActiveDeals && isPeakPeriod) {
                peakEndMinute = minute - 1;
                break;
            }
        }

        // Handle case where peak period extends to end of day
        if (isPeakPeriod && peakEndMinute == 0) {
            peakEndMinute = 1439; // Last minute of the day
        }

        // Convert minutes back to time format
        LocalTime peakStart = getTimeFromMinutes(peakStartMinute);
        LocalTime peakEnd = getTimeFromMinutes(peakEndMinute);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);

        PeakTime peakTime = new PeakTime();
        peakTime.setPeakTimeStart(peakStart.format(outputFormatter));
        peakTime.setPeakTimeEnd(peakEnd.format(outputFormatter));

        return peakTime;
    }

    private LocalTime getTimeFromMinutes(int minute) {
        return LocalTime.of(minute / 60, minute % 60);
    }

    private int calculateMaxActiveDeals(int[] activeDealsPerMinute) {
        int maxActiveDeals = 0;
        for (int minute = 0; minute < 1440; minute++) {
            if (activeDealsPerMinute[minute] > maxActiveDeals) {
                maxActiveDeals = activeDealsPerMinute[minute];
            }
        }
        return maxActiveDeals;
    }

    private int calculateMinutes(String timeStr) {
        LocalTime time = LocalTime.parse(timeStr.toUpperCase(), timeFormatter);
        return time.getHour() * 60 + time.getMinute();
    }
}
