package com.zhaoshenghua.testcustomview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyCustomView extends FrameLayout{
    private ProgressBar pb;
    private TextView tv;
    private Handler mHandler;

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View v = LayoutInflater.from(context).inflate(R.layout.count, null);
        pb = (ProgressBar) v.findViewById(R.id.pb);
        final int max = pb.getMax();
        tv = (TextView) v.findViewById(R.id.tv);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tv.setText(String.valueOf(msg.what));
                pb.setProgress(msg.what);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = max; i >= 0; i--) {
                Message msg = new Message();
                    msg.what = i;
                    mHandler.sendMessage(msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void setTime(int time){
        pb.setMax(time);
    }
}
