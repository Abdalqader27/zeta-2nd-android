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

public class unit9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit9);
        RecyclerView recyclerView = findViewById(R.id.words_unit9);

        ArrayList<WordsMeans> list = new ArrayList<>();

        list.add(new WordsMeans("Multimedia ", "وسائط متعددة "));
        list.add(new WordsMeans("Motion picture ", "الصور المتحركة"));
        list.add(new WordsMeans("Standards", "معايير"));
        list.add(new WordsMeans("Compression", "ضغط (كضغط الملفات) "));
        list.add(new WordsMeans("ALayer", "طبقة"));
        list.add(new WordsMeans("Actually ", "في الواقع"));
        list.add(new WordsMeans("Competes", "تتنافس"));
        list.add(new WordsMeans("Audio file format ", "تنسيق ملف صوتي"));
        list.add(new WordsMeans("Key difference    ", "الفرق الرئيسي"));
        list.add(new WordsMeans("Store", "تخزين "));
        list.add(new WordsMeans("Hold ", "تحتفظ "));
        list.add(new WordsMeans("Achieve", " تحقق"));
        list.add(new WordsMeans("Reproduce ", "اعادة انتاج"));
        list.add(new WordsMeans("Sample", "يأخذون عينة"));
        list.add(new WordsMeans("Discrete ", "منفصل"));
        list.add(new WordsMeans("Mass ", "كتلة"));
        list.add(new WordsMeans("Stripping out", "تجريد"));
        list.add(new WordsMeans("Significantly", "بشكل كبير"));
        list.add(new WordsMeans("For instance", "على سبيل المثال"));
        list.add(new WordsMeans("Frequency", "تكرر"));
        list.add(new WordsMeans("Eliminate ", "يزيل"));
        list.add(new WordsMeans("Mix ", "مزيج"));
        list.add(new WordsMeans("Genre", "نوع الموسيقى"));
        list.add(new WordsMeans("Immediately", " فوراً"));
        list.add(new WordsMeans("Decode ", "فك تشفير"));
        list.add(new WordsMeans("Route ", "توجه"));
        list.add(new WordsMeans("Soundcard", "كرت الصوت"));
        list.add(new WordsMeans("Features ", "مميزات"));
        list.add(new WordsMeans("Standalone ", " مستقل"));
        list.add(new WordsMeans("Default", "افتراضي"));
        list.add(new WordsMeans("randomize the selections ", "اختيار عشوائي"));
        list.add(new WordsMeans("spectrum analyzers      ", "محلّلات الطيف "));
        list.add(new WordsMeans("equalizers     ", "معادلات"));
        list.add(new WordsMeans("themes ", "مواضيع"));
        list.add(new WordsMeans("akin to", "اقرب إلى"));
        list.add(new WordsMeans("Alters   ", "يغير"));
        list.add(new WordsMeans("Jukebox   ", "خزانة فونوغراف آلي "));
        list.add(new WordsMeans("Dashboard     ", "لوحة القيادة"));
        list.add(new WordsMeans("Rippers   ", "كسارات"));
        list.add(new WordsMeans("Encoder   ", "التشفير"));
        list.add(new WordsMeans("vice versa ", "والعكس صحيح"));
        list.add(new WordsMeans("incorporate      ", "دمج او تجسيد"));
        list.add(new WordsMeans("interface    ", "واجهة تعامل "));
        list.add(new WordsMeans("Split ", "تقسيم"));
        list.add(new WordsMeans("Components ", "مكونات"));
        list.add(new WordsMeans("Decompressed ", "مخفّف الضغط"));
        list.add(new WordsMeans("Separate algorithm ", "خوارزمية منفصلة "));
        list.add(new WordsMeans("Frames  ", "إطارات"));
        list.add(new WordsMeans("Sequence  ", "تسلسل "));
        list.add(new WordsMeans("Depicting ", "تصوّر "));
        list.add(new WordsMeans("Bouncing ", "كاذب "));
        list.add(new WordsMeans("Conventional ", "تقليدي  "));
        list.add(new WordsMeans("Bitmap ", "صورة نقطية"));
        list.add(new WordsMeans("Predicted ", "توقّع"));
        list.add(new WordsMeans("Based on ", "يعتمد على"));
        list.add(new WordsMeans("Description ", "وصف "));
        list.add(new WordsMeans("Position ", "موقف"));
        list.add(new WordsMeans("Fraction ", "جزء"));
        list.add(new WordsMeans("massive ", "كبير"));
        list.add(new WordsMeans("introduced ", "أدخلت "));
        list.add(new WordsMeans("reference ", "مرجعية"));
        list.add(new WordsMeans("margin ", "حافة"));
        list.add(new WordsMeans("creeps ", "لصوص"));
        list.add(new WordsMeans("directional ", "اتجاهي "));
        list.add(new WordsMeans("based ", "على أساس"));
        list.add(new WordsMeans("recreate ", "إعادة إنشاء"));
        list.add(new WordsMeans("combination ", " مزيج"));
        list.add(new WordsMeans("propagate ", "بث "));
        list.add(new WordsMeans("typically ", "عادةً "));



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);
    }
}
