package com.fluxfederation.jfgraph;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HHRUtils {

    public static ArrayList<BarData> createHHRGraphData() {
        ArrayList<BarData> graphData = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            graphData.add(createHHREmptyBarData());
        }

        for (int i = 24; i < 48; i++) {
            graphData.add(createHHRBarData());
        }

        return graphData;
    }

    private static BarData createHHREmptyBarData() {
        return new BarData(new ArrayList<>(Collections.singletonList(createHHRMissingSegment())), "", BarData.MISSING_BAR);
    }

    private static BarData createHHRBarData() {
        return new BarData(new ArrayList<>(Collections.singletonList(createHHRSegment())), "");
    }
    private static BarSegment createHHRMissingSegment() {
        return new BarSegment(100,
                Color.parseColor("#FF5492"));
    }

    private static BarSegment createHHRSegment() {
        return new BarSegment(new Random().nextInt(99) + 1,
                Color.parseColor("#FF5492"));
    }
}
