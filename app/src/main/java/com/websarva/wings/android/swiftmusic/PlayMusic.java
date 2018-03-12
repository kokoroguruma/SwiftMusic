package com.websarva.wings.android.swiftmusic;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by swift01 on 2018/03/06.
 */

public class PlayMusic extends PlayActivity{
    MediaPlayer _player = new MediaPlayer();
    String playName;

    public PlayMusic(String url) {
        String mediaFileUriStr = url;

        try {
            //  _player = new MediaPlayer();
            System.out.println(mediaFileUriStr+"プレイミュージック");
            _player.setDataSource(mediaFileUriStr);
            //_player.setOnPreparedListener(new KaijoButtonListener());
            //_player.setOnCompletionListener(new PlayComListener());     **************
            _player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pmPlaying() {
        _player.start();
    }

    public void pmStoping() {
        _player.seekTo(0);
        _player.pause();
    }

    public void pmPauseing() {
        if(_player.isPlaying()) {
            _player.pause();
        }
    }
    public void pmDestroy() {
        if (_player.isPlaying()) {
            _player.stop();
        }
        _player.release();
        _player = null;
    }
}