package com.fluxfederation.jfgraph;

import java.util.ArrayList;

public class BarData {

    public static final int MISSING_BAR = 0;
    public static final int SKELETON_BAR = 1;
    public static final int NORMAL_BAR = 2;

    ArrayList<BarSegment> barSegments;
    String barLabel;
    int barType;

    public BarData(ArrayList<BarSegment> barSegments,
                   String barLabel,
                   int barType) {
        this.barSegments = barSegments;
        this.barLabel =  barLabel;
        this.barType = barType;
    }

    public BarData(ArrayList<BarSegment> barSegments,
                   String barLabel) {
        this.barSegments = barSegments;
        this.barLabel =  barLabel;
        this.barType = NORMAL_BAR;
    }
}
