package com.fluxfederation.jfgraph;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BarView extends RecyclerView.ViewHolder {

    View barSegment;
    TextView barLabel;

    public BarView(@NonNull View itemView) {
        super(itemView);

        barSegment = itemView.findViewById(R.id.barSegment);
        barLabel = itemView.findViewById(R.id.barLabel);
    }
}
