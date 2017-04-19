package com.test.fei;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class FrameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_frame);
    }

}
