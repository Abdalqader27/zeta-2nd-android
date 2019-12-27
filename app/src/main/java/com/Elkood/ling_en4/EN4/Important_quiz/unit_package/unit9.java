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

public class unit9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit9);
        RecyclerView recyclerView = findViewById(R.id.words_unit9);

        ArrayList<item_words> list = new ArrayList<>();

        list.add(new item_words("Multimedia ", "وسائط متعددة "));
        list.add(new item_words("Motion picture ", "الصور المتحركة"));
        list.add(new item_words("Standards", "معايير"));
        list.add(new item_words("Compression", "ضغط (كضغط الملفات) "));
        list.add(new item_words("ALayer", "طبقة"));
        list.add(new item_words("Actually ", "في الواقع"));
        list.add(new item_words("Competes", "تتنافس"));
        list.add(new item_words("Audio file format ", "تنسيق ملف صوتي"));
        list.add(new item_words("Key difference    ", "الفرق الرئيسي"));
        list.add(new item_words("Store", "تخزين "));
        list.add(new item_words("Hold ", "تحتفظ "));
        list.add(new item_words("Achieve", " تحقق"));
        list.add(new item_words("Reproduce ", "اعادة انتاج"));
        list.add(new item_words("Sample", "يأخذون عينة"));
        list.add(new item_words("Discrete ", "منفصل"));
        list.add(new item_words("Mass ", "كتلة"));
        list.add(new item_words("Stripping out", "تجريد"));
        list.add(new item_words("Significantly", "بشكل كبير"));
        list.add(new item_words("For instance", "على سبيل المثال"));
        list.add(new item_words("Frequency", "تكرر"));
        list.add(new item_words("Eliminate ", "يزيل"));
        list.add(new item_words("Mix ", "مزيج"));
        list.add(new item_words("Genre", "نوع الموسيقى"));
        list.add(new item_words("Immediately", " فوراً"));
        list.add(new item_words("Decode ", "فك تشفير"));
        list.add(new item_words("Route ", "توجه"));
        list.add(new item_words("Soundcard", "كرت الصوت"));
        list.add(new item_words("Features ", "مميزات"));
        list.add(new item_words("Standalone ", " مستقل"));
        list.add(new item_words("Default", "افتراضي"));
        list.add(new item_words("randomize the selections ", "اختيار عشوائي"));
        list.add(new item_words("spectrum analyzers      ", "محلّلات الطيف "));
        list.add(new item_words("equalizers     ", "معادلات"));
        list.add(new item_words("themes ", "مواضيع"));
        list.add(new item_words("akin to", "اقرب إلى"));
        list.add(new item_words("Alters   ", "يغير"));
        list.add(new item_words("Jukebox   ", "خزانة فونوغراف آلي "));
        list.add(new item_words("Dashboard     ", "لوحة القيادة"));
        list.add(new item_words("Rippers   ", "كسارات"));
        list.add(new item_words("Encoder   ", "التشفير"));
        list.add(new item_words("vice versa ", "والعكس صحيح"));
        list.add(new item_words("incorporate      ", "دمج او تجسيد"));
        list.add(new item_words("interface    ", "واجهة تعامل "));
        list.add(new item_words("Split ", "تقسيم"));
        list.add(new item_words("Components ", "مكونات"));
        list.add(new item_words("Decompressed ", "مخفّف الضغط"));
        list.add(new item_words("Separate algorithm ", "خوارزمية منفصلة "));
        list.add(new item_words("Frames  ", "إطارات"));
        list.add(new item_words("Sequence  ", "تسلسل "));
        list.add(new item_words("Depicting ", "تصوّر "));
        list.add(new item_words("Bouncing ", "كاذب "));
        list.add(new item_words("Conventional ", "تقليدي  "));
        list.add(new item_words("Bitmap ", "صورة نقطية"));
        list.add(new item_words("Predicted ", "توقّع"));
        list.add(new item_words("Based on ", "يعتمد على"));
        list.add(new item_words("Description ", "وصف "));
        list.add(new item_words("Position ", "موقف"));
        list.add(new item_words("Fraction ", "جزء"));
        list.add(new item_words("massive ", "كبير"));
        list.add(new item_words("introduced ", "أدخلت "));
        list.add(new item_words("reference ", "مرجعية"));
        list.add(new item_words("margin ", "حافة"));
        list.add(new item_words("creeps ", "لصوص"));
        list.add(new item_words("directional ", "اتجاهي "));
        list.add(new item_words("based ", "على أساس"));
        list.add(new item_words("recreate ", "إعادة إنشاء"));
        list.add(new item_words("combination ", " مزيج"));
        list.add(new item_words("propagate ", "بث "));
        list.add(new item_words("typically ", "عادةً "));



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);
    }
}
