package com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.Elkood.ling_en4.R;

import androidx.appcompat.app.AppCompatActivity;


public class statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);
        EditText edit_voca = findViewById(R.id.edit_voca);
        EditText comp = findViewById(R.id.comp);
        EditText fulltrue = findViewById(R.id.full_true);
        EditText fullfalse = findViewById(R.id.fullfalse);

        EditText mark = findViewById(R.id.mark);
        EditText tfS = findViewById(R.id.tfsta);
        EditText abber = findViewById(R.id.abber);
        EditText number = findViewById(R.id.numberplay);
        EditText ext = findViewById(R.id.ext);
        SharedPreferences preferences = getSharedPreferences("SaveScore", MODE_PRIVATE);
        edit_voca.setText(String.valueOf(preferences.getInt("VocHighScore", 0)));
        tfS.setText(String.valueOf(preferences.getInt("tfscore", 0)));
        fulltrue.setText(String.valueOf(preferences.getInt("fullscore", 0)));
        fullfalse.setText(String.valueOf(Math.abs(421 - preferences.getInt("fullscore", 0))));
        mark.setText(String.valueOf((100 * preferences.getInt("fullscore", 0)) / 421f));
        number.setText(String.valueOf(preferences.getInt("numberPlay", 0)));
        abber.setText(String.valueOf(preferences.getInt("AbberHighScore", 0)));
        ext.setText(String.valueOf(preferences.getInt("ExtHighScore", 0)));
        comp.setText(String.valueOf(preferences.getInt("CompHighScore", 0)));
    }
}
