package com.saaasaab.scott.spotter;

import android.content.Context;

/**
 * Created by Scott on 9/17/2017 on 12:04 AM
 */

public class Chronometer implements Runnable{

    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;
    public long since;
    private Context mContext;
    private long mStartTime;
    private boolean mIsRunning;


    public Chronometer(Context mContext) {
        this.mContext = mContext;
    }

    public void start(){
        mStartTime = NewWorkoutScreen.mStartTime;
        mIsRunning = true;
    }


    public void stop(){
        mIsRunning = false;
    }
    @Override
    public void run() {
        while(mIsRunning){

            since = System.currentTimeMillis() - mStartTime;
            int seconds =(int)((since / 1000) %  60);
            int minutes = (int)(((since / MILLIS_TO_MINUTES))% 60);
            int hours = (int)((since/MILLIS_TO_HOURS)%24);
            int millis = (int) since % 1000;

            ( (NewWorkoutScreen) mContext).updateTimerText(String.format(
                  "%02d:%02d:%02d",hours,minutes,seconds
            ));
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }
}
