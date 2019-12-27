package com.Elkood.ling_en4.EN4.Important_quiz.unit_package;

import android.os.Bundle;

import com.Elkood.ling_en4.EN4.Adapter.Adapter_words;
import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.EN4.Utils.item_words;

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

        ArrayList<item_words> list = new ArrayList<>();

        list.add(new item_words("Log on ", " تسجيل الدخول "));
        list.add(new item_words("Server", "الخادم  "));
        list.add(new item_words("Real- Time", "فورا "));
        list.add(new item_words("Browsing", "التصفح "));
        list.add(new item_words("Take Part", "يشارك  "));
        list.add(new item_words("Simulation ", "محاكاة "));
        list.add(new item_words("Appliance ", "تطبيق "));
        list.add(new item_words("Earthed", "مؤرض "));
        list.add(new item_words("Turn off ", "يوقف "));
        list.add(new item_words("Offence ", "إساءة "));
        list.add(new item_words("Unauthorized ", "مرفوض  "));
        list.add(new item_words("Safety ", "أمان "));
        list.add(new item_words("Breach ", "انتهاك "));
        list.add(new item_words("Band ", "حزمة  "));
        list.add(new item_words("Resistor ", "مقاوم  "));
        list.add(new item_words("Screwdriver ", "مفك براغي  "));
        list.add(new item_words("Fragile ", "هش  "));
        list.add(new item_words("Financial ", "ممول "));
        list.add(new item_words("Use out -of -date ", "استخدام خارج الصلاحية "));
        list.add(new item_words("Anti - static ", "غير ساكن  "));
        list.add(new item_words("Assign", "يحدد "));
        list.add(new item_words("Sub- network", "شبكات فرعية "));
        list.add(new item_words("gateway", "بوابة  "));
        list.add(new item_words("Route", "مسار "));
        list.add(new item_words("Delivery", "يسلم  "));
        list.add(new item_words("Acting", "يمثل "));
        list.add(new item_words("Resolution", "قرار "));
        list.add(new item_words("State", "حالة , قرر  "));
        list.add(new item_words("Suppose", "يفترض "));
        list.add(new item_words("Transmission", "شبكة الارسال نقل  "));
        list.add(new item_words("Package", "حزم  "));
        list.add(new item_words("Establish", "ينجز  "));
        list.add(new item_words("Exchange", "يتبادل  "));
        list.add(new item_words("Recovering", "استعادة  "));
        list.add(new item_words("Packet", "حزم  "));
        list.add(new item_words("Buffer", "يحجز "));
        list.add(new item_words("Reliable", "موثوق "));
        list.add(new item_words("Stream", "جدول  "));
        list.add(new item_words("Enshrined", "منصوص "));
        list.add(new item_words("Official", "مسؤول "));
        list.add(new item_words("Packing", "تحميل  "));
        list.add(new item_words("Extraction", "استخلاص "));
        list.add(new item_words("IP", "الملكية الفكرية "));
        list.add(new item_words("Portion", "جزء "));
        list.add(new item_words("Coding", "الترميز "));
        list.add(new item_words("Update", "تحديث "));
        list.add(new item_words("Conceptually", "بشكل توضيحي  "));
        list.add(new item_words("Integral", "متكامل "));
        list.add(new item_words("Frequently", "بشكل متكرر "));
        list.add(new item_words("Series", "سلسلة "));
        list.add(new item_words("TCP", "برنامج التعاون الفكري  "));






        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
