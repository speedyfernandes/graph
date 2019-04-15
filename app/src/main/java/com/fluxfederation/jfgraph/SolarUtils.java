package com.fluxfederation.jfgraph;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SolarUtils {

    public static ArrayList<BarData> createSolarGraphData() {
        ArrayList<BarData> graphData = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>(Arrays.asList("S", "M", "T", "W", "T", "F", "S"));

        for (int i = 0; i < 7 * 52; i++) {
            int modulus = i % 7;
            boolean isWeekDay = true;
            if (modulus == 0 || modulus == 6) {
                isWeekDay = false;
            }
            graphData.add(createSolarBarData(days.get(modulus), isWeekDay));
        }

        return graphData;
    }

    private static BarData createSolarBarData(String label, boolean isWeekDay) {
        final BarSegment segment = isWeekDay ? createSolarWeekdaySegment() : createSolarWeekendSegment();
        return new BarData(new ArrayList<>(Collections.singletonList(segment)), label);
    }

    private static BarSegment createSolarWeekdaySegment() {
        return new BarSegment(new Random().nextInt(49) + 1,
                Color.parseColor("#FFCA27"));
    }

    private static BarSegment createSolarWeekendSegment() {
        return new BarSegment(new Random().nextInt(49) + 1,
                Color.parseColor("#FDEEBF"));
    }
}
