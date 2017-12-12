package com.chrisgaona.musicmachine;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by chrisgaona on 12/9/17.
 */

public class DownloadHandler extends Handler {
    private static final String TAG = DownloadHandler.class.getSimpleName();
    private DownloadService mService;

    @Override
    public void handleMessage(Message msg) {
//        downloadSong(msg.obj.toString());
        // stop service after song is downloaded
        // passes in msg.arg1 to make sure all startId's are handled before we stop service
        mService.stopSelf(msg.arg1);
    }

    public void setService(DownloadService service) {
        mService = service;
    }
}
