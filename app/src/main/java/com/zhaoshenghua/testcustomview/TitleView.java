package com.zhaoshenghua.testcustomview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TitleView extends FrameLayout{
    private Button mLeftButton;
    private TextView mTitleText;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        mTitleText = (TextView) findViewById(R.id.title_text);
        mLeftButton = (Button) findViewById(R.id.button_left);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setTitleText(String text) {
        mTitleText.setText(text);
    }

    public void setLeftButtonText(String text) {
        mLeftButton.setText(text);
    }

    public void setLeftButtonListener(OnClickListener l){
        mLeftButton.setOnClickListener(l);
    }
}
