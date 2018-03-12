package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    MediaPlayer _player = new MediaPlayer();
    private Button _btPlay;
    private Button _btStop;
    private Button _btHead;
    private Button _btBack;
    private PlayMusic playMusic;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();  //　IntentされたURL
        url = intent.getStringExtra("URL");
        //   String url = intent.getStringExtra("url");

        // url = "https://soundeffect-lab.info/sound/environment/mp3/intersection1.mp3";  //仮URL
        System.out.println(url);

        _btHead = findViewById(R.id.play_head_b);
        _btStop = findViewById(R.id.play_stop_b);
        _btPlay = findViewById(R.id.play_play_b);
        _btBack = findViewById(R.id.play_back_b);
        playPauseClick();
        playPlayClick();
        playStopClick();
        playBackClick();
        playCreatePlayMusic(url);


    }

    public void playCreatePlayMusic(String url) { //urlを引っ張る　再生準備
        System.out.println(url);
        playMusic = new PlayMusic(url);
        //  System.out.println(url);
        _btHead.setEnabled(true);
        _btPlay.setEnabled(true);
        _btStop.setEnabled(true);


    }

    public void playPauseClick() {
        _btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic.pmPauseing();
            }
        });
    }

    public void playBackClick() { 

        _btBack.setOnClickListener(new View.OnClickListener()
                        {
            @Override
            public void onClick(View view) {
                if(AllActivity.mflag != null){
                    AllActivity.mflag.setFlag(true);

                }
                if (SearchActivity.mflag != null){
                    SearchActivity.mflag.setFlag(true);
                }
                finish();
            }
        });
    }

    public void playStopClick() { //　先頭ボタンメソッド
        _btHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic.pmStoping();
            }
        });
    }

    public void playPlayClick() { //　再生ボタンメソッド
        _btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic.pmPlaying();
            }
        });
    }

    @Override
    protected void onDestroy() { //　アクティビティ終了破壊　メソッド
        super.onDestroy();
        playMusic.pmDestroy();
    }
}
