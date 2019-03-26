package com.fluxfederation.jfgraph;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GraphViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<BarData> graphData;
    int barWidth;

    public GraphViewAdapter(ArrayList<BarData> graphData, int barWidth) {
        this.graphData = graphData;
        this.barWidth = barWidth;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BarView(LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_bar_view, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        BarData barData = graphData.get(position);

        BarView view = (BarView) holder;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(barWidth, 0);

        lp.weight = barData.barSegments.get(0).percentage;
        view.barSegment.setBackgroundColor(barData.barSegments.get(0).colour);
        view.barSegment.setLayoutParams(lp);
        view.barLabel.setText(barData.barLabel);
    }

    @Override
    public int getItemCount() {
        return graphData.size();
    }
}