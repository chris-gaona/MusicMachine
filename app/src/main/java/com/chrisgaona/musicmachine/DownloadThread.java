package com.chrisgaona.musicmachine;

import android.util.Log;

/**
 * Created by chrisgaona on 12/9/17.
 */

public class DownloadThread extends Thread {
    private static final String TAG = DownloadThread.class.getSimpleName();

    @Override
    public void run() {
        for (String song : Playlist.songs) {
            downloadSong();
        }
    }

    private void downloadSong() {
        // return difference in milliseconds between current time and epoch
        long endTime = System.currentTimeMillis() + 10 * 1000;

        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "Song downloaded!");
    }
}
