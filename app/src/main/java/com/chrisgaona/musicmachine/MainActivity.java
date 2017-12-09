package com.chrisgaona.musicmachine;

import android.content.Intent;
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
    private Button mDownloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final DownloadThread thread = new DownloadThread();
//        thread.setName("DownloadThread");
//        thread.start();

        mDownloadButton = findViewById(R.id.downloadButton);

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
                    Intent intent = new Intent(MainActivity.this, DownloadService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });
    }
}
