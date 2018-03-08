package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class AllActivity extends AppCompatActivity {

    public static FinishFlag mflag;

    SwiftMusicApplication put;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        put = (SwiftMusicApplication) getApplication();

        //全てのデータを取得
        List<String> dateList = put.allsearch("");
        //データが取れているかを確認
        for (String s : dateList) {
            System.out.println(s);
        }

        ListView lvSound = (ListView)findViewById(R.id.allNameList);

        ListAdapter adapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, dateList);

        lvSound.setAdapter(adapter);

       // lvSound.setOnItemClickListener(new allNameClick());

    }

    //名前をクリックした時に画面遷移
    private class allNameClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id){
            Intent intent = new Intent(AllActivity.this, PlayActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if(mflag.isFlag()){
            finish();
        }
    }



}
