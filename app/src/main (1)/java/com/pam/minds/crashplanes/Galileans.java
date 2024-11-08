package com.pam.minds.crashplanes;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class Galileans extends View {
    static float radius = 45;
    float x;
    float y;
    float stepY = 5;
    Paint paint;

    public Galileans(int color, Context c, float startx, float starty) {
        super(c);
        paint = new Paint();
        paint.setColor(color);
        x = startx;
        y = starty;
    }
    public static void setRadius(int width)
    {
        radius=(((width/2)-(width/4))/2)-5;
    }
    public int shootX(){return (int)x;}
    public int shootY(){ return (int)y;}
    public RectF getRect() {
        return new RectF(x-radius,y-radius,x+radius,y+radius);
    }
    public boolean move() {
        y -= stepY;
        return !(y - radius < 0);
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }
}
