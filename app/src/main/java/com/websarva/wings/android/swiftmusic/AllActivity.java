package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class AllActivity extends AppCompatActivity {

    public static FinishFlag mflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        ListView lvSound = (ListView)findViewById(R.id.allNameList);

        lvSound.setOnItemClickListener(new allNameClick());

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
