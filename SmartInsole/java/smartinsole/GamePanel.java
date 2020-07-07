package com.projects.saaasaab.smartinsole;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Scott on 9/25/2017 on 4:51 PM
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    private MainThread thread;
    private Random rand = new Random();

    public GamePanel(Context context) {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
        thread = new MainThread(getHolder(), this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;
        while (retry && counter < 1000) {
            counter++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.file000));
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {

            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update() {

    }

    public boolean collision(GameObject a, GameObject b) {
        if (Rect.intersects(a.getRectangle(), b.getRectangle())) {
            return true;
        }
        return false;

    }

    @Override
    public void draw(Canvas canvas) {
        final float scaleFactorX = getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);
        float[] mags = {MainActivity.s1, MainActivity.s2, MainActivity.s3};

        double[][] vs = new double[3][2];
        float width = Constants.SCREEN_WIDTH;
        float height = Constants.SCREEN_HEIGHT;
        vs[0][0] = width / 4;
        vs[0][1] = height / 4;

        vs[1][0] = width * 3.0 / 4.0;
        vs[1][1] = height / 4.0;

        vs[2][0] = width / 2.0;
        vs[2][1] = height * 3.0 / 4.0;


        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            Paint paint = new Paint();
            Color color = new Color();


            double max = 0;
            // Draw the shape
            int blockSize = 10;
            for (int i = 0; i < width; i += blockSize) {
                for (int j = 0; j < height; j += blockSize) {
                    double sum = 0;
                    double offset = 100;

                    double dis =
                            mags[0] / (Math.pow(i - vs[0][0], 2) + Math.pow(j - vs[0][1], 2) + offset) +
                                    mags[1] / (Math.pow(i - vs[1][0], 2) + Math.pow(j - vs[1][1], 2) + offset) +
                                    mags[2] / (Math.pow(i - vs[2][0], 2) + Math.pow(j - vs[2][1], 2) + offset);

                    if (max < dis) {
                        max = dis;
                    }
                    float H = Integer.valueOf((int) map(dis, 0, .015, 255, 0));
                    float S = 100;
                    float V = 100;


                    int rgb = color.HSVToColor(new float[]{H, S, V});

                    paint.setColor(rgb);

                    canvas.drawRect(i, j, i + blockSize, j + blockSize, paint);
                }

                canvas.restoreToCount(savedState);
            }
        }


    }

    double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

}