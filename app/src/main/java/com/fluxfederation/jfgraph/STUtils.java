package com.fluxfederation.jfgraph;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class STUtils {

    public static ArrayList<BarData> createSTGraphData() {
        ArrayList<BarData> graphData = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>(Arrays.asList("S", "M", "T", "W", "T", "F", "S"));

        for (int i = 0; i < 7 * 52; i++) {
            int modulus = i % 7;
            graphData.add(createSTBarData(days.get(modulus)));
        }

        return graphData;
    }

    private static BarData createSTBarData(String label) {
        int numberOfSegments = new Random().nextInt(4);
        ArrayList<BarSegment> barSegments = new ArrayList<>();
        for (int i = 0; i <= numberOfSegments; i++) {
            switch (i) {
                case 3:
                    barSegments.add(createFirstSegment());
                    break;
                case 2:
                    barSegments.add(createSecondSegment());
                    break;
                case 1:
                    barSegments.add(createThirdSegment());
                    break;
                case 0:
                    barSegments.add(createFourthSegment());
                    break;
            }
        }

        return new BarData(barSegments, label);
    }

    private static BarSegment createFirstSegment() {
        return new BarSegment(new Random().nextInt(24) + 1,
                Color.parseColor("#0E5A7F"));
    }

    private static BarSegment createSecondSegment() {
        return new BarSegment(new Random().nextInt(24) + 1,
                Color.parseColor("#1390ce"));
    }

    private static BarSegment createThirdSegment() {
        return new BarSegment(new Random().nextInt(24) + 1,
                Color.parseColor("#37BDFF"));
    }

    private static BarSegment createFourthSegment() {
        return new BarSegment(new Random().nextInt(24) + 1,
                Color.parseColor("#AFDFFA"));
    }
}
