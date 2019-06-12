package com.Elkood.ling_en4.Important_quiz.unit_package;

import android.os.Bundle;

import com.Elkood.ling_en4.Adapter.Adapter_words;
import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Utils.item_words;

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

        ArrayList<item_words> list = new ArrayList<>();
        list.add(new item_words("navigation", "التنقل   "));
        list.add(new item_words("eas of use", "سهولة الاستعمال   "));
        list.add(new item_words("Accuracy", "صحة  "));
        list.add(new item_words("Compatibility", "التوافق   "));
        list.add(new item_words("Spice up", "التوابل حتى   "));
        list.add(new item_words("Standard", "اساسي   "));
        list.add(new item_words("Generalized ", "معممة "));
        list.add(new item_words("Markup", "توصيف النص   "));
        list.add(new item_words("Spawned", "منتج "));
        list.add(new item_words("HyperText   ", "نص تشعبي  "));
        list.add(new item_words("eXtensible", "قابل للأمتداد   "));
        list.add(new item_words("MetaLanguges", "اللغة المركبة  "));
        list.add(new item_words("Encoded    ", "مشفرة  "));
        list.add(new item_words("Instruction   ", "تعليمة"));
        list.add(new item_words("Formatting   ", "تنسيق "));
        list.add(new item_words("Derivation", "اشتقاق  "));
        list.add(new item_words("Presentation", "عرض"));
        list.add(new item_words("Subset", "مجموعة فرعية "));
        list.add(new item_words("Platform", "خطة  "));
        list.add(new item_words("Internationalisation", "عولمة   "));
        list.add(new item_words("Parse", "يحلل"));
        list.add(new item_words("Intervention    ", "تدخل "));
        list.add(new item_words("Identity", "تطابق   "));
        list.add(new item_words("Developer", "مطور  "));
        list.add(new item_words("Flexibility", "مرونة "));
        list.add(new item_words("Explicitly", "بشكل واضح "));
        list.add(new item_words("Tag", "اقتباس مختصر "));
        list.add(new item_words("Preceding", "يسبق"));
        list.add(new item_words("Specifically   ", "بشكل محدد "));
        list.add(new item_words("MetaData", "البيانات المركبة "));
        list.add(new item_words("E- commerce ", "التجارة الكترونية"));
        list.add(new item_words("associated", "مرتبطة  "));
        list.add(new item_words("Potential", "امكانية  "));
        list.add(new item_words("Hyper", "فرط   "));
        list.add(new item_words("Extensible", "توسع "));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
