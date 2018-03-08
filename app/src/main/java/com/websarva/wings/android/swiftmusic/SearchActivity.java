package com.websarva.wings.android.swiftmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView lvSound = (ListView)findViewById(R.id.searchList);

        lvSound.setOnItemClickListener(new SearchActivity.searchNameClick());
    }

    private class searchNameClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id){

            Intent intent = new Intent(SearchActivity.this, PlayActivity.class);
            startActivity(intent);

        }
    }

}
