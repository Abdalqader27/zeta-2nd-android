package com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.Models.Quiz;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;
/// Not Used
public class Courses_Quiz_details extends AppCompatActivity {
    public static ArrayList<Quiz> list;
    public  static  String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_courses_quiz_details);
        ViewGroup progressBar = findViewById(R.id.proCourses);
        TextView titleView=findViewById(R.id.title);
        RecyclerView recyclerView = findViewById(R.id.recycle_viewQuizCourses);

        titleView.setText(title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, Courses_Quiz_details.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);


    }
}