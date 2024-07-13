package com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.Constants.En4.En4R2017Constants;
import com.Elkood.ling_en4.R;


public class r2017 extends AppCompatActivity {
    private final En4R2017Constants en4R2017Constants = new En4R2017Constants();
     ViewGroup progressBar;
     RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2017);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        progressBar = findViewById(R.id.pro2017);
        recyclerView = findViewById(R.id.recycle_view2017);

        Adapter_Quiz adapter_home = new Adapter_Quiz(en4R2017Constants.list, r2017.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);


    }

}
