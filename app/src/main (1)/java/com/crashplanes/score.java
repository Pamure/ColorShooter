package com.pam.minds.crashplanes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class score{
    private Paint score_paint,add_score;
    private String score_now;
    static private String add_now="";
    static int count;
    score() {
        score_paint = new Paint();
        score_now="0";
        add_score=new Paint();
        count=0;
    }
    void setScore(int score)
    {
        score_now=score+"";
    }
    static void setAdd_now(int toBeAdd)
    {
        count=30;
        add_now="+"+toBeAdd;
    }
    void draw(Canvas canvas)
    {

        score_paint.setAntiAlias(true);
        score_paint.setStyle(Paint.Style.FILL);
        score_paint.setColor(Color.WHITE);
        score_paint.setTextSize(55);
        canvas.drawText(score_now, 60, 60, score_paint);
        if(count--<=0) return;
        else
            {
                add_score.setAntiAlias(true);
                add_score.setStyle(Paint.Style.FILL);
                add_score.setColor(Color.WHITE);
                add_score.setTextSize(45);
                canvas.drawText(add_now,140,56,add_score);
            }
    }

}
