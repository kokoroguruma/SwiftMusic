package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        ListView lvSound = (ListView)findViewById(R.id.allNameList);

        lvSound.setOnItemClickListener(new allNameClick());

    }

    private class allNameClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id){

            Intent intent = new Intent(AllActivity.this, PlayActivity.class);
            startActivity(intent);

        }
    }
}
