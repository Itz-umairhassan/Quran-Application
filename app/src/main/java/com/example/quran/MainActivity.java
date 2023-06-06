package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tracker=new Tracker();
        ArrayList<String>surah_names=tracker.GetEnglishSurahNames();

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,surah_names);
        listview=findViewById(R.id.listView);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "position "+i+" is clicked now ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,DisplaySurah.class);
                intent.putExtra("position",i);
                startActivity(intent);

            }
        });

    }
}