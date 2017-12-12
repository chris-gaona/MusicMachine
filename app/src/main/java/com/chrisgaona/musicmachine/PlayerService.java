package com.chrisgaona.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by chrisgaona on 12/12/17.
 */

public class PlayerService extends Service {
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.jingle);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
