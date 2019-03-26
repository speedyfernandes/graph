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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int width = 0;
    int oldPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        width = getWindow().getWindowManager().getDefaultDisplay().getWidth() / 15;
        int padding = getWindow().getWindowManager().getDefaultDisplay().getWidth() / 2 - width / 2;

        RecyclerView graphView = findViewById(R.id.graphView);
        graphView.setAdapter(new GraphViewAdapter());
        graphView.setPadding(padding, 0, padding, 0);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        graphView.setLayoutManager(linearLayoutManager);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(graphView);
        //graphView.addItemDecoration(new BarEdgePadding(getWindow().getWindowManager().getDefaultDisplay().getWidth() / 2));

        View graphSelectionBar = findViewById(R.id.graphSelectionBar);
        View labelSelectionBar = findViewById(R.id.labelSelectionBar);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);

        graphSelectionBar.setLayoutParams(lp);
        labelSelectionBar.setLayoutParams(lp);

        graphView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View oldView = linearLayoutManager.findViewByPosition(oldPosition);
                    if(oldView != null) {
                        oldView.findViewById(R.id.barSegment).setBackgroundColor(Color.parseColor("#BC206B"));
                    }

                    View centerView = snapHelper.findSnapView(linearLayoutManager);
                    oldPosition = linearLayoutManager.getPosition(centerView);
                    centerView.findViewById(R.id.barSegment).setBackgroundColor(Color.parseColor("#FB0D6A"));
                }
            }
        });

        graphView.smoothScrollToPosition(48);
    }

    private class GraphViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BarView(LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_bar_view, null));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            BarView view = (BarView) holder;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, 0);

            lp.weight = new Random().nextInt(99) + 1;

            switch (position % 7) {
                case 1:
                    view.barLabel.setText("M");
                    view.barSegment.setBackgroundColor(Color.parseColor("#BC206B"));
                    break;
                case 2:
                    view.barLabel.setText("T");
                    view.barSegment.setBackgroundColor(Color.parseColor("#BC206B"));
                    break;
                case 3:
                    view.barLabel.setText("W");
                    view.barSegment.setBackgroundColor(Color.parseColor("#BC206B"));
                    break;
                case 4:
                    view.barLabel.setText("T");
                    view.barSegment.setBackgroundColor(Color.parseColor("#BC206B"));
                    break;
                case 5:
                    view.barLabel.setText("F");
                    view.barSegment.setBackgroundColor(Color.parseColor("#BC206B"));
                    break;
                case 6:
                    view.barLabel.setText("S");
                    view.barSegment.setBackgroundColor(Color.parseColor("#FC9FC3"));
                    break;
                case 0:
                    view.barLabel.setText("S");
                    view.barSegment.setBackgroundColor(Color.parseColor("#FC9FC3"));
                    break;
            }
            view.barSegment.setLayoutParams(lp);
        }

        @Override
        public int getItemCount() {
            return 49;
        }
    }

    private class BarView extends RecyclerView.ViewHolder {

        View barSegment;
        TextView barLabel;

        public BarView(@NonNull View itemView) {
            super(itemView);

            barSegment = itemView.findViewById(R.id.barSegment);
            barLabel = itemView.findViewById(R.id.barLabel);
        }
    }

    private class BarEdgePadding extends RecyclerView.ItemDecoration {
        private final int edgePadding;

        public BarEdgePadding(int edgePadding) {
            this.edgePadding = edgePadding;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int itemCount = state.getItemCount();

            final int itemPosition = parent.getChildAdapterPosition(view);

            /*// no position, leave it alone
            if (itemPosition == RecyclerView.NO_POSITION) {
                return;
            }*/

            // first item
            if (itemPosition == 0) {
                outRect.set(edgePadding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            }
            // last item
            else if (itemCount > 0 && itemPosition == itemCount - 1) {
                outRect.set(view.getPaddingLeft(), view.getPaddingTop(), edgePadding, view.getPaddingBottom());
            }
            // every other item
            else {
                outRect.set(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            }
        }
    }
}
