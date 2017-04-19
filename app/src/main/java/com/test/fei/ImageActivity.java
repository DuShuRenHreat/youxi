package com.test.fei;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ImageActivity extends Activity implements View.OnClickListener{
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    private MyImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        LinearLayout layout = (LinearLayout) findViewById(R.id.image_layout);
        view = new MyImageView(this);
        layout.addView(view);
        initView();
    }
    class MyImageView extends View{
        private Paint paint = null;
        private Bitmap bitmap = null;
        private Bitmap newbitmap = null;
        private Matrix matrix = null;
        private int m_Width = 0 ;
        private int m_Height = 0;
        //当前坐标
        private int x = 0 ;
        private int y = 0;
        private float multiple = 1f;
        private float degrees = 0f;
        public MyImageView(Context context) {
            super(context);
            initView();
        }

        public MyImageView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }

        public MyImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            initView();
        }
        public void initView(){
            paint = new Paint();
            paint.setColor(Color.WHITE);
            matrix = new Matrix();
            bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.pic);
            newbitmap = bitmap;
            m_Width = bitmap.getWidth();
            m_Height = bitmap.getHeight();
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(newbitmap,x,y,paint);
            invalidate();
        }
        public void moveToRight(){
            x+=10;
        }
        public void moveTLeft(){
            x-=10;
        }
        public void moveToTop(){
            y-=10;
            invalidate();
        }
        public void moveToBottom(){
            y+=10;
        }
        public void setBS(){
            matrix.reset();
            matrix.postScale(multiple,multiple);
            newbitmap = Bitmap.createBitmap(bitmap,0,0, m_Width,m_Height,matrix,true);
        }
        public void setRote(){
            matrix.reset();
            matrix.postRotate(degrees);
            newbitmap = Bitmap.createBitmap(bitmap,0,0,m_Width,m_Height,matrix,true);
        }
        public void rotateToRight(){
            degrees += 10;
            setRote();
        }
        public void rotateToLeft(){
            degrees -= 10;
            setRote();
        }
        public void setbig(){
            multiple += 0.1f;
            setBS();
        }
        public void setsmall(){
            multiple -= 0.1f;
            setBS();
        }
    }


    public void initView(){
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                view.moveToRight();
                break;
            case R.id.btn2:
                view.moveTLeft();
                break;
            case R.id.btn3:
                view.moveToTop();
                break;
            case R.id.btn4:
                view.moveToBottom();
                break;
            case R.id.btn5:
                view.rotateToRight();
                break;
            case R.id.btn6:
                view.rotateToLeft();
                break;
            case R.id.btn7:
                view.setbig();
                break;
            case R.id.btn8:
                view.setsmall();
                break;

        }
    }
}
