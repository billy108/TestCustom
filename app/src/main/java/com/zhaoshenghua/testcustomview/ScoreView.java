package com.zhaoshenghua.testcustomview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class ScoreView extends View{
    private int score, score_text;
    private float unitage, arc_y;
    private Paint mPaint_blace, mPaint_white;
    private RectF mRectf;

    public ScoreView(Context context, int score) {
        super(context);
        init(score);
    }

    private void init(int score) {
        this.score = score;
        Resources res = getResources();
        unitage = res.getDimension(R.dimen.unitage);

        mPaint_blace = new Paint();
        mPaint_blace.setAntiAlias(true);
        mPaint_blace.setDither(true);

        mPaint_blace.setColor(Color.BLACK);
        mPaint_blace.setStyle(Paint.Style.STROKE);
        mPaint_blace.setStrokeWidth(unitage * 0.2f);

        mPaint_white = new Paint();
        mPaint_white.setAntiAlias(true);
        mPaint_white.setDither(true);
        mPaint_white.setStyle(Paint.Style.STROKE);
        mPaint_white.setStrokeWidth(unitage * 0.2f);
        mPaint_white.setTextSize(unitage * 6);
        mPaint_white.setTextAlign(Paint.Align.CENTER);
        mPaint_white.setColor(Color.WHITE);

        mRectf = new RectF();
        mRectf.set(unitage*0.5f,unitage*0.5f,unitage*18.5f,unitage*18.5f);
        setLayoutParams(new ViewGroup.LayoutParams((int)(unitage*19.5f),(int)(unitage*19.5f)));

        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                new DrawThread();
                getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
    }


    private class DrawThread implements Runnable{
        private final Thread mDrawThread;
        private int statek;
        int count;

        public DrawThread() {
            mDrawThread = new Thread(this);
            mDrawThread.start();
        }

        @Override
        public void run() {
            while (true) {
                switch (statek) {
                    case 0:
                        try {
                            Thread.sleep(1000);
                            statek = 1;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            Thread.sleep(20);
                            arc_y += 3.6f;
                            score_text ++;
                            count ++;
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                if (count >= score) {
                    break;
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRectf, 0, 360, false, mPaint_blace);
        canvas.drawArc(mRectf, -90, arc_y, false, mPaint_white);
        canvas.drawText(score_text + "", unitage*9.7f,unitage*11.0f,mPaint_white);
    }
}
