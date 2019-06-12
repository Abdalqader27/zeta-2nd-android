package com.Elkood.ling_en4.Header_Elements;

import android.os.Bundle;

import com.Elkood.ling_en4.Adapter.Adapter_member;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class study_member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_member);
        RecyclerView recyclerView = findViewById(R.id.recycle_member);
        ArrayList<String> list = new ArrayList<>();
        list.add("لانا قدو");
        list.add("ريَان حوري  ");
        list.add("نور سفلو  ");
        list.add("عبدالعزيز شحرور  ");
        list.add("راما لبان ");
        list.add("ميس مهروسة ");
        list.add("دلال فتوح  ");
        list.add("دانيا رجب  ");
        list.add("فاطمة  الزهراء حمدك ");
        list.add("شيماء نجار  ");
        list.add("ليلى وراق ");
        list.add("دعاء مايو ");
        list.add("هديل الابراهيم ");
        list.add("ياسمين اسماعيل  ");


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        Adapter_member adapter_home = new Adapter_member(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);
    }
}
