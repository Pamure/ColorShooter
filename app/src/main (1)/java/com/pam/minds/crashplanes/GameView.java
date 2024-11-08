package com.pam.minds.crashplanes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Build;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.VIBRATOR_SERVICE;

public class GameView extends FrameLayout implements View.OnTouchListener {
    private int width, height, indo;
    ArrayList<Rocket> aliens;
    Context mContext;
    ArrayList<Galileans> bullets;
    ArrayList<Blast> explosions;
    shooter cannon;
    score haisiyat;
    int startTime, use, iteration;
    boolean check;
    static int score;
    Activity toFinish;
    Vibrator reached_end;

    @SuppressLint("ClickableViewAccessibility")
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        reached_end = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        iteration = score = indo = 0;
        check = true;
        toFinish = (Activity) getContext();
        startTime = (int) (SystemClock.elapsedRealtime());
        mContext = context;
        setWillNotDraw(false);
        cannon = new shooter(Color.RED, mContext);
        bullets = new ArrayList<>();
        explosions = new ArrayList<>();
        aliens = new ArrayList<>();
        haisiyat = new score();
        addView(cannon);
        cannon.setOnTouchListener(this);
        for (int i = bullets.size() - 1; i >= 0; i--) {
            addView(bullets.get(i));
            bullets.get(i).setOnTouchListener(this);
        }
        for (int i = aliens.size() - 1; i >= 0; i--) {
            addView(aliens.get(i));
            aliens.get(i).setOnTouchListener(this);
        }
        attackRocket();
        use = 1;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                if ((event.getX() - bullets.get(i).shootX()) <= Galileans.radius && (event.getX() - bullets.get(i).shootX()) >= -Galileans.radius && (event.getY() - bullets.get(i).shootY()) <= Galileans.radius && (event.getY() - bullets.get(i).shootY()) >= -Galileans.radius) {
                    bullets.remove(i);
                    return true;

                }
            }
            if ((event.getX() - cannon.getPosition()) <= (cannon.right - cannon.left) && (event.getX() - cannon.getPosition()) >= -(cannon.right - cannon.left) && (event.getY() - cannon.shooterY()) <= 150 && (event.getY() - cannon.shooterY()) >= -150) {
                cannon.move();
                cannon.invalidate();
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                for (int i = aliens.size() - 1; i >= 0; i--) {
                    if ((event.getX() - aliens.get(i).getX()) <= aliens.get(i).dst && (event.getX() - aliens.get(i).getX()) >= -aliens.get(i).dst && (event.getY() - aliens.get(i).getY()) <= aliens.get(i).dst && (event.getY() - aliens.get(i).getY()) >= -aliens.get(i).dst) {
                        aliens.get(i).invertRocket();
                        aliens.get(i).invalidate();
                        return true;

                    }
                }
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int time = (int) (SystemClock.elapsedRealtime() - startTime);
        int time2 = (int) (SystemClock.elapsedRealtime());
        if ((time / 3600) % 2 == 0) {    //True for 0-5999, false for 6000-11999, true for 12000-17999,
            if (cannon.paint.getColor() != Color.BLUE) {
                cannon.paint.setColor(Color.BLUE);
                cannon.invalidate();
            }
        } else {
            if (cannon.paint.getColor() != Color.RED) {
                cannon.paint.setColor(Color.RED);
                cannon.invalidate();
            }
        }
        if (iteration > 15) {
            use = 2;
        }
        if (iteration > 40) {
            use = 3;
        }
        if (time2 % 120 == 0) {
            attackRocket();
        }


        drawGameBoard(canvas);

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Galileans.setRadius(width);
    }

    public void drawGameBoard(Canvas canvas) {
        cannon.draw(canvas);
        haisiyat.setScore(score);
        haisiyat.draw(canvas);
        for (int i = bullets.size() - 1; i >= 0; i--) {
            if (bullets.get(i) != null) {
                bullets.get(i).draw(canvas);
                if (!bullets.get(i).move()) {
                    bullets.remove(i);
                }
            }
        }
        for (int i = explosions.size() - 1; i >= 0; i--) {
            if (explosions.get(i) != null) {
                if (!explosions.get(i).draw(canvas)) {
                    explosions.remove(i);
                }
            }
        }
        for (int i = aliens.size() - 1; i >= 0; i--) {
            if (aliens.get(i) != null) {
                aliens.get(i).draw(canvas);
                RectF guyRect = aliens.get(i).getRect();
                for (int z = bullets.size() - 1; z >= 0; z--) {
                    if (RectF.intersects(guyRect, bullets.get(z).getRect())) {
                        if (bullets.get(z).paint.getColor() != aliens.get(i).rocketColor.getColor()) {
                            gameStop();
                        }
                        explosions.add(new Blast(mContext, aliens.get(i).getX(), aliens.get(i).getY()));
                        haisiyat.setAdd_now(5 * use);
                        aliens.get(i).plz = true;
                        check = false;
                        bullets.remove(z);
                        iteration++;
                        score += 5 * use;
                        break;
                    }
                }
                if (aliens.get(i).move()) {
                    aliens.remove(i);
                    attackRocket();
                    if (check) {
                        indo++;
                        vibrate(100 * indo);
                        if (indo > 2) {
                            gameStop();
                        }
                    }
                    check = true;
                }
            }
        }
    }

    public void shootCannon() {
        bullets.add(new Galileans(cannon.paint.getColor(), mContext, cannon.getPosition(), (float) (height - 160)));
    }

    public void gameStop() {
        Intent hello = new Intent(mContext, GameOver.class);
        hello.putExtra("key", score);
        mContext.startActivity(hello);
        toFinish.finish();
    }

    public void vibrate(int mili) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            reached_end.vibrate(VibrationEffect.createOneShot(mili, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            reached_end.vibrate(mili);

        }
    }

    public void attackRocket() {
        if (aliens.size() < use) {
            aliens.add(new Rocket(mContext, width, height, iteration));
        }
        iteration++;
    }
}