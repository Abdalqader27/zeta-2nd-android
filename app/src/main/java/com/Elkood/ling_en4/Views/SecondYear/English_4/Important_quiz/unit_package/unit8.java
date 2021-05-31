package com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz.unit_package;

import android.os.Bundle;

import com.Elkood.ling_en4.Adapter.Adapter_words;
import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Models.WordsMeans;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class unit8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit8);
        RecyclerView recyclerView = findViewById(R.id.words_unit8);

        ArrayList<WordsMeans> list = new ArrayList<>();

        list.add(new WordsMeans("Consulting", "الإستشارة"));
        list.add(new WordsMeans("alongside", "جانبا"));
        list.add(new WordsMeans("holds", "العوائق"));
        list.add(new WordsMeans("Appointments", "المواعيد"));
        list.add(new WordsMeans("Prior", "مسبق"));
        list.add(new WordsMeans("Brief", "موجز"));
        list.add(new WordsMeans("Case", "الإصابة المرضية"));
        list.add(new WordsMeans("diagnosis", "التشخيص"));
        list.add(new WordsMeans("statistics", "إحصائيات"));
        list.add(new WordsMeans("categories", "الأصناف"));
        list.add(new WordsMeans("staff", "الموظفين"));
        list.add(new WordsMeans("Tailored", "مضبوطة"));
        list.add(new WordsMeans("Identify", "يميز/يقارن"));
        list.add(new WordsMeans("Due", "يستحق"));
        list.add(new WordsMeans("Vaccinations", "التلقيح"));
        list.add(new WordsMeans("Mailmerging", "دمج الرسائل"));
        list.add(new WordsMeans("Payroll", "قائمة الرواتب"));
        list.add(new WordsMeans("Package", "حزمة"));
        list.add(new WordsMeans("Spreadsheet", "برنامج الجدولة"));
        list.add(new WordsMeans("Expenditure", "النفقة "));
        list.add(new WordsMeans("Packed", "مكتظ "));
        list.add(new WordsMeans("Bursting point", "نقطة الانفجار "));
        list.add(new WordsMeans("Department", "قسم/مقاطعة "));
        list.add(new WordsMeans("fix", "يصلح "));
        list.add(new WordsMeans("installing", "التركيب "));
        list.add(new WordsMeans("rent", "يستأجر/يستعير "));
        list.add(new WordsMeans("Approach", "الطريقة "));
        list.add(new WordsMeans("havoc", "خراب/دمار/شيء سلبي "));
        list.add(new WordsMeans("Suite ", "حزمة "));
        list.add(new WordsMeans("Attractive", "جذابة"));
        list.add(new WordsMeans("Option", "خيار "));
        list.add(new WordsMeans("Investing", "الاستثمار"));
        list.add(new WordsMeans("Tied", "موصول/مقيد "));
        list.add(new WordsMeans("Upgrading ", "ترقية/ترفيع "));
        list.add(new WordsMeans("Version ", "النسخة "));
        list.add(new WordsMeans("Battling  ", "محاربة "));
        list.add(new WordsMeans("Pitfalls  ", "مخاطر "));
        list.add(new WordsMeans("Bandwidth ", "عرض الموجة "));
        list.add(new WordsMeans("Broadband  ", "البث المكثف "));
        list.add(new WordsMeans("Version ", "النسخة "));
        list.add(new WordsMeans("Connection ", "الإتصال "));
        list.add(new WordsMeans("Reliable ", "موثوق "));
        list.add(new WordsMeans("Via ", "عن طريق/بواسطة "));
        list.add(new WordsMeans("Means  ", "وسائل/موارد مالية "));
        list.add(new WordsMeans("Otherwise ", "بوجه آخر "));
        list.add(new WordsMeans("Prohibitively ", "بشكل محرم "));
        list.add(new WordsMeans("Opportunity ", "فرصة/مناسبة"));
        list.add(new WordsMeans("Permanent ", "دائم"));
        list.add(new WordsMeans("Barriers ", "بالحواجز"));
        list.add(new WordsMeans("Cope ", "تغلب/تواجه"));
        list.add(new WordsMeans("adequate ", "كافي "));
        list.add(new WordsMeans("established ", "قائم/مؤلف"));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
