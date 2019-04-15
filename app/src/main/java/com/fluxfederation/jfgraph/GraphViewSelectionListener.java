package com.fluxfederation.jfgraph;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class GraphViewSelectionListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager linearLayoutManager;
    SnapHelper snapHelper;
    int oldPosition = 0;
    final String selectionColour;

    public GraphViewSelectionListener(LinearLayoutManager linearLayoutManager,
                                      SnapHelper snapHelper,
                                      String selectionColour) {
        this.linearLayoutManager = linearLayoutManager;
        this.snapHelper = snapHelper;
        this.selectionColour = selectionColour;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.getAdapter() != null) {
            View oldView = linearLayoutManager.findViewByPosition(oldPosition);
            BarData barData = ((GraphViewAdapter)recyclerView.getAdapter()).graphData.get(oldPosition);

            if (oldView != null) {
                resetSelectedGraphBars(oldView, barData);
            }

            View centerView = snapHelper.findSnapView(linearLayoutManager);
            if(centerView != null) {
                oldPosition = linearLayoutManager.getPosition(centerView);
                setSelectedGraphBars(centerView);
            }
        }
    }

    private void resetSelectedGraphBars(View parentView, BarData barData) {
        LinearLayout containerView = parentView.findViewById(R.id.barContainer);
        for(int i =0; i<containerView.getChildCount(); i++) {
            View barView = containerView.getChildAt(i);
            barView.setBackgroundColor(barData.barSegments.get(i).colour);
        }
    }

    private void setSelectedGraphBars(View parentView) {
        LinearLayout containerView = parentView.findViewById(R.id.barContainer);
        for(int i =0; i<containerView.getChildCount(); i++) {
            View barView = containerView.getChildAt(i);
            barView.setBackgroundColor(Color.parseColor(selectionColour));
        }
    }
}
