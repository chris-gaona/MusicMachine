package com.chrisgaona.musicmachine;

import android.os.Looper;
import android.util.Log;

/**
 * Created by chrisgaona on 12/9/17.
 */

public class DownloadThread extends Thread {
    private static final String TAG = DownloadThread.class.getSimpleName();
    public DownloadHandler mHandler;

    @Override
    public void run() {
        // creates looper for thread and creates message queue
        Looper.prepare();
        // initialize handler
        // by default handler is associated with looper for current thread
        mHandler = new DownloadHandler();
        // starts looping over the message queue
        Looper.loop();
    }
}
