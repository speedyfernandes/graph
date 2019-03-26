package com.fluxfederation.jfgraph;

import java.util.ArrayList;

public class BarData {
    ArrayList<BarSegment> barSegments;
    String barLabel;

    public BarData(ArrayList<BarSegment> barSegments, String barLabel) {
        this.barSegments = barSegments;
        this.barLabel =  barLabel;
    }
}
