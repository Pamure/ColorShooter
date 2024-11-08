package com.example.crowshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.Random;

public class Rocket extends View {

    float x, y;
    static float stepY ;
    int upperX, upperY;
    int dst;
    Bitmap aliens;
    boolean plz;
    Paint rocketColor;
    private Context mContext;
    int randomTime,setValue;

    public Rocket(Context c, int width, int height,int iteration) {
        super(c);
        upperX = width;
        upperY = height;
        setValue=310;
        if(iteration>24){ setValue=240; }
        if(iteration>40){ setValue=190; }
        if(iteration>150){setValue=160; }
        stepY=upperY/setValue;
        dst=((width/2)-(width/4));
        mContext = c;
        rocketColor = new Paint();
        Random rocket = new Random();
        randomTime = rocket.nextInt(101);
        if (randomTime % 2 == 0) {
            aliens = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.redplane), dst, dst, false);
            rocketColor.setColor(Color.RED);
        } else {
            aliens = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.blueplane), dst, dst, false);
            rocketColor.setColor(Color.BLUE);
        }
        randomTime = rocket.nextInt(201);
        x = ((randomTime) % 2) == 0 ? (float) ((upperX / 4) - dst / 2) : (float) ((3 * upperX / 4) - dst / 2);
        y = 0;
        plz = false;
    }

    public boolean move() {
        y += stepY;
        if (y + dst > upperY) {
            return !plz;
        } else
            return plz;
    }

    public void invertRocket() {
        if (rocketColor.getColor() == Color.RED) {
            aliens = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.blueplane), dst, dst, false);
            rocketColor.setColor(Color.BLUE);
        } else if (rocketColor.getColor() == Color.BLUE) {
            aliens = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.redplane), dst, dst, false);
            rocketColor.setColor(Color.RED);
        }
    }

    public RectF getRect() {
        return new RectF(x, y, x + dst, y + dst);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(aliens, x, y, null);
    }
}


