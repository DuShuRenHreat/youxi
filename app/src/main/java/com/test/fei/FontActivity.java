package com.test.fei;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;

public class FontActivity extends Activity {
    public int mScreenWidth = 0;
    public int mScreenHeight = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new FootView(this));
        Display display = getWindowManager().getDefaultDisplay();
        mScreenHeight = display.getHeight();
        mScreenWidth = display.getWidth();
    }
    class FootView extends View{
        public final static String STR_WIDTH = "获取字符串宽为：";
        public final static String STR_HEIGHT = "获取字符串高为：";
        Paint paint = null;
        public FootView(Context context) {
            super(context);
            paint = new Paint();
            this.setBackgroundColor(Color.BLACK);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint = new Paint();
            paint.setTextSize(50);
            paint.setColor(Color.WHITE);
            canvas.drawText(STR_WIDTH + mScreenWidth,0,50,paint);
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            canvas.drawText(STR_HEIGHT + mScreenHeight,0,100,paint);

        }
    }
}
