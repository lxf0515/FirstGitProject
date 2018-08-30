package com.took.firstgit.ui.auto;

import android.os.Handler;

import java.util.TimerTask;

public class BannerTimerTask extends TimerTask {
    public static final int AUTOBANNER_CODE = 0X1001;
    /**
     * handler
     */
    Handler handler;

    public BannerTimerTask(Handler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(AUTOBANNER_CODE);
    }
}
