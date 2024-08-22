package com.greenmark.datafeed.service;

import org.junit.jupiter.api.Test;

import java.util.List;

class GenerateTAResponseTest {

    @Test
    void getDays() {
        List<Long> result = GenerateTAResponse.getDays(3);
       // assertTrue(result > 0);
    }
}