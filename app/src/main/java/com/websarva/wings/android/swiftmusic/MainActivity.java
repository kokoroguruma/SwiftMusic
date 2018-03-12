package com.websarva.wings.android.swiftmusic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView textView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*textView = findViewById(R.id.textView);



        final EditText editText = (EditText)findViewById(R.id.edit_text);

        Button button = (Button)findViewById(R.id.main_search_b);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                if(editText.getText() != null){

                    String str = editText.getText().toString();
                    intent.putExtra("search", str);
                    editText.setText("");

                }
            }
        });*/

    }
    



    //全部ボタンの画面遷移
    public void mainAllClick(View view){
        Intent intent = new Intent(this,AllActivity.class);
        startActivity(intent);
    }

    //検索ボタンの画面遷移
    public void mainSearchClick(View view){

        final EditText editText = (EditText)findViewById(R.id.edit_text);



        Intent intent = new Intent(this, SearchActivity.class);
        if(editText.getText().length() != 0){

            String str = editText.getText().toString();
            intent.putExtra("dateStr", str);
            editText.setText("");
            startActivity(intent);

        }
        //仮データを送る
        /*intent.putExtra("dateStr2", "な");*/

    }

}
















