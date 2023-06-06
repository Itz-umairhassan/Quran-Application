package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AyatDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat_display);

        Intent obj=getIntent();

        Toast.makeText(this, "Starting from "+obj.getIntExtra("start",-1)+" and end at "+obj.getIntExtra("end",-1), Toast.LENGTH_SHORT).show();
    }
}