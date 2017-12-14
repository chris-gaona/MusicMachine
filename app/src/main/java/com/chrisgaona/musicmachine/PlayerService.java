package com.chrisgaona.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chrisgaona on 12/12/17.
 */

public class PlayerService extends Service {
    private static final String TAG = PlayerService.class.getSimpleName();
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        mMediaPlayer = MediaPlayer.create(this, R.raw.jingle);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        // frees up resources for other apps when we're done
        mMediaPlayer.release();
    }


    // Client methods
    public void play() {
        mMediaPlayer.start();
    }

    public void pause() {
        mMediaPlayer.pause();
    }
}
