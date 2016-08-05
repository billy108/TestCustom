package com.zhaoshenghua.testcustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class WritingBoardView extends View {
    private int mBoardBackground;
    private int mPaintColor;
    private int mPaintWidth;
    private Paint mPaint;

    public WritingBoardView(Context context) {
        this(context, null);
    }

    public WritingBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WritingBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WritingBoardView);
        mBoardBackground = ta.getColor(R.styleable.WritingBoardView_boardBackground, Color.WHITE);
        mPaintColor = ta.getColor(R.styleable.WritingBoardView_paintColor, Color.WHITE);
        mPaintWidth = (int) ta.getDimension(R.styleable.WritingBoardView_paintWidth,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,getResources().getDisplayMetrics()));
        ta.recycle();
        mPaint = new Paint();
        setBackgroundColor(mBoardBackground);
        mPaint.setColor(mPaintColor);
        mPaint.setStrokeMiter(mPaintWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }
}
