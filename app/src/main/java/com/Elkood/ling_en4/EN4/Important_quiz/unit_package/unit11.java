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

public class unit11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit11);
        RecyclerView recyclerView = findViewById(R.id.words_unit11);

        ArrayList<item_words> list = new ArrayList<>();

        list.add(new item_words("Interface ", "واجهة"));
        list.add(new item_words("Plug-in", "المكونات الاضافية  "));
        list.add(new item_words("Adaptors ", "محولات "));
        list.add(new item_words("Component ", "مكون "));
        list.add(new item_words("Access point  ", "نقطة الوصول "));
        list.add(new item_words("Local", "محلي  "));
        list.add(new item_words("Area network  ", "شبكة المنطقة "));
        list.add(new item_words("Operates  ", "تعمل "));
        list.add(new item_words("Router", "جهاز توجيه "));
        list.add(new item_words("Provides", "يوفر "));
        list.add(new item_words("Bridge ", "جسر "));
        list.add(new item_words("Plugs", "مقابس   "));
        list.add(new item_words("Hub ", "محور "));
        list.add(new item_words("Via ", "بواسطة "));
        list.add(new item_words("Advantages ", "مزايا  "));
        list.add(new item_words("Expensive", "مكلف"));
        list.add(new item_words("Install ", "تثبيت  "));
        list.add(new item_words("Cover", "يغطي  "));
        list.add(new item_words("Entire ", "كامل "));
        list.add(new item_words("Floor", "طابق "));
        list.add(new item_words("Building", "مبنى "));
        list.add(new item_words("Range ", "مجال "));
        list.add(new item_words("Outside", "في الخارج "));
        list.add(new item_words("Hotspot", " نقطة اتصال  "));
        list.add(new item_words("Link ", "الارتباط "));
        list.add(new item_words("Disadvantages   ", "عيوب  "));
        list.add(new item_words("Further ", "الأبعد "));
        list.add(new item_words("Rate  ", "معدل/ تردد "));
        list.add(new item_words("Although", " على الرغم"));
        list.add(new item_words("Savings           ", "توفيرات  "));
        list.add(new item_words("Cost ", "كلفة   "));
        list.add(new item_words("Wired ", "السلكية   "));
        list.add(new item_words("Versions ", "النسخات   "));
        list.add(new item_words("Interference ", "التداخل  "));
        list.add(new item_words("Neighbor", "جار   "));
        list.add(new item_words("Same", "نفس   "));
        list.add(new item_words("Channel  ", "القناة  "));
        list.add(new item_words("Intercept", "اعتراض "));
        list.add(new item_words("Encryption  ", "التشفير   "));
        list.add(new item_words("Equivalent ", "مايعادلها  "));
        list.add(new item_words("Privacy  ", "الخصوصية  "));
        list.add(new item_words("Diagram ", "الرسم التخطيطي   "));
        list.add(new item_words("Enabling ", "تمكين  "));
        list.add(new item_words("Equipped ", "مجهزة "));
        list.add(new item_words("Print server ", "ملقم الطابعة  "));
        list.add(new item_words("Client", "العميل  "));
        list.add(new item_words("combination               ", "مزيج  "));
        list.add(new item_words("partition   ", "تقسيم   "));
        list.add(new item_words("Nearby    ", "مجاوز"));
        list.add(new item_words("Directs", "يواجه  "));
        list.add(new item_words("Communicating   ", "التواصل  "));
        list.add(new item_words("Several", "العديد من  "));
        list.add(new item_words("Backbone", "العمود الفقري   "));
        list.add(new item_words("Serve", "تخدم   "));
        list.add(new item_words("Enables     ", "تمكين   "));
        list.add(new item_words("Dissimilar", "غير مشابه   "));
        list.add(new item_words("Based", "على اساس "));
        list.add(new item_words("Topologies", "علوم الهندسة اللاكمية   "));
        list.add(new item_words("Main", "الاساسية  "));
        list.add(new item_words("Transmission", "انتقال   "));
        list.add(new item_words("Path", "مسار  "));
        list.add(new item_words("Handling", "معالجة  "));
        list.add(new item_words("Major", "رائد   "));
        list.add(new item_words("Contained", "يتضمن  "));
        list.add(new item_words("Within", "في غضون   "));
        list.add(new item_words("Department", "قسم   "));
        list.add(new item_words("Converting", " التحول  "));
        list.add(new item_words("digital signals", "اشارات رقمية   "));
        list.add(new item_words("analogue", "النظير "));
        list.add(new item_words("vice", "نائب   "));
        list.add(new item_words("Versa", "بالعكس   "));
        list.add(new item_words("Transmit", "نقل  "));
        list.add(new item_words("Receive", "تسلم   "));
        list.add(new item_words("Ordinary", "  عادي "));
        list.add(new item_words("Layer ", "طبقة   "));
        list.add(new item_words("Process", "عملية   "));
        list.add(new item_words("Sees", "يري   "));
        list.add(new item_words("Prepare", "إعداد   "));
        list.add(new item_words("Converts", "تحويل "));
        list.add(new item_words("Human-readable", "مقروء للإنسان"));
        list.add(new item_words("Attaches", "تولي "));
        list.add(new item_words("Header", "راس "));
        list.add(new item_words("Identifying", "تحديد"));
        list.add(new item_words("Ensures", "يضمن"));
        list.add(new item_words("Transmitted", "احال"));
        list.add(new item_words("Interpret", "تفسير "));
        list.add(new item_words("Compresses", "يضغط "));
        list.add(new item_words("Perhaps", "ربما  "));
        list.add(new item_words("Encrypts", "يشفر  "));
        list.add(new item_words("Specifying", "تحديد  "));
        list.add(new item_words("Compression", "الضغط  "));
        list.add(new item_words("Schemes", "مخططات  "));
        list.add(new item_words("Session", "الدوره  "));
        list.add(new item_words("Straight", "مباشرة  "));
        list.add(new item_words("Among", "بين  "));
        list.add(new item_words("Nodes", "العقد  "));
        list.add(new item_words("Boundaries", "الحدود  "));
        list.add(new item_words("Bracketing", "بين أقواس  "));
        list.add(new item_words("Establishes", "يحدد  "));
        list.add(new item_words("Whether", "سواء  "));
        list.add(new item_words("Half-duplex", "نصف دوبليكس  "));
        list.add(new item_words("Decision", "المقرر  "));
        list.add(new item_words("Transport", "التقل  "));
        list.add(new item_words("Subdivides", "يقسم  "));
        list.add(new item_words("Segments", "قطاعات "));
        list.add(new item_words("Checksum", "المجموع الأختباري "));
        list.add(new item_words("Contents", " المحتويات  "));
        list.add(new item_words("Determine", "تحديد  "));
        list.add(new item_words("Scrambled", "سارعت  "));
        list.add(new item_words("Forms", "أشكال  "));
        list.add(new item_words("Supervises", "تشرف  "));
        list.add(new item_words("Confirms", "يؤكد  "));
        list.add(new item_words("Encodes", "الترميز  "));
        list.add(new item_words("Analogue", "التماثلية "));
        list.add(new item_words("Intermediate", "المتوسطة "));
        list.add(new item_words("Verifies", "يتحقق "));
        list.add(new item_words("Congestion", "الازدحام "));
        list.add(new item_words("Billing", "الفواتير "));
        list.add(new item_words("Reassembles", "إعادة التجميع  "));
        list.add(new item_words("Decrypts", "فك تشفير "));



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);

    }
}
