package com.greenmark.app.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class SolarEvent {
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("date")
    private String date;
    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("type")
    private String type;
    @JsonProperty("class")
    private String eventClass;
    @JsonProperty("size")
    private String size;
    @JsonProperty("speed_km_per_s")
    private int speedKmPerS;

    // Getters and Setters
}

@Data
class Coordinates {
    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    // Getters and setters
}

