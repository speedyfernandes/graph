package com.fluxfederation.jfgraph;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class MainActivity extends AppCompatActivity {

    public static final String GRAPH_TYPE = "GRAPH_TYPE";
    public static final int HHR_GRAPH = 0;
    public static final int SOLAR_GRAPH = 1;
    public static final int ST_GRAPH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int graphType = getIntent().getExtras().getInt(GRAPH_TYPE, HHR_GRAPH);

        int numberOfBars = 0;
        boolean usePadding = false;
        ArrayList<BarData> barData = new ArrayList<>();
        int scrollToPosition = 0;
        boolean showDaySelector = false;
        boolean showBarSelector = false;
        boolean highlightSelectedDay = false;
        String selectionColour = "#FB0D6A";
        int barPadding = 2;

        switch (graphType) {
            case HHR_GRAPH:
                usePadding = false;
                numberOfBars = 48;
                barData.addAll(HHRUtils.createHHRGraphData());
                scrollToPosition = 0;
                showDaySelector = false;
                showBarSelector = false;
                highlightSelectedDay = false;
                selectionColour = "#FB0D6A";
                barPadding = 2;
                break;
            case SOLAR_GRAPH:
                numberOfBars = 15;
                usePadding = true;
                barData.addAll(SolarUtils.createSolarGraphData());
                scrollToPosition = 7 * 52 - 1;
                showDaySelector = true;
                showBarSelector = true;
                highlightSelectedDay = true;
                selectionColour = "#FFB300";
                barPadding = 5;
                break;
            case ST_GRAPH:
                numberOfBars = 15;
                usePadding = true;
                barData.addAll(STUtils.createSTGraphData());
                scrollToPosition = 7 * 52 - 1;
                showDaySelector = true;
                showBarSelector = true;
                highlightSelectedDay = false;
                selectionColour = "#1390ce";
                barPadding = 5;
                break;
        }

        int screenWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        int barWidth = screenWidth / numberOfBars;
        RecyclerView graphView = findViewById(R.id.graphView);
        graphView.setAdapter(new GraphViewAdapter(this, barData, barWidth, barPadding));

        if (usePadding) {
            int padding = screenWidth / 2 - barWidth / 2;
            graphView.setPadding(padding, 0, padding, 0);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        graphView.setLayoutManager(linearLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(graphView);

        View graphSelectionBar = findViewById(R.id.graphSelectionBar);
        View labelSelectionBar = findViewById(R.id.labelSelectionBar);
        labelSelectionBar.setBackgroundColor(Color.parseColor(selectionColour));

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(barWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);

        graphSelectionBar.setLayoutParams(lp);
        labelSelectionBar.setLayoutParams(lp);

        if(highlightSelectedDay) {
            graphView.addOnScrollListener(new GraphViewSelectionListener(linearLayoutManager,
                    snapHelper,
                    selectionColour));
        }

        graphView.scrollToPosition(scrollToPosition - numberOfBars);
        graphView.smoothScrollToPosition(scrollToPosition);

        if (!showDaySelector) {
            labelSelectionBar.setVisibility(View.INVISIBLE);
        }

        if (!showBarSelector) {
            graphSelectionBar.setVisibility(View.INVISIBLE);
        }
    }
}
