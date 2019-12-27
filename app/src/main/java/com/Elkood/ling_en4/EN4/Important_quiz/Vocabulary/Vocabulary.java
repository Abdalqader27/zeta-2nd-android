package com.Elkood.ling_en4.EN4.Important_quiz.Vocabulary;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.Elkood.ling_en4.R;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

import androidx.appcompat.app.AppCompatActivity;

public class Vocabulary extends AppCompatActivity {
    private ExpandingList mExpandingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        mExpandingList = findViewById(R.id.expanding_list_main);

        createItems();
    }

    private void createItems() {
        //Vocablury
        addItem("Website", "Collection of related web pages \n ", "مجموعة من صفحات الويب ذات الصلة ", R.color.colorPrimary);
        addItem("Virus", "Self-replicating program.\n", "برنامج التكرار الذاتي.", R.color.colorPrimary);
        addItem("Office Suite", "Set of Standard Programs used in an Office.\n", "مجموعة من البرامج القياسية المستخدمة في مكتب.", R.color.colorPrimary);
        addItem("Bandwidth", "Capacity of a network connection.\n", "قدرة اتصال الشبكة.", R.color.colorPrimary);
        addItem("Broadband", "High Capacity of internet connection\n", "قدرة عالية على الاتصال بالإنترنت", R.color.colorPrimary);
        addItem("Data Center", "Facility for storing Large Amount of information\n", "مرفق لتخزين كمية كبيرة من المعلومات", R.color.colorPrimary);
        addItem("SAP", "Common Enterprise resource Planning tool \n ", "أداة تخطيط موارد المؤسسة الشائعة", R.color.colorPrimary);
        addItem("MIDI", "( Using reference works like encyclopedias.)  \n (  standard for interconnecting electronic musical instruments and computers.)  \n", "( باستخدام مرجع يعمل مثل الموسوعات )\n( .معيار لربط الآلات الموسيقية الإلكترونية وأجهزة الكمبيوتر) .\n", R.color.colorPrimary);
        addItem("Mp3", "Downloading Music from the internet. \n ", "تحميل الموسيقى من الإنترنت",R.color.color4);
        addItem("DVD", "Watching Movie \n ", "مشاهدة فيلم", R.color.color4);
        addItem("Algorithm", "Formula used for decompressing component of data stream. \n ", "الصيغة المستخدمة لإلغاء ضغط مكون دفق البيانات", R.color.color4);
        addItem("I-Frame", "Compressed video frame that contains the complete Image Information \n ", "إطار فيديو مضغوط يحتوي على معلومات الصورة الكاملة", R.color.color4);
        addItem("JPEG", "Type of compression used for Bitmap image. \n ", "نوع الضغط المستخدم لصورة نقطية",R.color.color4);
        addItem("P-Frame", "Compressed video frame known as Predicted Frame. \n ", "إطار فيديو مضغوط يعرف باسم الإطار المتوقع", R.color.color4);
        addItem("B-Frame", "Compressed video frame that stores Changes between the frame before it and the frame after it. \n ", "إطار فيديو مضغوط يخزن التغيرات بين الإطار الذي قبله و الإطار الذي بعده", R.color.color4);
        addItem("MPEG", "( Common type of Compression used for video data ) \n ( Composing music from the internet )\n ", "( نوع شائع من الضغط المستخدم لبيانات الفيديو ) \n ( تأليف الموسيقى من الإنترنت ) \n ", R.color.color4);
        addItem("Bracketing", "Set boundaries for the beginning and end of message \n ", "تعيين حدود لبداية و نهاية الرسالة", R.color.color4);
        addItem("Checksum", "Mathematical calculation based on the content of data \n ", "حساب رياضي يعتمد على محتوي البيانات", R.color.color4);
        addItem("Half-Duplex", "Transmission mode in which each computer takes turn sending and receiving \n ", "وضع النقال الذي يتحول فيه كل كمبيوتر إلى إرسال و استقبال", R.color.color4);
        addItem("Full-Duplex", "Transmission mode in which both computers send and receive at the same time \n ", "وضع النقل الذي يقوم كلا الجهازين بإرسال و استقباله في نفس الوقت", R.color.color4);
        addItem("IRC", "Chatting to other users in real-time. \n ", "الدردشة مع المستخدمين الآخرين في الوقت الحقيقي", R.color.color4);
        addItem("Moos", "Taking part in simulation in shared environment \n ","المشاركة في المحاكاة في البيئة المشتركة", R.color.color4);
        addItem("E-Mail", "Sending and receiving message \n ", "إرسال و استقبال الرسائل", R.color.colorPrimary);
        addItem("FTP", "Downloading file from server. \n ", "تنزيل الملف من الخادم", R.color.colorPrimary);
        addItem("WWW", "Browsing web page \n ", "تصفح صفحة ويب", R.color.colorPrimary);
        addItem("Telnet", "Logging on to your computer at a distance \n ", "تسجيل الدخول إلى جهاز الكمبيوتر الخاص بك عن بعد", R.color.colorPrimary);
        addItem("Usenet", "Accessing web pages \n ", "الوصول إلى صفحة الويب", R.color.colorPrimary);
        addItem("Router", "Special computer that’s directs communications. \n ", "كومبيوتر خاص يوجه الاتصالات",   R.color.colorPrimary);
        addItem("backbone", "Main transmission path handling major data traffic \n ", "مسار النقل الرئيسي التعامل مع حركة البيانات الرئيسية", R.color.colorPrimary);
        addItem("Internet Address", "A 32-bit number identifying anode on an IP network  \n ", "رقم 32 بت يحدد الأنود على شبكةIP", R.color.colorPrimary);
        addItem("Resolution Protocol", "Standard used for software that routes data through get way \n ", "المعيار المستخدم للبرامج التي تقوم بتوجيه البيانات من خلال الحصول على الطريق", R.color.colorPrimary);
        addItem("Look-up Table", "Stored information used to route data through get way. \n ", "المعلومات المخزنة المستخدمة لتوجيه البيانات من خلال الحصول على الطريق", R.color.colorPrimary);
        addItem("Get Way", "Device for connecting dissimilar networks. \n ", "جهاز لتوصيل شبكات متباينة", R.color.colorPrimary);
        addItem("User Datagram Protocol(UDB)", "Standard used by software that moves information to the correct application on the receiving system of a network. \n ", "معيار يستخدمه البرنامج الذي ينقل المعلومات إلى التطبيق الصحيح على نظام الاستقبال الخاص بالشبكة", R.color.colorPrimary);
        addItem("Transmission Control Protocol(TCP)", "Standard used by software that manage communication exchanges between computers on the internet \n ", "المعيار المستخدم بواسطة البرامج التي تدير تبادل الاتصالات بين أجهزة الكومبيوتر على الإنترنت", R.color.colorPrimary);
        addItem("ISMTP", "Simple Mail Transfer Protocol that is used to send message between server. \n ", "بروتوكول نقل بريد بسيط يستخدم لإرسال رسالة بين الخادم", R.color.colorPrimary);
        addItem("Push' Operation", "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer \n ", "عملية نقل بريد إلكتروني يتم فيها بدء الاتصال بواسطة جهاز الكومبيوتر المرسل بدلاً من جهاز الاستقبال", R.color.colorPrimary);
        addItem("Pull' Operation", "An E-Mail Transfer Process in which the receiving computer initiates the connection \n ", " عملية نقل بريد الكتروني يقوم فيها الكبيوتر المتلقي ببدء الاتصال", R.color.colorPrimary);
        addItem("POP", "A Message-Retrieval protocol that download all E-Mail messages at the same time \n ", "بروتوكول لاإستعادةالرسائل يقوم بتنويل جميع رسائل البريد الالكتروني في نفس الوقت", R.color.colorPrimary);
        addItem("IMAP", "Mail transfer protocol that initially only retrieves the message handers \n ", "بروتوكول نقل البريد الذي يسترد مبدئياً معالجات الرسائل فقط", R.color.colorPrimary);
        addItem("Metadata", "Data about Data \n ", "بيانات حول البيانات", R.color.colorPrimary);
        addItem("GMetalanguage", "Language from which you can create other Language. \n ", "اللغة التي يمكنك من خلالها إنشاء لغة أخرى",R.color.colorPrimary);
        addItem("HTML", "example of page presentation Language. \n ", "مثال على لغة عرض الصفحة", R.color.colorPrimary);
        addItem("XML", "extensible markup Language \n ", "لغة التوصيف الموسعة", R.color.colorPrimary);
        addItem("Markup Language", "coding system used for structuring and formatting documents. \n ", "نظام الترميز المستخدم لهيكلة وتنسيق الوثائق", R.color.colorPrimary);

    }

    private void addItem(final String title, String x, String x2, int colorRes) {
        //Let's create an item with R.layout.expanding_layo1ut
        final ExpandingItem item = mExpandingList.createNewItem(R.layout.expanding_layout);

        //If item creation is successful, let's configure it
        if (item != null) {
            item.setIndicatorColorRes(colorRes);
            item.setIndicatorIconRes(R.drawable.ic_clubs_word_smash_icon);

            //It is possible to get any view inside the inflated layout. Let's set the text in the item
            ((TextView) item.findViewById(R.id.name)).setText(title);
            final ImageView imageView = item.findViewById(R.id.remove_sub_item);
            item.setStateChangedListener(expanded -> {
                if (expanded) {
                    imageView.setImageResource(R.mipmap.more);
                    rotate(-180.0f, imageView);
                } else {
                    imageView.setImageResource(R.mipmap.less);
                    rotate(180.0f, imageView);
                }
            });
            //We can create items in batch.
            item.createSubItems(1);
            for (int i = 0; i < item.getSubItemsCount(); i++) {
                //Let's get the created sub item by its index


                final View view = item.getSubItemView(i);


                //Let's set some values in
                configureSubItem(item, view, x, x2);
            }


        }


    }

    private void configureSubItem(ExpandingItem item, View view, String subItem, String subItem2) {
        ((TextView) view.findViewById(R.id.sub_title)).setText(subItem);
        ((TextView) view.findViewById(R.id.sub_title2)).setText(subItem2);


    }

    private static final int DURATION = 250;

    private void rotate(float angle, ImageView imageViewExpand1) {
        Animation animation = new RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(DURATION);
        imageViewExpand1.startAnimation(animation);
    }
}