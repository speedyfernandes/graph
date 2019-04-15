package com.fluxfederation.jfgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
    }

    public void showHHRGraph(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.putExtra(MainActivity.GRAPH_TYPE, MainActivity.HHR_GRAPH);
        startActivity(mainActivity);

    }

    public void showSTGraph(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.putExtra(MainActivity.GRAPH_TYPE, MainActivity.ST_GRAPH);
        startActivity(mainActivity);
    }

    public void showSolarGraph(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.putExtra(MainActivity.GRAPH_TYPE, MainActivity.SOLAR_GRAPH);
        startActivity(mainActivity);
    }
}
