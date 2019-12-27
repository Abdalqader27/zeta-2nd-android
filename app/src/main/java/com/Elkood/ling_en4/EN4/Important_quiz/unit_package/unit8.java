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

public class unit8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit8);
        RecyclerView recyclerView = findViewById(R.id.words_unit8);

        ArrayList<item_words> list = new ArrayList<>();

        list.add(new item_words("Consulting", "الإستشارة"));
        list.add(new item_words("alongside", "جانبا"));
        list.add(new item_words("holds", "العوائق"));
        list.add(new item_words("Appointments", "المواعيد"));
        list.add(new item_words("Prior", "مسبق"));
        list.add(new item_words("Brief", "موجز"));
        list.add(new item_words("Case", "الإصابة المرضية"));
        list.add(new item_words("diagnosis", "التشخيص"));
        list.add(new item_words("statistics", "إحصائيات"));
        list.add(new item_words("categories", "الأصناف"));
        list.add(new item_words("staff", "الموظفين"));
        list.add(new item_words("Tailored", "مضبوطة"));
        list.add(new item_words("Identify", "يميز/يقارن"));
        list.add(new item_words("Due", "يستحق"));
        list.add(new item_words("Vaccinations", "التلقيح"));
        list.add(new item_words("Mailmerging", "دمج الرسائل"));
        list.add(new item_words("Payroll", "قائمة الرواتب"));
        list.add(new item_words("Package", "حزمة"));
        list.add(new item_words("Spreadsheet", "برنامج الجدولة"));
        list.add(new item_words("Expenditure", "النفقة "));
        list.add(new item_words("Packed", "مكتظ "));
        list.add(new item_words("Bursting point", "نقطة الانفجار "));
        list.add(new item_words("Department", "قسم/مقاطعة "));
        list.add(new item_words("fix", "يصلح "));
        list.add(new item_words("installing", "التركيب "));
        list.add(new item_words("rent", "يستأجر/يستعير "));
        list.add(new item_words("Approach", "الطريقة "));
        list.add(new item_words("havoc", "خراب/دمار/شيء سلبي "));
        list.add(new item_words("Suite ", "حزمة "));
        list.add(new item_words("Attractive", "جذابة"));
        list.add(new item_words("Option", "خيار "));
        list.add(new item_words("Investing", "الاستثمار"));
        list.add(new item_words("Tied", "موصول/مقيد "));
        list.add(new item_words("Upgrading ", "ترقية/ترفيع "));
        list.add(new item_words("Version ", "النسخة "));
        list.add(new item_words("Battling  ", "محاربة "));
        list.add(new item_words("Pitfalls  ", "مخاطر "));
        list.add(new item_words("Bandwidth ", "عرض الموجة "));
        list.add(new item_words("Broadband  ", "البث المكثف "));
        list.add(new item_words("Version ", "النسخة "));
        list.add(new item_words("Connection ", "الإتصال "));
        list.add(new item_words("Reliable ", "موثوق "));
        list.add(new item_words("Via ", "عن طريق/بواسطة "));
        list.add(new item_words("Means  ", "وسائل/موارد مالية "));
        list.add(new item_words("Otherwise ", "بوجه آخر "));
        list.add(new item_words("Prohibitively ", "بشكل محرم "));
        list.add(new item_words("Opportunity ", "فرصة/مناسبة"));
        list.add(new item_words("Permanent ", "دائم"));
        list.add(new item_words("Barriers ", "بالحواجز"));
        list.add(new item_words("Cope ", "تغلب/تواجه"));
        list.add(new item_words("adequate ", "كافي "));
        list.add(new item_words("established ", "قائم/مؤلف"));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
