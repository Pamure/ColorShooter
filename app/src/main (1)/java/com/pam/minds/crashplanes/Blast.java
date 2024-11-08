package com.pam.minds.crashplanes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Blast {
    private float x;
    private float y;
    private int count = 15;
    private static Bitmap blast;

    public Blast(Context c, float loc_x, float loc_y) {
        blast = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(c.getResources(), R.drawable.explosion), (int) (2*Galileans.radius), (int)  (2*Galileans.radius), false);
        x = loc_x;
        y = loc_y;

    }

    public boolean draw(Canvas canvas) {

        if (count-- == 0)
            return false;
        else {

            canvas.drawBitmap(blast, x, y, null);
            return true;
        }
    }
}
