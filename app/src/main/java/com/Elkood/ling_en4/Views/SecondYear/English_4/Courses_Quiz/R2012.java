package com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.Constants.En4.En4R2012Constants;
import com.Elkood.ling_en4.R;

import butterknife.BindView;

public class R2012 extends AppCompatActivity {
    @BindView(R.id.pro2012)
     ViewGroup progressBar;
    @BindView(R.id.recycle_view2012)
     RecyclerView recyclerView;
    En4R2012Constants en4R2012Constants = new En4R2012Constants();
    LinearLayoutManager layoutManager;
    Adapter_Quiz adapter_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2012);
        initItems();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);

    }

    private void initItems() {
        progressBar = findViewById(R.id.pro2012);
        recyclerView = findViewById(R.id.recycle_view2012);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter_home = new Adapter_Quiz(en4R2012Constants.list, R2012.this);


    }


}
