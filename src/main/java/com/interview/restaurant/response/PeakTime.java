package com.interview.restaurant.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "peakTimeStart", "peakTimeEnd" })
public class PeakTime {

    private String peakTimeStart;
    private String peakTimeEnd;
}
