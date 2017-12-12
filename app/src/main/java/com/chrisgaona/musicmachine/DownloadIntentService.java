package com.chrisgaona.musicmachine;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chrisgaona on 12/12/17.
 */

public class DownloadIntentService extends IntentService {
    // no longer need DownloadService / DownloadThread / DownloadHandler while
    // using DownloadIntentService

    private static final String TAG = DownloadIntentService.class.getSimpleName();

    public DownloadIntentService() {
        super("DownloadIntentService");
        // by default an intent service will use start sticky
        // so we're specifying the redelivery one
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String song = intent.getStringExtra(MainActivity.KEY_SONG);
        downloadSong(song);
    }

    private void downloadSong(String song) {
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
