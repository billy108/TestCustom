package com.zhaoshenghua.testcustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBar extends RelativeLayout{
    public static final int LEFT_BUTTUN = 0;
    public static final int RIGHT_BUTTUN = 1;

    private Drawable mLeftBackgroud, mRightBackgroud;
    private String mLeftText, mTitleText, mRightText;
    private int mLeftTextColor, mTitleTextColor, mRightTextColor;
    private float mTitleSize;

    private Button mLeftButton, mRightButton;
    private TextView mTitleView;

    private LayoutParams mRightParams, mLeftParams, mTitleParams;

    private TopBarClickListener mListener;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftBackgroud = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, Color.BLACK);

        mTitleText = ta.getString(R.styleable.TopBar_myTitle);
        mTitleSize = ta.getDimension(R.styleable.TopBar_myTitleTextSize, 15);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_myTitleTextColor, Color.BLACK);

        mRightBackgroud = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, Color.BLACK);

        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setText(mLeftText);
        mLeftButton.setBackground(mLeftBackgroud);
        mLeftButton.setTextColor(mLeftTextColor);

        mTitleView.setText(mTitleText);
        mTitleView.setTextSize(mTitleSize);
        mTitleView.setTextColor(mTitleTextColor);

        mRightButton.setBackground(mRightBackgroud);
        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);

        mTitleView.setGravity(Gravity.CENTER);

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
        addView(mRightButton, mRightParams);

        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
        addView(mLeftButton, mLeftParams);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, 1);
        addView(mTitleView, mTitleParams);

        bindEvents();
    }

    private void bindEvents() {
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.leftClick();
                }
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.rightClick();
                }
            }
        });
    }

    public interface TopBarClickListener{
        void leftClick();
        void rightClick();
    }

    public void setOnTopBarClickListener(TopBarClickListener mListener){
        this.mListener = mListener;
    }

    public void setButtonVisable(int childView, boolean flag){
        switch (childView) {
            case LEFT_BUTTUN:
                if (flag) {
                    mLeftButton.setVisibility(VISIBLE);
                } else {
                    mLeftButton.setVisibility(INVISIBLE);
                }
                break;
            case RIGHT_BUTTUN:
                if (flag) {
                    mRightButton.setVisibility(VISIBLE);
                } else {
                    mRightButton.setVisibility(INVISIBLE);
                }
                break;
        }
    }

}
