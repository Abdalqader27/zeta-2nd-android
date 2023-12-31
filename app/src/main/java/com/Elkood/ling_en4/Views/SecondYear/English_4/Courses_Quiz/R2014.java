package com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.Constants.En4.En4R2014Constants;
import com.Elkood.ling_en4.R;

import butterknife.BindView;

public class R2014 extends AppCompatActivity {
    @BindView(R.id.pro2014)
    ViewGroup progressBar;
    @BindView(R.id.recycle_view2014)
    RecyclerView recyclerView;
    final private En4R2014Constants en4R2014Constants = new En4R2014Constants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2014);
        progressBar = findViewById(R.id.pro2014);
        recyclerView = findViewById(R.id.recycle_view2014);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Adapter_Quiz adapter_home = new Adapter_Quiz(en4R2014Constants.list, R2014.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}
