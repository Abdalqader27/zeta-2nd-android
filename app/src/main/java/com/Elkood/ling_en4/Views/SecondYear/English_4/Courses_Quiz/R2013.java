package com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.Constants.En4.En4R2013Constants;
import com.Elkood.ling_en4.R;


public class R2013 extends AppCompatActivity {
     ViewGroup progressBar;

     RecyclerView recyclerView;
    final En4R2013Constants en4R2013Constants = new En4R2013Constants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2013);
        progressBar = findViewById(R.id.pro2013);
        recyclerView = findViewById(R.id.recycle_view2013);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Adapter_Quiz adapter_home = new Adapter_Quiz(en4R2013Constants.list, R2013.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}
