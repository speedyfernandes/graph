package com.fluxfederation.jfgraph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int oldPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screenWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        int barWidth = screenWidth / 15;
        int padding =  screenWidth / 2 - barWidth / 2;

        RecyclerView graphView = findViewById(R.id.graphView);
        graphView.setAdapter(new GraphViewAdapter(createGraphData(), barWidth));
        graphView.setPadding(padding, 0, padding, 0);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        graphView.setLayoutManager(linearLayoutManager);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(graphView);

        View graphSelectionBar = findViewById(R.id.graphSelectionBar);
        View labelSelectionBar = findViewById(R.id.labelSelectionBar);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(barWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);

        graphSelectionBar.setLayoutParams(lp);
        labelSelectionBar.setLayoutParams(lp);

        graphView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View oldView = linearLayoutManager.findViewByPosition(oldPosition);
                    BarData barData = ((GraphViewAdapter)recyclerView.getAdapter()).graphData.get(oldPosition);
                    if (oldView != null) {
                        oldView.findViewById(R.id.barSegment).setBackgroundColor(barData.barSegments.get(0).colour);
                    }

                    View centerView = snapHelper.findSnapView(linearLayoutManager);
                    oldPosition = linearLayoutManager.getPosition(centerView);
                    centerView.findViewById(R.id.barSegment).setBackgroundColor(Color.parseColor("#FB0D6A"));
                }
            }
        });

        graphView.smoothScrollToPosition(48);
    }

    private ArrayList<BarData> createGraphData() {
        ArrayList<BarData> graphData = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>(Arrays.asList("S","M", "T", "W", "T", "F","S"));

        for(int i = 0; i < 7*52; i++) {
            int modulus = i%7;
            boolean isWeekDay = false;
            if(modulus == 0 ||modulus == 6) {
                isWeekDay = true;
            }
            graphData.add(createBarData(days.get(modulus), isWeekDay));
        }

        return graphData;
    }

    private BarData createBarData(String label, boolean isWeekDay) {
        //final BarSegment segment = isWeekDay ? createWeekdaySegment() : createWeekendSegment();
        return new BarData(new ArrayList<>(Arrays.asList(createWeekdaySegment(), createWeekendSegment())),label);
    }

    private BarSegment createWeekdaySegment() {
        return new BarSegment(new Random().nextInt(49) + 1,
                Color.parseColor("#FC9FC3"));
    }

    private BarSegment createWeekendSegment() {
        return new BarSegment(new Random().nextInt(49) + 1,
                Color.parseColor("#BC206B"));
    }
}
