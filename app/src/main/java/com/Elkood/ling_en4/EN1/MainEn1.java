package com.Elkood.ling_en4.EN1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.Elkood.ling_en4.R;

public class MainEn1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_en1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
