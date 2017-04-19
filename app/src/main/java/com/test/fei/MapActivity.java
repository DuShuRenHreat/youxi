package com.test.fei;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.test.fei.view.PersonView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapActivity extends Activity {
    private RelativeLayout layout;
    private ExecutorService loop = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
        layout = (RelativeLayout) findViewById(R.id.per_layer);
        final PersonView personView = new PersonView(this);
        layout.addView(personView);
        personView.resize();
        layout.setOnTouchListener(new View.OnTouchListener() {

            private boolean track = false;
            private boolean handleDown = true;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!handleDown) return false;
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        track = true;
                        handleDown = false;
                        moveTo(motionEvent.getX() - personView.getWidth() / 2, motionEvent.getY() - personView.getHeight() / 2);
                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (!track) return false;
//                        personView.setX((motionEvent.getX() - 0) - personView.getWidth() / 2);
//                        personView.setY((motionEvent.getY() - 0) - personView.getHeight() / 2);
//                        break;
                    case MotionEvent.ACTION_UP:
//                        track = false;
                        break;
                }
                personView.isMove = track;
                //personView.postInvalidate();
                return false;
            }

            public void moveTo(final float x, final float y) {
                Log.e("s", String.format("x:%f y:%f vx:%f, vy:%f", x, y, personView.getX(), personView.getY()));
                int s = (int) Math.abs((Math.pow(x, 2) + Math.pow(y ,2)) - (Math.pow(personView.getX(), 2) + Math.pow(personView.getY(), 2)));
                s = (int) Math.sqrt(s);
                final int speed;
                if (0 <= s && s < 50) {
                    speed = 5;
                } else if (50 <= s && s < 300) {
                    speed = 10;
                } else if (300 <= s && s < 600) {
                    speed = 20;
                } else {
                    speed = 30;
                }
                Log.e("speed", speed + "");
                int times = speed;
                final float dx = personView.getX() - x;
                final float dy = personView.getY() - y;
                final float ddx = Math.abs(dx / speed);
                final float ddy = Math.abs(dy / speed);
                while (times > -1) {
                    loop.execute(new Runnable() {
                        @Override
                        public void run() {

                            if (x != personView.getX()) {
                                if (dx > 0) {
//                                    Log.e("move", "应该左移" + (ddx < dx ? x :( personView.getX() -  ddx)));
                                    personView.setX(0 + (ddx > Math.abs(personView.getX() - x) ? x :( personView.getX() -  ddx)));
                                } else {
//                                    Log.e("move", "应该右移" + (ddx < dx ? x :( personView.getX() + ddx)));
                                    personView.setX(0 + (ddx > Math.abs(personView.getX() - x) ? x :( personView.getX() + ddx)));
                                }
                            }
                            if (y != personView.getY()) {
                                if (dy > 0) {
//                                    Log.e("move", "应该上移" + ( ddy < dy ?  y :( personView.getY()- ddy)));
                                    personView.setY(0 + (ddy > Math.abs(personView.getY() - y) ?  y :( personView.getY()- ddy)));
                                } else {
//                                    Log.e("move", "应该下移" + ( ddy < dy ? y :(personView.getY() + ddy)));
                                    personView.setY(0 + (ddy > Math.abs(personView.getY() - y) ? y :(personView.getY() + ddy)));
                                }
                            }
                            personView.postInvalidate();
                            try {
                                Thread.sleep(PersonView.SPEED);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    times --;
                }
                loop.execute(new Runnable() {
                    @Override
                    public void run() {
                        personView.isMove = false;
                        handleDown = true;
                    }
                });
            }
        });

    }


}
