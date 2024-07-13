package com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.Constants.En4.En4R2016_3Constants;
import com.Elkood.ling_en4.R;


public class R2016_3 extends AppCompatActivity {
    private final En4R2016_3Constants en4R2016_3Constants = new En4R2016_3Constants();
    ViewGroup progressBar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2016_3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        progressBar = findViewById(R.id.pro2016_3);
        recyclerView = findViewById(R.id.recycle_view2016_3);
        Adapter_Quiz adapter_home = new Adapter_Quiz(en4R2016_3Constants.list, R2016_3.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}

