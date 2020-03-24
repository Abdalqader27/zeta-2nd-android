package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.unit_package;

import android.os.Bundle;

import com.Elkood.ling_en4.Adapter.Adapter_words;
import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Models.WordsMeans;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class unit12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit12);
        RecyclerView recyclerView = findViewById(R.id.words_unit12);

        ArrayList<WordsMeans> list = new ArrayList<>();

        list.add(new WordsMeans("Log on ", " تسجيل الدخول "));
        list.add(new WordsMeans("Server", "الخادم  "));
        list.add(new WordsMeans("Real- Time", "فورا "));
        list.add(new WordsMeans("Browsing", "التصفح "));
        list.add(new WordsMeans("Take Part", "يشارك  "));
        list.add(new WordsMeans("Simulation ", "محاكاة "));
        list.add(new WordsMeans("Appliance ", "تطبيق "));
        list.add(new WordsMeans("Earthed", "مؤرض "));
        list.add(new WordsMeans("Turn off ", "يوقف "));
        list.add(new WordsMeans("Offence ", "إساءة "));
        list.add(new WordsMeans("Unauthorized ", "مرفوض  "));
        list.add(new WordsMeans("Safety ", "أمان "));
        list.add(new WordsMeans("Breach ", "انتهاك "));
        list.add(new WordsMeans("Band ", "حزمة  "));
        list.add(new WordsMeans("Resistor ", "مقاوم  "));
        list.add(new WordsMeans("Screwdriver ", "مفك براغي  "));
        list.add(new WordsMeans("Fragile ", "هش  "));
        list.add(new WordsMeans("Financial ", "ممول "));
        list.add(new WordsMeans("Use out -of -date ", "استخدام خارج الصلاحية "));
        list.add(new WordsMeans("Anti - static ", "غير ساكن  "));
        list.add(new WordsMeans("Assign", "يحدد "));
        list.add(new WordsMeans("Sub- network", "شبكات فرعية "));
        list.add(new WordsMeans("gateway", "بوابة  "));
        list.add(new WordsMeans("Route", "مسار "));
        list.add(new WordsMeans("Delivery", "يسلم  "));
        list.add(new WordsMeans("Acting", "يمثل "));
        list.add(new WordsMeans("Resolution", "قرار "));
        list.add(new WordsMeans("State", "حالة , قرر  "));
        list.add(new WordsMeans("Suppose", "يفترض "));
        list.add(new WordsMeans("Transmission", "شبكة الارسال نقل  "));
        list.add(new WordsMeans("Package", "حزم  "));
        list.add(new WordsMeans("Establish", "ينجز  "));
        list.add(new WordsMeans("Exchange", "يتبادل  "));
        list.add(new WordsMeans("Recovering", "استعادة  "));
        list.add(new WordsMeans("Packet", "حزم  "));
        list.add(new WordsMeans("Buffer", "يحجز "));
        list.add(new WordsMeans("Reliable", "موثوق "));
        list.add(new WordsMeans("Stream", "جدول  "));
        list.add(new WordsMeans("Enshrined", "منصوص "));
        list.add(new WordsMeans("Official", "مسؤول "));
        list.add(new WordsMeans("Packing", "تحميل  "));
        list.add(new WordsMeans("Extraction", "استخلاص "));
        list.add(new WordsMeans("IP", "الملكية الفكرية "));
        list.add(new WordsMeans("Portion", "جزء "));
        list.add(new WordsMeans("Coding", "الترميز "));
        list.add(new WordsMeans("Update", "تحديث "));
        list.add(new WordsMeans("Conceptually", "بشكل توضيحي  "));
        list.add(new WordsMeans("Integral", "متكامل "));
        list.add(new WordsMeans("Frequently", "بشكل متكرر "));
        list.add(new WordsMeans("Series", "سلسلة "));
        list.add(new WordsMeans("TCP", "برنامج التعاون الفكري  "));






        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
