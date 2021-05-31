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

public class unit13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit13);
        RecyclerView recyclerView = findViewById(R.id.words_unit13);

        ArrayList<WordsMeans> list = new ArrayList<>();
        list.add(new WordsMeans("Prefix ", "بادئة "));
        list.add(new WordsMeans("Domain", "حقل  "));
        list.add(new WordsMeans("Extension", "امتداد "));
        list.add(new WordsMeans("Aviation   ", "الطيران "));
        list.add(new WordsMeans("Cooperative", "تعاوني  "));
        list.add(new WordsMeans("Government     ", "حكومة "));
        list.add(new WordsMeans("Organization    ", "منظمة "));
        list.add(new WordsMeans("Military   ", "عسكري "));
        list.add(new WordsMeans("Agency   ", "وكالة "));
        list.add(new WordsMeans("Non-profit    ", "لاربحي "));
        list.add(new WordsMeans("Professionals    ", "مهن  "));
        list.add(new WordsMeans("Informative    ", "مثقف "));
        list.add(new WordsMeans("Cultural    ", "ثقافي "));
        list.add(new WordsMeans("Firm    ", "شركة  "));
        list.add(new WordsMeans("Retail    ", "بيع بالتجزئة   "));
        list.add(new WordsMeans("Recreational    ", "مسلي"));
        list.add(new WordsMeans("Put back    ", "يرجع  "));
        list.add(new WordsMeans("Immediately    ", "فورا "));
        list.add(new WordsMeans("Precede    ", "يسبق"));
        list.add(new WordsMeans("Individual    ", "فردي "));
        list.add(new WordsMeans("Hyperlink   ", "ارتباط مباشر في النت "));
        list.add(new WordsMeans("Streamed   ", "متدفق "));
        list.add(new WordsMeans("Gradually   ", "بشكل تدريجي  "));
        list.add(new WordsMeans("forward     ", "يرسل "));
        list.add(new WordsMeans("Bookmark     ", "يؤشر ملف  "));
        list.add(new WordsMeans("Buffering    ", "الحجز "));
        list.add(new WordsMeans("Ensuring     ", "يضمن "));
        list.add(new WordsMeans("Bandwidth    ", "كمية البينات  "));
        list.add(new WordsMeans("Movie     ", "مقطع فيديو "));
        list.add(new WordsMeans(" Fine-tune", "يقيد "));
        list.add(new WordsMeans("Occurrence", "حادثة  "));
        list.add(new WordsMeans("Numrange    ", "المدى العددي  "));
        list.add(new WordsMeans("Quantities      ", "كميات  "));
        list.add(new WordsMeans("Phrase     ", "عبارة  "));
        list.add(new WordsMeans("Ignore     ", "يهمل  "));
        list.add(new WordsMeans("Enclose     ", "يرفق "));
        list.add(new WordsMeans("Quotes    ", "اقتباسات "));
        list.add(new WordsMeans("Offer", "يعرض "));
        list.add(new WordsMeans("Toil    ", "كفاح "));
        list.add(new WordsMeans("Tears     ", "دموع "));
        list.add(new WordsMeans("Sweat    ", "عرق  "));
        list.add(new WordsMeans("Insert     ", "إدخال "));
        list.add(new WordsMeans("Alternative        ", "بديل "));
        list.add(new WordsMeans("Host     ", "المضيف "));
        list.add(new WordsMeans("Format   ", "صياغة "));
        list.add(new WordsMeans("Transmitted   ", "منقول "));
        list.add(new WordsMeans("Rigidly    ", "بشدة  "));
        list.add(new WordsMeans("Slightly     ", "بقلة "));
        list.add(new WordsMeans("Employ      ", "يوظف"));
        list.add(new WordsMeans("Respectively     ", "على التوالي  "));
        list.add(new WordsMeans("Retrieve   ", "يستعيد  "));
        list.add(new WordsMeans("Straightforward ", "صريح"));
        list.add(new WordsMeans("Facilities", "تسهيلات "));
        list.add(new WordsMeans("Recipients", "المستلمون "));
        list.add(new WordsMeans("Batch mode   ", "نمط الدفعة  "));
        list.add(new WordsMeans("Recalled ", "يذكر "));
        list.add(new WordsMeans("Cancelled  ", "يلغي  "));
        list.add(new WordsMeans("Push ", "يدفع "));
        list.add(new WordsMeans("container ", "وعاء "));
        list.add(new WordsMeans("Filled", "مملوء "));
        list.add(new WordsMeans("Playback ", "إعادة الاستماع  "));
        list.add(new WordsMeans("Clip", "مقطع فيديو "));
        list.add(new WordsMeans("Interruption ", "إعاقة"));
        list.add(new WordsMeans("Steady ", "ثابت  "));
        list.add(new WordsMeans("Keywords", "الكلمات المفتاحية  "));
        list.add(new WordsMeans("Wisely ", "بحكمة "));
        list.add(new WordsMeans("Refining      ", "التصفية"));
        list.add(new WordsMeans("Landing       ", "هبوط      "));
        list.add(new WordsMeans("Exclude   ", "يستثني "));
        list.add(new WordsMeans("Particular      ", "بشكل خاص  "));
        list.add(new WordsMeans("Item ", "بند "));
        list.add(new WordsMeans("Immediately  ", "مباشرة "));
        list.add(new WordsMeans("Mention  ", "تذكير"));
        list.add(new WordsMeans("Category  ", "صنف      "));
        list.add(new WordsMeans("narrow_down    ", "يحصر "));
        list.add(new WordsMeans("Particular      ", "بشكل خاص  "));
        list.add(new WordsMeans("Item ", "بند "));
        list.add(new WordsMeans("Immediately  ", "مباشرة "));
        list.add(new WordsMeans("Mention  ", "تذكير"));
        list.add(new WordsMeans("Category  ", "صنف      "));
        list.add(new WordsMeans("narrow_down    ", "يحصر "));
        list.add(new WordsMeans("relevant    ", "ذو علاقة "));
        list.add(new WordsMeans("Directory    ", "دليل "));
        list.add(new WordsMeans("Reference      ", "مرجع "));
        list.add(new WordsMeans("Dongle     ", "جهاز حماية من النسخ"));
        list.add(new WordsMeans("Initiated       ", "يبتدئ      "));
        list.add(new WordsMeans("Guaranteed         ", "مضمون "));
        list.add(new WordsMeans("Transmitted ", "مرسل"));
        list.add(new WordsMeans("Vary ", "يتغير "));
        list.add(new WordsMeans("Mailbox   ", "صندوق البريد "));
        list.add(new WordsMeans("Pull  ", "سحب"));
        list.add(new WordsMeans("Mode ", "نموذج      "));
        list.add(new WordsMeans("Initiating  ", "ابتداء "));
        list.add(new WordsMeans("Clients     ", "مشتركين"));
        list.add(new WordsMeans("Automatically       ", "اوتوماتيكيا "));
        list.add(new WordsMeans("Preset            ", " حالي "));
        list.add(new WordsMeans("Interval      ", "فاصلة"));
        list.add(new WordsMeans("Via     ", "بواسطة      "));
        list.add(new WordsMeans("picked up      ", "يرتقع "));
        list.add(new WordsMeans("Losing    ", "فقد"));
        list.add(new WordsMeans("Regularly    ", "بانتظام "));
        list.add(new WordsMeans("build up ", " يشكل "));
        list.add(new WordsMeans("Recognize  ", "يميز"));
        list.add(new WordsMeans("Duplicate ", "ينسخ طيق الأصل  "));
        list.add(new WordsMeans("Header  ", "عنوان "));
        list.add(new WordsMeans("Technical   ", "فني "));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);


    }
}
