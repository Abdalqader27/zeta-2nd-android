package com.Elkood.ling_en4.Important_quiz.True_false;

import android.os.Bundle;

import com.Elkood.ling_en4.Adapter.Adapter_true_false;
import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Utils.item_true_false;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class True_false extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);
        RecyclerView recyclerView = findViewById(R.id.recycle_true_false);
        ArrayList<item_true_false> list = new ArrayList<>();
        list.add(new item_true_false("Software from an ASP must be installed locally on a user's computer ", "يجب تثبيت البرنامج من ASP محليًا على كمبيوتر المستخدم",R.drawable.ic_cancel_mark));
        list.add(new item_true_false("You need a high bandwidth connection to use an ASP service ","تحتاج إلى اتصال نطاق ترددي عالي لاستخدام خدمة ASP", R.drawable.ic_check_green));
        list.add(new item_true_false("ASPs usually use their own storage space for customers ","عادةً ما تستخدم ASPs مساحة التخزين الخاصة بها للعملاء", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("Using an ASP gives you more flexibility","يمنحك استخدام ASP مرونة أكبر", R.drawable.ic_check_green));
        list.add(new item_true_false("An e-commerce business usually provides all of the required technology itself ","عادة ما توفر شركة التجارة الإلكترونية كل التكنولوجيا المطلوبة نفسها", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("JPEG the most common compression system used for video ","JPEG أكثر أنظمة الضغط شيوعًا المستخدمة للفيديو", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("P-frame only store the changes in the image","P- الإطار فقط تخزين التغييرات في الصورة", R.drawable.ic_check_green));
        list.add(new item_true_false("here is always at least one P-frame between tow I-frame","هنا دائما ما لا يقل عن واحد P- الإطار بين اثنين Iframe", R.drawable.ic_check_green));
        list.add(new item_true_false("B-frame store the complete picture information","B- الإطار تخزين معلومات الصورة كاملة", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("There can only be one B-frame between each I and P-frame","يمكن أن يكون هناك إطار B واحد فقط بين كل إطار I و P", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("There are typically about four P-frames between each I-frame","يوجد عادة حوالي أربعة إطارات P بين كل إطار ", R.drawable.ic_check_green));
        list.add(new item_true_false("Most the work that an application does to prepare a message for sending over a network is not seen by the user","معظم الأعمال التي يقوم بها التطبيق لإعداد رسالة لإرسالها عبر شبكة لا يراها المستخدم", R.drawable.ic_check_green));
        list.add(new item_true_false("ASCII Is always used to transmit a data","يستخدم ASCII دائمًا لنقل البيانات", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("The encryption layer compressed the message","ضغط طبقة التشفير الرسالة", R.drawable.ic_check_green));
        list.add(new item_true_false("The network layer keeps a copy of each packet until it arrives at the next node undamaged","تحتفظ طبقة الشبكة بنسخة من كل حزمة حتى تصل إلى العقدة التالية غير تالفة", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("Analogue signal are used on ordinary telephone lines","يتم استخدام إشارة تناظرية على خطوط الهاتف العادية", R.drawable.ic_check_green));
        list.add(new item_true_false("When a message arrives at its destination, it passes through the same seven network communication layers as when it was sent, but in reverse order","عندما تصل الرسالة إلى وجهتها ، فإنها تمر عبر طبقات الاتصال الشبكي السبع نفسها كما كانت عند إرسالها ، ولكن بترتيب عكسي", R.drawable.ic_check_green));
        list.add(new item_true_false("Internet addresses are an integral part of the IP protocol.", "تعتبر عناوين الإنترنت جزءًا لا يتجزأ من بروتوكول IP.",R.drawable.ic_check_green));
        list.add(new item_true_false("Internet addresses can be written as a series of numbers.", "يمكن كتابة عناوين الإنترنت كسلسلة من الأرقام.",R.drawable.ic_check_green));
        list.add(new item_true_false("UDP software provides the final routing for data within the receiving system.", "يوفر برنامج UDP التوجيه النهائي للبيانات داخل نظام الاستقبال.",R.drawable.ic_check_green));
        list.add(new item_true_false("UDP recovers packets that aren't successfully delivered.","يستعيد UDP الحزم التي لم يتم تسليمها بنجاح", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("TCP only works with packet-switched networks.", "يعمل TCP فقط مع شبكات تبديل الحزمة",R.drawable.ic_cancel_mark));
        list.add(new item_true_false("TCP only works when it is combined with IP.","يعمل TCP فقط عندما يتم دمجه مع IP.", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("Different mail systems transfer emails in different ways.","تنقل أنظمة البريد المختلفة رسائل البريد الإلكتروني بطرق مختلفة.", R.drawable.ic_check_green));
        list.add(new item_true_false("IMAP4 requires more bandwidth than the other email protocols.", "يتطلب IMAP4 نطاقًا تردديًا أكبر من بروتوكولات البريد الإلكتروني الأخرى.",R.drawable.ic_cancel_mark));
        list.add(new item_true_false("SMTP is used for sending emails from a PC to a server", "يستخدم SMTP لإرسال رسائل البريد الإلكتروني من جهاز كمبيوتر إلى خادم",R.drawable.ic_check_green));
        list.add(new item_true_false("SMTP delivers messages one at a time.","يسلم SMTP رسائل واحدة في كل مرة.", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("SMTP does not allow a delivered message to be cancelled.","لا يسمح SMTP بإلغاء الرسالة التي يتم تسليمها.", R.drawable.ic_check_green));
        list.add(new item_true_false("SMTP is only one of many protocols used to send mail between servers.","SMTP هو واحد فقط من العديد من البروتوكولات المستخدمة لإرسال البريد بين الخوادم.", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("POP protocol allows the user to download one message at a time.","يسمح بروتوكول POP للمستخدم بتنزيل رسالة واحدة في كل مرة.", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("HTML is no longer useful for creating webpages.", "لم يعد HTML مفيدًا لإنشاء صفحات الويب",R.drawable.ic_cancel_mark));
        list.add(new item_true_false("SGML is more complex than XML.","SGML أكثر تعقيدًا من XML.", R.drawable.ic_check_green));
        list.add(new item_true_false("XML files can only be used on Unix systems.", "لا يمكن استخدام ملفات XML إلا على أنظمة Unix.",R.drawable.ic_cancel_mark));
        list.add(new item_true_false("XML files can only be read by browser programs.","لا يمكن قراءة ملفات XML إلا عن طريق برامج المتصفح", R.drawable.ic_cancel_mark));
        list.add(new item_true_false("HTML is a markup language", "HTML هي لغة الترميز",R.drawable.ic_check_green));
        list.add(new item_true_false("Internet searches will be better with XML files.","ستكون عمليات البحث على الإنترنت أفضل مع ملفات XML", R.drawable.ic_check_green));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        Adapter_true_false adapter_home = new Adapter_true_false(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);
    }

}
