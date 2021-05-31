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

public class unit14 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit14);
        RecyclerView recyclerView = findViewById(R.id.words_unit14);

        ArrayList<WordsMeans> list = new ArrayList<>();
        list.add(new WordsMeans("navigation", "التنقل   "));
        list.add(new WordsMeans("eas of use", "سهولة الاستعمال   "));
        list.add(new WordsMeans("Accuracy", "صحة  "));
        list.add(new WordsMeans("Compatibility", "التوافق   "));
        list.add(new WordsMeans("Spice up", "التوابل حتى   "));
        list.add(new WordsMeans("Standard", "اساسي   "));
        list.add(new WordsMeans("Generalized ", "معممة "));
        list.add(new WordsMeans("Markup", "توصيف النص   "));
        list.add(new WordsMeans("Spawned", "منتج "));
        list.add(new WordsMeans("HyperText   ", "نص تشعبي  "));
        list.add(new WordsMeans("eXtensible", "قابل للأمتداد   "));
        list.add(new WordsMeans("MetaLanguges", "اللغة المركبة  "));
        list.add(new WordsMeans("Encoded    ", "مشفرة  "));
        list.add(new WordsMeans("Instruction   ", "تعليمة"));
        list.add(new WordsMeans("Formatting   ", "تنسيق "));
        list.add(new WordsMeans("Derivation", "اشتقاق  "));
        list.add(new WordsMeans("Presentation", "عرض"));
        list.add(new WordsMeans("Subset", "مجموعة فرعية "));
        list.add(new WordsMeans("Platform", "خطة  "));
        list.add(new WordsMeans("Internationalisation", "عولمة   "));
        list.add(new WordsMeans("Parse", "يحلل"));
        list.add(new WordsMeans("Intervention    ", "تدخل "));
        list.add(new WordsMeans("Identity", "تطابق   "));
        list.add(new WordsMeans("Developer", "مطور  "));
        list.add(new WordsMeans("Flexibility", "مرونة "));
        list.add(new WordsMeans("Explicitly", "بشكل واضح "));
        list.add(new WordsMeans("Tag", "اقتباس مختصر "));
        list.add(new WordsMeans("Preceding", "يسبق"));
        list.add(new WordsMeans("Specifically   ", "بشكل محدد "));
        list.add(new WordsMeans("MetaData", "البيانات المركبة "));
        list.add(new WordsMeans("E- commerce ", "التجارة الكترونية"));
        list.add(new WordsMeans("associated", "مرتبطة  "));
        list.add(new WordsMeans("Potential", "امكانية  "));
        list.add(new WordsMeans("Hyper", "فرط   "));
        list.add(new WordsMeans("Extensible", "توسع "));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
