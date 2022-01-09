package com.shivam.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    RelativeLayout layout;
    float x = 0;
    float y = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(RelativeLayout)findViewById(R.id.layout);
        layout.addView(new CustomView(MainActivity.this));
    }

    public class CustomView extends View {

        Bitmap mBitmap;
        Paint paint;
        ArrayList < PointF> pointslist = new ArrayList < PointF > ();




        public CustomView(Context context) {
            super(context);
            mBitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(pointslist.size()<=3) {
                for (int i = 0; i < pointslist.size(); i++) {
                    canvas.drawCircle(pointslist.get(i).x, pointslist.get(i).y, 10, paint);
                    if(i==0){
                        TextView textV = new TextView(getContext());
                        textV.setGravity(Gravity.LEFT);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);

                        layoutParams.setMargins((int)pointslist.get(i).x, (int)pointslist.get(i).y, 0, 0);
                        textV.setLayoutParams(layoutParams);
                        textV.setText("A");

                        layout.addView(textV);
                    }
                    if(i==1){
                        TextView textV = new TextView(getContext());
                        textV.setGravity(Gravity.LEFT);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);

                        layoutParams.setMargins((int)pointslist.get(i).x, (int)pointslist.get(i).y, 0, 0);
                        textV.setLayoutParams(layoutParams);
                        textV.setText("B");

                        layout.addView(textV);
                    }
                    if(i==2){
                        TextView textV = new TextView(getContext());
                        textV.setGravity(Gravity.LEFT);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);

                        layoutParams.setMargins((int)pointslist.get(i).x, (int)pointslist.get(i).y, 0, 0);
                        textV.setLayoutParams(layoutParams);
                        textV.setText("C");

                        layout.addView(textV);
                    }
                }

                if(pointslist.size()==3){


                    circleFromPoints(pointslist.get(0).x,pointslist.get(0).y,pointslist.get(1).x,pointslist.get(1).y,pointslist.get(2).x,pointslist.get(2).y,canvas);
                   // canvas.drawCircle(250,250,150,paint);
                }
            }



        }

        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                x = event.getX();
                y = event.getY();

                pointslist.add(new PointF(x,y));

                invalidate();
            }
            return false;
        }

        public void circleFromPoints(Float x1, Float y1,
                                     Float x2, Float y2,
                                     Float x3, Float y3, Canvas canvas)
        {
            PointF p1 =new PointF(x1,y1);
            PointF p2 =new PointF(x2,y2);
            PointF p3 =new PointF(x3,y3);

            final double offset = Math.pow(p2.x,2) + Math.pow(p2.y,2);
            final double bc =   ( Math.pow(p1.x,2) + Math.pow(p1.y,2) - offset )/2.0;
            final double cd =   (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2))/2.0;
            final double det =  (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x)* (p1.y - p2.y);

            //if (Math.abs(det) < TOL) { throw new IllegalArgumentException("Yeah, lazy."); }

            final double idet = 1/det;

            final double centerx =  (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
            final double centery =  (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
            final double radius =
                    Math.sqrt( Math.pow(p2.x - centerx,2) + Math.pow(p2.y-centery,2));

            Paint paint=new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle((float)centerx,(float)centery, (float)radius,paint);
        }


    }

}
