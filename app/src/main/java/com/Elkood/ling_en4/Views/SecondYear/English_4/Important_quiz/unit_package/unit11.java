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

public class unit11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit11);
        RecyclerView recyclerView = findViewById(R.id.words_unit11);

        ArrayList<WordsMeans> list = new ArrayList<>();

        list.add(new WordsMeans("Interface ", "واجهة"));
        list.add(new WordsMeans("Plug-in", "المكونات الاضافية  "));
        list.add(new WordsMeans("Adaptors ", "محولات "));
        list.add(new WordsMeans("Component ", "مكون "));
        list.add(new WordsMeans("Access point  ", "نقطة الوصول "));
        list.add(new WordsMeans("Local", "محلي  "));
        list.add(new WordsMeans("Area network  ", "شبكة المنطقة "));
        list.add(new WordsMeans("Operates  ", "تعمل "));
        list.add(new WordsMeans("Router", "جهاز توجيه "));
        list.add(new WordsMeans("Provides", "يوفر "));
        list.add(new WordsMeans("Bridge ", "جسر "));
        list.add(new WordsMeans("Plugs", "مقابس   "));
        list.add(new WordsMeans("Hub ", "محور "));
        list.add(new WordsMeans("Via ", "بواسطة "));
        list.add(new WordsMeans("Advantages ", "مزايا  "));
        list.add(new WordsMeans("Expensive", "مكلف"));
        list.add(new WordsMeans("Install ", "تثبيت  "));
        list.add(new WordsMeans("Cover", "يغطي  "));
        list.add(new WordsMeans("Entire ", "كامل "));
        list.add(new WordsMeans("Floor", "طابق "));
        list.add(new WordsMeans("Building", "مبنى "));
        list.add(new WordsMeans("Range ", "مجال "));
        list.add(new WordsMeans("Outside", "في الخارج "));
        list.add(new WordsMeans("Hotspot", " نقطة اتصال  "));
        list.add(new WordsMeans("Link ", "الارتباط "));
        list.add(new WordsMeans("Disadvantages   ", "عيوب  "));
        list.add(new WordsMeans("Further ", "الأبعد "));
        list.add(new WordsMeans("Rate  ", "معدل/ تردد "));
        list.add(new WordsMeans("Although", " على الرغم"));
        list.add(new WordsMeans("Savings           ", "توفيرات  "));
        list.add(new WordsMeans("Cost ", "كلفة   "));
        list.add(new WordsMeans("Wired ", "السلكية   "));
        list.add(new WordsMeans("Versions ", "النسخات   "));
        list.add(new WordsMeans("Interference ", "التداخل  "));
        list.add(new WordsMeans("Neighbor", "جار   "));
        list.add(new WordsMeans("Same", "نفس   "));
        list.add(new WordsMeans("Channel  ", "القناة  "));
        list.add(new WordsMeans("Intercept", "اعتراض "));
        list.add(new WordsMeans("Encryption  ", "التشفير   "));
        list.add(new WordsMeans("Equivalent ", "مايعادلها  "));
        list.add(new WordsMeans("Privacy  ", "الخصوصية  "));
        list.add(new WordsMeans("Diagram ", "الرسم التخطيطي   "));
        list.add(new WordsMeans("Enabling ", "تمكين  "));
        list.add(new WordsMeans("Equipped ", "مجهزة "));
        list.add(new WordsMeans("Print server ", "ملقم الطابعة  "));
        list.add(new WordsMeans("Client", "العميل  "));
        list.add(new WordsMeans("combination               ", "مزيج  "));
        list.add(new WordsMeans("partition   ", "تقسيم   "));
        list.add(new WordsMeans("Nearby    ", "مجاوز"));
        list.add(new WordsMeans("Directs", "يواجه  "));
        list.add(new WordsMeans("Communicating   ", "التواصل  "));
        list.add(new WordsMeans("Several", "العديد من  "));
        list.add(new WordsMeans("Backbone", "العمود الفقري   "));
        list.add(new WordsMeans("Serve", "تخدم   "));
        list.add(new WordsMeans("Enables     ", "تمكين   "));
        list.add(new WordsMeans("Dissimilar", "غير مشابه   "));
        list.add(new WordsMeans("Based", "على اساس "));
        list.add(new WordsMeans("Topologies", "علوم الهندسة اللاكمية   "));
        list.add(new WordsMeans("Main", "الاساسية  "));
        list.add(new WordsMeans("Transmission", "انتقال   "));
        list.add(new WordsMeans("Path", "مسار  "));
        list.add(new WordsMeans("Handling", "معالجة  "));
        list.add(new WordsMeans("Major", "رائد   "));
        list.add(new WordsMeans("Contained", "يتضمن  "));
        list.add(new WordsMeans("Within", "في غضون   "));
        list.add(new WordsMeans("Department", "قسم   "));
        list.add(new WordsMeans("Converting", " التحول  "));
        list.add(new WordsMeans("digital signals", "اشارات رقمية   "));
        list.add(new WordsMeans("analogue", "النظير "));
        list.add(new WordsMeans("vice", "نائب   "));
        list.add(new WordsMeans("Versa", "بالعكس   "));
        list.add(new WordsMeans("Transmit", "نقل  "));
        list.add(new WordsMeans("Receive", "تسلم   "));
        list.add(new WordsMeans("Ordinary", "  عادي "));
        list.add(new WordsMeans("Layer ", "طبقة   "));
        list.add(new WordsMeans("Process", "عملية   "));
        list.add(new WordsMeans("Sees", "يري   "));
        list.add(new WordsMeans("Prepare", "إعداد   "));
        list.add(new WordsMeans("Converts", "تحويل "));
        list.add(new WordsMeans("Human-readable", "مقروء للإنسان"));
        list.add(new WordsMeans("Attaches", "تولي "));
        list.add(new WordsMeans("Header", "راس "));
        list.add(new WordsMeans("Identifying", "تحديد"));
        list.add(new WordsMeans("Ensures", "يضمن"));
        list.add(new WordsMeans("Transmitted", "احال"));
        list.add(new WordsMeans("Interpret", "تفسير "));
        list.add(new WordsMeans("Compresses", "يضغط "));
        list.add(new WordsMeans("Perhaps", "ربما  "));
        list.add(new WordsMeans("Encrypts", "يشفر  "));
        list.add(new WordsMeans("Specifying", "تحديد  "));
        list.add(new WordsMeans("Compression", "الضغط  "));
        list.add(new WordsMeans("Schemes", "مخططات  "));
        list.add(new WordsMeans("Session", "الدوره  "));
        list.add(new WordsMeans("Straight", "مباشرة  "));
        list.add(new WordsMeans("Among", "بين  "));
        list.add(new WordsMeans("Nodes", "العقد  "));
        list.add(new WordsMeans("Boundaries", "الحدود  "));
        list.add(new WordsMeans("Bracketing", "بين أقواس  "));
        list.add(new WordsMeans("Establishes", "يحدد  "));
        list.add(new WordsMeans("Whether", "سواء  "));
        list.add(new WordsMeans("Half-duplex", "نصف دوبليكس  "));
        list.add(new WordsMeans("Decision", "المقرر  "));
        list.add(new WordsMeans("Transport", "التقل  "));
        list.add(new WordsMeans("Subdivides", "يقسم  "));
        list.add(new WordsMeans("Segments", "قطاعات "));
        list.add(new WordsMeans("Checksum", "المجموع الأختباري "));
        list.add(new WordsMeans("Contents", " المحتويات  "));
        list.add(new WordsMeans("Determine", "تحديد  "));
        list.add(new WordsMeans("Scrambled", "سارعت  "));
        list.add(new WordsMeans("Forms", "أشكال  "));
        list.add(new WordsMeans("Supervises", "تشرف  "));
        list.add(new WordsMeans("Confirms", "يؤكد  "));
        list.add(new WordsMeans("Encodes", "الترميز  "));
        list.add(new WordsMeans("Analogue", "التماثلية "));
        list.add(new WordsMeans("Intermediate", "المتوسطة "));
        list.add(new WordsMeans("Verifies", "يتحقق "));
        list.add(new WordsMeans("Congestion", "الازدحام "));
        list.add(new WordsMeans("Billing", "الفواتير "));
        list.add(new WordsMeans("Reassembles", "إعادة التجميع  "));
        list.add(new WordsMeans("Decrypts", "فك تشفير "));



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
