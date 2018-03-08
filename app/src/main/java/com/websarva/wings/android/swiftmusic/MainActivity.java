package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //private void TextView editView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //全部ボタンの画面遷移
    public void mainAllClick(View view){
        Intent intent = new Intent(this,AllActivity.class);
        startActivity(intent);
    }

    //検索ボタンの画面遷移
    public void mainSearchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        //仮データを送る
        intent.putExtra("dateDayo", 1000);
        startActivity(intent);
    }

}
