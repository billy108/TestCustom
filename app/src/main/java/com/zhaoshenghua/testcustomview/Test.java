package com.zhaoshenghua.testcustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Test extends View{
    public Test(Context context) {
        super(context);
    }

    public Test(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Test, defStyleAttr, R.style.AppTheme);

//        custom_size = a.getDimensionPixelSize(R.styleable.Test_size, 100);
//        custom_backgroud = a.getColor(R.styleable.Test_background_color, DEFAULT_COLOR);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measurWidth, measureHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            measurWidth = widthSize;
        } else {
            measurWidth = 100;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = heightSize;
        } else {
            measureHeight = 100;
        }

        setMeasuredDimension(measurWidth, measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    }

}
