package com.greenmark.app.buckets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenmark.app.BucketAllData;
import com.greenmark.database.DomainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolarEventContainerTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenJsonObjects_whenUsingJackson_thenSortedBySpeedCorrectly() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SolarEventContainer container =
                objectMapper.readValue(new File("src/test/resources/solar_events.json"),
                        SolarEventContainer.class);

        List<SolarEvent> events = container.getSolarEvents();
        Collections.sort(events, Comparator.comparingInt(event -> event.getSpeedKmPerS()));

        assertEquals(100, events.get(0).getSpeedKmPerS());
        assertEquals(500, events.get(1).getSpeedKmPerS());
        assertEquals(1000, events.get(2).getSpeedKmPerS());
        assertEquals(1500, events.get(3).getSpeedKmPerS());
    }
}