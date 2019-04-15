package com.fluxfederation.jfgraph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GraphViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<BarData> graphData;
    int barWidth;
    int padding;

    public GraphViewAdapter(Context context,
                            ArrayList<BarData> graphData,
                            int barWidth,
                            int padding) {
        this.context = context;
        this.graphData = graphData;
        this.barWidth = barWidth;
        this.padding = padding;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BarView(LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_bar_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        BarData barData = graphData.get(position);

        BarView view = (BarView) holder;

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(barWidth, 0);
        view.barContainer.setPadding(padding, 0, padding, 0);
        lp.weight = 1;

        view.barContainer.setLayoutParams(lp);
        view.barContainer.removeAllViews();

        for(BarSegment barSegment : barData.barSegments) {
            lp.weight = barSegment.percentage;
            View barSegmentView = new View(context);
            barSegmentView.setBackgroundColor(barSegment.colour);

            view.barContainer.addView(barSegmentView, lp);
        }

        view.barLabel.setText(barData.barLabel);
    }

    @Override
    public int getItemCount() {
        return graphData.size();
    }
}