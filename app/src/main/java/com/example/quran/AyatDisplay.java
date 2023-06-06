package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AyatDisplay extends AppCompatActivity {

    TextView textView;
    Tracker tracker;
    Translations translations;

    protected String getTransaltion(int position,int start,int end){
        String transaltion="";
        if(start==end) return translations.QuranArabicText[start+position-1];

        for(int i=start;i<=end;i++) {
            transaltion+=(translations.QuranArabicText[position+i-1]);
        }
        return transaltion;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat_display);

        textView=findViewById(R.id.ayah_text);
        tracker=new Tracker();
        translations=new Translations();
        Intent obj=getIntent();

        int idx=tracker.getSurahStart(obj.getIntExtra("position",-1));
        int start= obj.getIntExtra("start",-1);
        int end=obj.getIntExtra("end",-1);

        try {

            String ttext = "";
            if (start == end) ttext = translations.QuranArabicText[start + idx - 1];
            else {
                for (int i = start; i <= end; i++) {
                    ttext = ttext + translations.QuranArabicText[i + idx - 1];
                }
            }

            textView.setText(ttext);

        }catch(Exception e){
            textView.setText(e.getMessage());
        }


       //Toast.makeText(this, "Starting from "+obj.getIntExtra("start",-1)+" and end at "+obj.getIntExtra("end",-1), Toast.LENGTH_SHORT).show();
    }
}