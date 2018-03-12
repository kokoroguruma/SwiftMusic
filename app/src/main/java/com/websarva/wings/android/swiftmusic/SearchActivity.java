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

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static FinishFlag mflag = new FinishFlag();

    SwiftMusicApplication put;

    //検索文字で指定されたデータを取得
    List<String> dateList;

    //PlayActivityに送るURLを格納
    String dateUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        put = (SwiftMusicApplication)getApplication();

        //dateDayoにMainActivityからのデータを格納
        //左下の実行ボタンを押すと、ログの中にデータを確認できる
        Intent intent = this.getIntent();
        String dateStr = intent.getStringExtra("dateStr");
        Log.d(this.getLocalClassName(), "dateStr：" + dateStr);

        //データ取得
        //""の中に文字が格納された変数を入れると、その条件により検索
        dateList = put.allsearch(dateStr);

        //データが取れているかを確認
        for(String s : dateList){
            System.out.println(s);
        }

        ListView lvSound = (ListView)findViewById(R.id.searchList);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dateList);

        lvSound.setAdapter(adapter);

        lvSound.setOnItemClickListener(new SearchActivity.searchNameClick());

        //リスト上でクリックされた名前に、付随したデータを取得
        lvSound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //クリックされたものを取得
                String get_parent = (String) parent.getClass().getSimpleName();
                String get_position = String.valueOf(position);
                dateList.get(position);
                String get_id = String.valueOf(id);
                //log出力
                Log.v("tag", String.format("onItemClick: %s", get_parent));
                Log.v("tag", String.format("onItemClick: %s", get_position));
                Log.v("tag", String.format("onItemClick: %s", dateList.get(position)));
                Log.v("tag", String.format("onItemClick: %s", get_id));

                //mp3を取得
                //urlメソッドを使っている
                dateUrl = put.url(dateList.get(position));
                Log.v("tag", String.format("onItemClick: %s", dateUrl));

                //URLデータを保持しつつ、PlayActivityに画面遷移
                Intent intent = new Intent(SearchActivity.this, PlayActivity.class);
                intent.putExtra("URL", dateUrl);
                startActivity(intent);
            }
        });


    }

    //名前をクリックされた時に画面遷移
    private class searchNameClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id){
            Intent intent = new Intent(SearchActivity.this, PlayActivity.class);
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
