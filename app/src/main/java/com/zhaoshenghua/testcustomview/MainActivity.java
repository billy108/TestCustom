package com.zhaoshenghua.testcustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Animation animation;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation = AnimationUtils.loadAnimation(this, R.anim.scaleanim);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn_animation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.startAnimation(animation);
            }
        });
    }

}
