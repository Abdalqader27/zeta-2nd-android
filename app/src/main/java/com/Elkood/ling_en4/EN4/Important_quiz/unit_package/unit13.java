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

public class unit13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit13);
        RecyclerView recyclerView = findViewById(R.id.words_unit13);

        ArrayList<item_words> list = new ArrayList<>();
        list.add(new item_words("Prefix ", "بادئة "));
        list.add(new item_words("Domain", "حقل  "));
        list.add(new item_words("Extension", "امتداد "));
        list.add(new item_words("Aviation   ", "الطيران "));
        list.add(new item_words("Cooperative", "تعاوني  "));
        list.add(new item_words("Government     ", "حكومة "));
        list.add(new item_words("Organization    ", "منظمة "));
        list.add(new item_words("Military   ", "عسكري "));
        list.add(new item_words("Agency   ", "وكالة "));
        list.add(new item_words("Non-profit    ", "لاربحي "));
        list.add(new item_words("Professionals    ", "مهن  "));
        list.add(new item_words("Informative    ", "مثقف "));
        list.add(new item_words("Cultural    ", "ثقافي "));
        list.add(new item_words("Firm    ", "شركة  "));
        list.add(new item_words("Retail    ", "بيع بالتجزئة   "));
        list.add(new item_words("Recreational    ", "مسلي"));
        list.add(new item_words("Put back    ", "يرجع  "));
        list.add(new item_words("Immediately    ", "فورا "));
        list.add(new item_words("Precede    ", "يسبق"));
        list.add(new item_words("Individual    ", "فردي "));
        list.add(new item_words("Hyperlink   ", "ارتباط مباشر في النت "));
        list.add(new item_words("Streamed   ", "متدفق "));
        list.add(new item_words("Gradually   ", "بشكل تدريجي  "));
        list.add(new item_words("forward     ", "يرسل "));
        list.add(new item_words("Bookmark     ", "يؤشر ملف  "));
        list.add(new item_words("Buffering    ", "الحجز "));
        list.add(new item_words("Ensuring     ", "يضمن "));
        list.add(new item_words("Bandwidth    ", "كمية البينات  "));
        list.add(new item_words("Movie     ", "مقطع فيديو "));
        list.add(new item_words(" Fine-tune", "يقيد "));
        list.add(new item_words("Occurrence", "حادثة  "));
        list.add(new item_words("Numrange    ", "المدى العددي  "));
        list.add(new item_words("Quantities      ", "كميات  "));
        list.add(new item_words("Phrase     ", "عبارة  "));
        list.add(new item_words("Ignore     ", "يهمل  "));
        list.add(new item_words("Enclose     ", "يرفق "));
        list.add(new item_words("Quotes    ", "اقتباسات "));
        list.add(new item_words("Offer", "يعرض "));
        list.add(new item_words("Toil    ", "كفاح "));
        list.add(new item_words("Tears     ", "دموع "));
        list.add(new item_words("Sweat    ", "عرق  "));
        list.add(new item_words("Insert     ", "إدخال "));
        list.add(new item_words("Alternative        ", "بديل "));
        list.add(new item_words("Host     ", "المضيف "));
        list.add(new item_words("Format   ", "صياغة "));
        list.add(new item_words("Transmitted   ", "منقول "));
        list.add(new item_words("Rigidly    ", "بشدة  "));
        list.add(new item_words("Slightly     ", "بقلة "));
        list.add(new item_words("Employ      ", "يوظف"));
        list.add(new item_words("Respectively     ", "على التوالي  "));
        list.add(new item_words("Retrieve   ", "يستعيد  "));
        list.add(new item_words("Straightforward ", "صريح"));
        list.add(new item_words("Facilities", "تسهيلات "));
        list.add(new item_words("Recipients", "المستلمون "));
        list.add(new item_words("Batch mode   ", "نمط الدفعة  "));
        list.add(new item_words("Recalled ", "يذكر "));
        list.add(new item_words("Cancelled  ", "يلغي  "));
        list.add(new item_words("Push ", "يدفع "));
        list.add(new item_words("container ", "وعاء "));
        list.add(new item_words("Filled", "مملوء "));
        list.add(new item_words("Playback ", "إعادة الاستماع  "));
        list.add(new item_words("Clip", "مقطع فيديو "));
        list.add(new item_words("Interruption ", "إعاقة"));
        list.add(new item_words("Steady ", "ثابت  "));
        list.add(new item_words("Keywords", "الكلمات المفتاحية  "));
        list.add(new item_words("Wisely ", "بحكمة "));
        list.add(new item_words("Refining      ", "التصفية"));
        list.add(new item_words("Landing       ", "هبوط      "));
        list.add(new item_words("Exclude   ", "يستثني "));
        list.add(new item_words("Particular      ", "بشكل خاص  "));
        list.add(new item_words("Item ", "بند "));
        list.add(new item_words("Immediately  ", "مباشرة "));
        list.add(new item_words("Mention  ", "تذكير"));
        list.add(new item_words("Category  ", "صنف      "));
        list.add(new item_words("narrow_down    ", "يحصر "));
        list.add(new item_words("Particular      ", "بشكل خاص  "));
        list.add(new item_words("Item ", "بند "));
        list.add(new item_words("Immediately  ", "مباشرة "));
        list.add(new item_words("Mention  ", "تذكير"));
        list.add(new item_words("Category  ", "صنف      "));
        list.add(new item_words("narrow_down    ", "يحصر "));
        list.add(new item_words("relevant    ", "ذو علاقة "));
        list.add(new item_words("Directory    ", "دليل "));
        list.add(new item_words("Reference      ", "مرجع "));
        list.add(new item_words("Dongle     ", "جهاز حماية من النسخ"));
        list.add(new item_words("Initiated       ", "يبتدئ      "));
        list.add(new item_words("Guaranteed         ", "مضمون "));
        list.add(new item_words("Transmitted ", "مرسل"));
        list.add(new item_words("Vary ", "يتغير "));
        list.add(new item_words("Mailbox   ", "صندوق البريد "));
        list.add(new item_words("Pull  ", "سحب"));
        list.add(new item_words("Mode ", "نموذج      "));
        list.add(new item_words("Initiating  ", "ابتداء "));
        list.add(new item_words("Clients     ", "مشتركين"));
        list.add(new item_words("Automatically       ", "اوتوماتيكيا "));
        list.add(new item_words("Preset            ", " حالي "));
        list.add(new item_words("Interval      ", "فاصلة"));
        list.add(new item_words("Via     ", "بواسطة      "));
        list.add(new item_words("picked up      ", "يرتقع "));
        list.add(new item_words("Losing    ", "فقد"));
        list.add(new item_words("Regularly    ", "بانتظام "));
        list.add(new item_words("build up ", " يشكل "));
        list.add(new item_words("Recognize  ", "يميز"));
        list.add(new item_words("Duplicate ", "ينسخ طيق الأصل  "));
        list.add(new item_words("Header  ", "عنوان "));
        list.add(new item_words("Technical   ", "فني "));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setHasFixedSize(true);
        Adapter_words adapter_home = new Adapter_words(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);


    }
}
