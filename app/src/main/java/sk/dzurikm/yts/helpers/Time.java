package sk.dzurikm.yts.helpers;

import android.os.Handler;

import sk.dzurikm.yts.models.callbacks.Callback;

public class Time {
    private static final int DEFAULT_TIME_DURATION = 500;
    public static void wait(Callback callback, int duration){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.callback();
            }
        }, duration);
    }

    public static void wait(Callback callback){
        wait(callback, DEFAULT_TIME_DURATION);
    }
}
