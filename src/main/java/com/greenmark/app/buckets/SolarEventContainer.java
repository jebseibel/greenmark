package com.greenmark.app.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SolarEventContainer {
    @JsonProperty("solar_events")
    private List<SolarEvent> solarEvents;
}
