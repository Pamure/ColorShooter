package com.pam.minds.crashplanes;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class shooter extends View {

    public Paint paint, paint2, paint3;
    Point center;
    int left, right, cW, cH, i = 0, shootPoint, top, circlex, circleX;
    public shooter(int color, Context c) {
        super(c);
        paint = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        paint.setColor(color);
        paint2.setColor(Color.WHITE);
        paint3.setColor(Color.GRAY);
    }

    public void move() {
        if (left == 0) {
            left = center.x;
            right = cW;
            shootPoint = left + (left / 2);
            circleX = left + left / 4;
            circlex = left + (3 * left) / 4;
        } else {
            left = 0;
            right = center.x;
            shootPoint = right / 2;
            circleX = shootPoint / 2;
            circlex = circleX + shootPoint;
        }
        invalidate();
    }

    public float getPosition() {
        return shootPoint;
    }

    public int shooterY() {
        return  top;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        cW = getWidth();
        cH = getHeight();
        center = new Point(cW / 2, cH);
        if (i == 0) {
            left = 0;right = center.x;shootPoint = right / 2;i++;circleX = shootPoint / 2;circlex = circleX + shootPoint;
        }
        int rH = 100, bottom = center.y + (rH / 2);
        top = center.y - (rH / 2);
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(shootPoint, top + 5, cW / 4, paint);
        canvas.drawRect(left, top, right, bottom, paint);
        canvas.drawCircle(circleX, top - 25, cW/16, paint2);
        canvas.drawCircle(circlex, top - 25, cW/16, paint2);
        canvas.drawCircle(circleX, top - 25, cW/30, paint3);
        canvas.drawCircle(circlex, top - 25, cW/30, paint3);
        canvas.drawRect(shootPoint - 4, top - 30, shootPoint + 4, top, paint3);

    }
}