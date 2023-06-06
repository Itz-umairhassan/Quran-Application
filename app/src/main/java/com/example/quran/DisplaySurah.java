package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplaySurah extends AppCompatActivity {
    Tracker tracker;
    Translations translations;
    EditText editText;
    Button button;
    TextView textView;
    String insideText;


    protected String getSurahTransaltion(int idx){
        String surah_translation="";

        int end;
        if(idx!=113)end=tracker.getSurahStart(idx+1)-1;
        else end=idx+5;


        for(int i=idx-1;i<end;i++ ){
            surah_translation+=(translations.QuranArabicText[i]);
        }

        return surah_translation;
    }

    protected void intentHandler(String[] arr,int position){
        int tempo;

        Intent intent=new Intent(DisplaySurah.this,AyatDisplay.class);
        intent.putExtra("start",Integer.parseInt(arr[0]));

        if(arr.length==2) intent.putExtra("end",Integer.parseInt(arr[1]));
        else intent.putExtra("end",Integer.parseInt(arr[0]));

        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_surah);

        tracker=new Tracker();
        translations=new Translations();
        textView=findViewById(R.id.surah_text);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editTextNumber);

        Intent obj=getIntent();

        int idx=obj.getIntExtra("position",-1);

        String surah_translation=this.getSurahTransaltion(idx);

        textView.setText(surah_translation);

        /** now attach an event listener with the button so that we can get the translation of a specific ayats or range... */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insideText=editText.getText().toString();
                if(insideText.length()<1){
                    Toast.makeText(DisplaySurah.this, "Please Enter ayat Number", Toast.LENGTH_SHORT).show();
                }else{
                    String[]arr=insideText.split(",");
                    if(arr.length>2){
                        Toast.makeText(DisplaySurah.this, "Too Much numbers", Toast.LENGTH_SHORT).show();
                    }else{
                        boolean any_thing_wrong=false;
                        int tempo;
                        try{
                            for(int i=0;i<arr.length;i++){
                                tempo=Integer.parseInt(arr[i]);
                                if(tempo<1 || tempo>=tracker.getSurahStart(idx+1)){
                                    Toast.makeText(DisplaySurah.this, "Ayat number is out of range", Toast.LENGTH_SHORT).show();
                                    any_thing_wrong=true;
                                }
                            }
                        }catch(Exception exception){
                            Toast.makeText(DisplaySurah.this, "Only Numbers are allowed", Toast.LENGTH_SHORT).show();
                            any_thing_wrong=true;
                        }
                        
                        if(!any_thing_wrong) intentHandler(arr,idx);
                    }
                }

            }
        });
    }
}