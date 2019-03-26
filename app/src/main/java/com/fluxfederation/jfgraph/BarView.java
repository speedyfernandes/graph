package com.fluxfederation.jfgraph;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BarView extends RecyclerView.ViewHolder {

    LinearLayout barContainer;
    TextView barLabel;

    public BarView(@NonNull View itemView) {
        super(itemView);

        barContainer = itemView.findViewById(R.id.barContainer);
        barLabel = itemView.findViewById(R.id.barLabel);
    }
}
