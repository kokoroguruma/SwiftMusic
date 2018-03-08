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

    //全てのデータを取得
    List<String> dateList;

    String dateUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        put = (SwiftMusicApplication) getApplication();

        //全てのデータを取得
        dateList = put.allsearch("");

        //データが取れているかを確認
        for (String s : dateList) {
            System.out.println(s);
        }

        ListView lvSound = (ListView)findViewById(R.id.allNameList);

        ListAdapter adapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, dateList);

        lvSound.setAdapter(adapter);

        lvSound.setOnItemClickListener(new allNameClick());

        lvSound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //クリックされたものを取得
                String get_parent = (String) parent.getClass().getSimpleName();
                String get_position = String.valueOf(position);
                dateList.get(position);
                String get_id = String.valueOf(id);
                //Log出力
                Log.v("tag", String.format("onItemClick: %s", get_parent));
                Log.v("tag", String.format("onItemClick: %s", get_position));
                Log.v("tag", String.format("onItemClick: %s", dateList.get(position)));
                Log.v("tag", String.format("onItemClick: %s", get_id));

                //mp3を取得
                dateUrl = put.url(dateList.get(position));
                Log.v("tag", String.format("onItemClick: %s", dateUrl));

                Intent intent = new Intent(AllActivity.this, PlayActivity.class);
                intent.putExtra("URL", dateUrl);
                startActivity(intent);

            }
        });

    }

    //名前をクリックした時に画面遷移
    private class allNameClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id){
            Intent intent = new Intent(AllActivity.this, PlayActivity.class);
            intent.putExtra("URL:", dateUrl);
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
