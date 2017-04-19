package com.test.fei;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class GeometryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GeometryView(this));
    }
    class GeometryView extends View {
        private Paint paint;
        public GeometryView(Context context) {
            super(context);
            paint = new Paint();
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            paint.setColor(Color.BLACK);
            paint.setTextSize(20);
        //    canvas.drawText("绘制无规则几何图形哦！！",200,300,paint);

            paint.setStrokeWidth(4);
            canvas.drawLine(0,0,50,50,paint);
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);
          //  canvas.drawRect(0,0,100,100,paint);

        //    canvas.drawCircle(150,150,25,paint);
            paint.setColor(Color.BLACK);
            Path path = new Path();
            path.moveTo(400,400);
            path.lineTo(200,500);
            path.lineTo(300,600);
            path.lineTo(500,600);
            path.lineTo(600,500);
            path.close();
            canvas.drawPath(path, paint);


        }
    }
}
