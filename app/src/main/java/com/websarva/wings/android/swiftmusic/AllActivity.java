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

    public static FinishFlag mflag = new FinishFlag();

    SwiftMusicApplication put;

    //全てのデータを取得
    List<String> dateList;

    String dateUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        allNameGet();
        allList();
        allNameClick();

    }

    /**
     * データベースから名前のリストデータを受け取る
     */
    public void allNameGet(){
        //グローバル変数
        //どのActivityからでも参照できる、グローバルな変数。getApplication()を使って参照する。
        put = (SwiftMusicApplication) getApplication();

        //全てのデータを取得
        //""の中に文字を入れると、その条件で指定して検索
        dateList = put.allsearch("");

        //データが取れているかを確認
        for (String s : dateList) {
            System.out.println(s);
        }
    }

    /**
     * 受け取ったデータをリスト化して表示
     */
    public void allList(){
        ListView lvSound = (ListView)findViewById(R.id.allNameList);

        ListAdapter adapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, dateList);

        lvSound.setAdapter(adapter);

    }

    /**
     * 名前をクリックされたらDBに名前を送り、戻り値としてURLを貰う
     */
    public void allNameClick(){

        ListView lvSound = (ListView)findViewById(R.id.allNameList);

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

                allUrlIntent();


            }
        });
    }


    /**
     * URLをintentで引き渡す
     */
    public void allUrlIntent(){
                Intent intent = new Intent(AllActivity.this, PlayActivity.class);
                System.out.println(dateUrl);
                intent.putExtra("URL", dateUrl);
                startActivity(intent);
    }


    /**
     * PlayActivityで戻るボタンが押されたら、このメソッドを使って、
     * AllActivityを終了させる
     */
    @Override
    public void onRestart(){
        super.onRestart();
        if(mflag.isFlag()){
            finish();
        }
    }

}
