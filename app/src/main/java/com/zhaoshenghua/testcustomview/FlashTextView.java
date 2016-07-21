package com.zhaoshenghua.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class FlashTextView extends TextView {
    private Paint mPaint, mPaint1, mPaint2;
    private int mViewWidth;
    private LinearGradient mLinearGradient;
    private Matrix mGradienMatrix;
    private int mTranslate;

    public FlashTextView(Context context) {
        this(context, null);
    }

    public FlashTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlashTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint2 = new Paint();

        mPaint1.setColor(Color.RED);
        mPaint2.setColor(Color.GRAY);

        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                    new int[]{Color.BLACK, 0xffffffff, Color.BLACK},
                    null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradienMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(5, 5, getMeasuredWidth() - 5, getMeasuredHeight() - 5, mPaint2);
        canvas.translate(5, 0);
        canvas.save();
        super.onDraw(canvas);
        canvas.restore();
        if (mGradienMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate -= mViewWidth;
            }
        }
        mGradienMatrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(mGradienMatrix);
        postInvalidateDelayed(1000);
    }
}
