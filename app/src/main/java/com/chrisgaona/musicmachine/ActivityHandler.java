package com.chrisgaona.musicmachine;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

/**
 * Created by chrisgaona on 12/15/17.
 */

public class ActivityHandler extends Handler {
    private MainActivity mMainActivity;

    public ActivityHandler(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.arg1 == 0) {
            // music is NOT playing
            if (msg.arg2 == 1) {
                mMainActivity.changePlayButtonText("Play");

            } else {
                // play the music
                Message message = Message.obtain();
                message.arg1 = 0;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                // change play button to say "Pause"
                mMainActivity.changePlayButtonText("Pause");
            }

        } else if (msg.arg1 == 1) {
            // music is playing
            if (msg.arg2 == 1) {
                mMainActivity.changePlayButtonText("Pause");
            } else {
                // pause the music
                Message message = Message.obtain();
                message.arg1 = 1;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                // change play button to say "Play"
                mMainActivity.changePlayButtonText("Play");
            }
        }
    }
}
