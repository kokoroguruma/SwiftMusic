package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    private String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();

        Url = intent.getStringExtra("URL");

        //一時的に表示
        TextView textView = findViewById(R.id.textView);
        textView.setText(Url);

    }

    //戻るボタンによる画面遷移
    public void playBackClick(View view){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }



}
