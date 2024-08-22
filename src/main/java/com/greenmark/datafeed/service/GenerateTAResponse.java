package com.greenmark.datafeed.service;

import com.greenmark.datafeed.request.TAResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class GenerateTAResponse {

//
//    public static final TAResponse getResponseDay() {
//
//    }

    public static final TAResponse getFakeResponse() {
        List<Double> xx = Arrays.asList(0.0, 0.0, 73.74833, 73.72416, 70.676666, 70.045, 68.911, 67.41666, 66.802);
        Map<String, List<Double>> indicat = new HashMap<>();
        indicat.put("sma", xx);

        List<Long> yy = Arrays.asList(1583107200L,1583193600L,1583280000L,1583366400L,1583452800L,1583712000L,1583798400L,1583884800L,1583971200L,1584057600L);
        Map<String, List<Long>> timest = new HashMap<>();
        timest.put("t", yy);

        //
        TAResponse response = new TAResponse();
        response.setValues(indicat);
        response.setTimestamps(timest);
        return response;
    }

    public static List<Long> getDays(int numDays) {

        ZoneId zoneId = ZoneId.systemDefault();

        ArrayList<Long> days = new ArrayList<>(numDays);
        for (int i = numDays; i >= 0; i--) {
            LocalDateTime backDate = LocalDate.now(zoneId).atStartOfDay().minusDays(i);
//            LocalDateTime backDate = LocalDateTime.now().minusDays(i);
//            backDate.a
            System.out.println(backDate);
            long epoch = backDate.atZone(zoneId).toEpochSecond();
        }


        return days;
    }

//    public static Long getDays(int number) {
//
//        LocalDate localDate = LocalDate.now();
//        LocalDateTime startOfDay = localDate.atStartOfDay();
//
//        for (int i = 0; i < number; i++) {
//
//        }
//        LocalDate localDate = LocalDate.now();
//        LocalDateTime startOfDay = localDate.atStartOfDay();
//        long startOfDayEpoch = startOfDay.atZone(zoneId).toEpochSecond();
//        System.out.println(startOfDayEpoch);
//
//        System.out.println(startOfDay);
//        return epoch;
//    }

    public static Long getNowEpoch1() {
        LocalDateTime now = LocalDateTime.now();
        return getEpoch(now);
    }

    public static Long getNowEpoch() {
        LocalDateTime now = LocalDateTime.now();
        return getEpoch(now);
    }

    public static Long getEpoch(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        return localDateTime.atZone(zoneId).toEpochSecond();
    }
}
