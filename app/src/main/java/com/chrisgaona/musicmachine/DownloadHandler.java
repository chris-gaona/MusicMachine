package com.chrisgaona.musicmachine;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by chrisgaona on 12/9/17.
 */

public class DownloadHandler extends Handler {
    private static final String TAG = DownloadHandler.class.getSimpleName();

    @Override
    public void handleMessage(Message msg) {
        downloadSong(msg.obj.toString());
    }

    private void downloadSong(String song) {
        // return difference in milliseconds between current time and epoch
        long endTime = System.currentTimeMillis() + 10 * 1000;

        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, song + " downloaded!");
    }
}
