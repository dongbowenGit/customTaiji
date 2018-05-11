package com.example.administrator.zihuikongjian.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;

/**
 * Created by Administrator on 2018.05.11.
 */

public class MyView extends View{

    private Paint myPaint;
    private float currentX=0;
    private float currentY=0;
    //大圆半径
    private float radiusBig = 200;
    //中圆半径
    private float radiusCenter = radiusBig / 2;
    //小圆半径
    private float radiusSmall = radiusCenter / 3;
    //边距（主要作用是画黑色大圆的时候，为了达到有边框的效果，半径需要比原来的大一点）
    private int padding = 8;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        myPaint = new Paint();
        myPaint.setAntiAlias(true);
        //省略其余代码
        //setAnimation();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //通过MeasureSpec拿到X轴
        currentX=MeasureSpec.getSize(widthMeasureSpec);
        //通过MeasureSpec拿到Y轴
        currentY=MeasureSpec.getSize(heightMeasureSpec);
        //测绘
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画黑色大圆
        drawBG(canvas);
        //画左边白色半圆
        drawLeftHalfCirle(canvas);
        //画上下的四个圆
        drawTBCirle(canvas);
    }

    private void drawTBCirle(Canvas canvas) {
        //画上面的白中圆
        myPaint.setColor(Color.WHITE);
        canvas.drawCircle(currentX, currentY - radiusBig / 2, radiusCenter, myPaint);
        //画上面的黑小圆
        myPaint.setColor(Color.BLACK);
        canvas.drawCircle(currentX, currentY - radiusBig / 2, radiusSmall, myPaint);
        //画下面的黑中圆
        myPaint.setColor(Color.BLACK);
        canvas.drawCircle(currentX, currentY + radiusBig / 2, radiusCenter, myPaint);
        //画下面的白小圆
        myPaint.setColor(Color.WHITE);
        canvas.drawCircle(currentX, currentY + radiusBig / 2, radiusSmall, myPaint);
    }

    private void drawLeftHalfCirle(Canvas canvas) {
        myPaint.setColor(Color.WHITE);
        //从90度开始画，画180度
        canvas.drawArc(new RectF(currentX-radiusBig,currentY-radiusBig,currentX+radiusBig,currentY+radiusBig),90,180,true,myPaint);

    }

    private void drawBG(Canvas canvas) {
        //设置背景颜色为黑色
        myPaint.setColor(Color.BLACK);
        //这里加padding才有边框效果
        canvas.drawCircle(currentX,currentY,radiusBig+padding,myPaint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();//获取当前手指所在x轴
        currentY = event.getY();//获取当前手指所在y轴
        invalidate();//立刻重绘
        return true;//返回true表示不通知父控件处理，自己处理
    }
    private void setAnimation() {
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, ScaleAnimation.RELATIVE_TO_PARENT, 0.5f, ScaleAnimation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation2.setDuration(500);// 设置持续时间
        scaleAnimation2.setRepeatCount(99999);// 设置重复次数
        scaleAnimation2.setFillAfter(true);// 保持动画结束时的状态
        startAnimation(scaleAnimation2);
    }
}
