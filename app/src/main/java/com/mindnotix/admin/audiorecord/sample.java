package com.mindnotix.admin.audiorecord;

/**
 * Created by Admin on 1/6/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.eralp.circleprogressview.CircleProgressView;
import com.eralp.circleprogressview.ProgressAnimationListener;

public class sample extends AppCompatActivity {

    private CircleProgressView mCircleProgressView1;
    private CircleProgressView mCircleProgressView2;
    private CircleProgressView mCircleProgressView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaaaa);

        mCircleProgressView1 = (CircleProgressView) findViewById(R.id.circle_progress_view1);
        mCircleProgressView1.setProgressWithAnimation(65, 1500);
     ;

        mCircleProgressView2 = (CircleProgressView) findViewById(R.id.circle_progress_view2);
        mCircleProgressView2.setInterpolator(new OvershootInterpolator());
        mCircleProgressView2.setProgressWithAnimation(75, 1000);

        mCircleProgressView3 = (CircleProgressView) findViewById(R.id.circle_progress_view3);
        mCircleProgressView3.setTextEnabled(false);
        mCircleProgressView3.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleProgressView3.setStartAngle(45);
        mCircleProgressView3.setProgressWithAnimation(85, 2000);

        mCircleProgressView3.addAnimationListener(new ProgressAnimationListener() {
            @Override
            public void onValueChanged(float value) {

            }

            @Override
            public void onAnimationEnd() {
                Toast.makeText(sample.this, "Animation of ProgressView3 done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}