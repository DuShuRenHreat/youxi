package com.test.fei.view;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.fei.R;

/**
 * Created by asdf on 2017/4/19.
 */
public class PersonView extends View{
    public static final int SPEED = 260;

    private Paint paint = null;
    private Bitmap[] bitmaps = new Bitmap[4];
    private int[] imgs = {R.drawable.hero_a,R.drawable.hero_b,R.drawable.hero_c,R.drawable.hero_d};
    int playID = 0;
    public PersonView(Context context) {
        super(context);
        initView();
    }

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PersonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public PersonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private boolean loop = true;
    public boolean isMove = false;

    public void initView(){
        paint = new Paint();

        for(int i = 0 ; i < 4; i++){
            bitmaps[i] = BitmapFactory.decodeResource(this.getResources(),imgs[i]);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (loop) {
                    if (!isMove) {
                        PersonView.this.postInvalidate();
                    }

                    try {
                        Thread.sleep(SPEED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void resize() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
        layoutParams.width = this.bitmaps[0].getWidth();
        layoutParams.height = this.bitmaps[0].getHeight();
        layoutParams.alignWithParent = true;
        this.setLayoutParams(layoutParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = bitmaps[playID = ++playID == 4 ? 0 : playID];
        canvas.drawBitmap(bitmap,0,0 ,paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.loop = false;
    }

}