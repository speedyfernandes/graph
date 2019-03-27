package com.fluxfederation.jfgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

import androidx.annotation.Nullable;

public class GraphForeground extends View {

    private Paint linePaint;
    private Paint textPaint;
    private Path path;
    private float startX;
    private float textCenter;

    public GraphForeground(Context context) {
        super(context);

        init();
    }

    public GraphForeground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public GraphForeground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(32);

        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(1);
        linePaint.setAlpha(25);
        linePaint.setStyle(Paint.Style.STROKE);

        path = new Path();

        float[] intervals = new float[]{5.0f, 5.0f};
        float phase = 0;

        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);

        linePaint.setPathEffect(dashPathEffect);

        final Rect bounds = new Rect();
        textPaint.getTextBounds("100000 kwh", 0, 10, bounds);

        startX = bounds.right;
        textCenter = bounds.height() / 2f ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int canvasWidth = getWidth();
        final int canvasHeight = getHeight();

        for (int i = 0; i < 4; i++) {

            final float y = (float)(canvasHeight * (0.25*i));

            // Draw the thick label line
            path.moveTo(startX, y);
            path.lineTo(canvasWidth, y);
            canvas.drawPath(path, linePaint);

            // Add the text
            final String finalLabel = String.format(Locale.getDefault(), "%d kwh", (4-i)*25);
            float textWidth = textPaint.measureText(finalLabel);

            canvas.drawText(finalLabel, startX - textWidth - startX/8, y+textCenter , textPaint);
        }
    }
}
