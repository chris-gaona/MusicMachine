package com.chrisgaona.musicmachine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_SONG = "SONG";
    private boolean mBound = false;
    private PlayerService mPlayerService;
    private Button mDownloadButton;
    private Button mPlayButton;

    // using an anonymous class for ServiceConnection
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            mBound = true;
            // creating a separate process
            // messenger is a reference to a handler
            // messenger can be tranformed to a Binder
            PlayerService.LocalBinder localBinder = (PlayerService.LocalBinder) binder;
            mPlayerService = localBinder.getService();

            if (mPlayerService.isPlaying()) {
                mPlayButton.setText("Pause");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final DownloadThread thread = new DownloadThread();
//        thread.setName("DownloadThread");
//        thread.start();

        mDownloadButton = findViewById(R.id.downloadButton);
        mPlayButton = findViewById(R.id.playButton);

        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT).show();

                // send messages to Handler for processing
                for (String song : Playlist.songs) {
//                    // android keeps a pool of message objects for us to use
//                    Message message = Message.obtain();
//                    // add any type of object to message
//                    // send message to handler
//                    message.obj = song;
//                    thread.mHandler.sendMessage(message);

                    // start service for each song
                    Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound) {
                    if (mPlayerService.isPlaying()) {
                        mPlayerService.pause();
                        mPlayButton.setText("Play");
                    } else {
                        Intent intent = new Intent(MainActivity.this, PlayerService.class);
                        startService(intent);
                        mPlayerService.play();
                        mPlayButton.setText("Pause");
                    }
                }
            }
        });


    }
}
