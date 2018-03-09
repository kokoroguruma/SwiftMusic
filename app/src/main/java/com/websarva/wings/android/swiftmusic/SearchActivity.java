package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    public static FinishFlag mflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView lvSound = (ListView)findViewById(R.id.searchList);

        lvSound.setOnItemClickListener(new SearchActivity.searchNameClick());

        //dateDayoにMainActivityからのデータを格納
        //左下の実行ボタンを押すと、ログの中にデータを確認できる
        Intent intent = this.getIntent();
        int dateDayo = intent.getIntExtra("dateDayo", 0);
        Log.d(this.getLocalClassName(), "dateDayo：" + dateDayo);
    }

    //名前をクリックされた時に画面遷移
    private class searchNameClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id){
            Intent intent = new Intent(SearchActivity.this, PlayActivity.class);
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
