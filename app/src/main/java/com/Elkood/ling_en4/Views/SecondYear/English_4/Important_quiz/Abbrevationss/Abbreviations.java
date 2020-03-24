package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Abbrevationss;

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

public class Abbreviations extends AppCompatActivity {
    private ExpandingList mExpandingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abbreviations);
        mExpandingList = findViewById(R.id.expanding_list_main_Abber);
        createItems();
    }
        private void createItems() {
            //Vocablury
            addItem("GPS", "Global Positioning System\n","نظام تحديد المواقع العالمي", R.color.colorPrimary);
            addItem("XML", "eXtensible Markup Language\n","لغة التوصيف الموسعة", R.color.colorPrimary);
            addItem("IMAP", "Internet Mail Access Protocol\n","بروتوكول الوصول إلى بريد الإنترنت", R.color.colorPrimary);
            addItem("LAN", "Local Access Network.\n","شبكة المنطقة المحلية.", R.color.colorPrimary);
            addItem("FTP", "File Transfer Protocol\n","بروتكول نقل الملفات", R.color.colorPrimary);
            addItem("MP3", "MPEG Audio Layer 3\n","MPEG أغنية طبقة 3", R.color.colorPrimary);
            addItem("TA", "Terminal Adapter\n","محول المحطة", R.color.colorPrimary);
            addItem("DSL", "Digital Subscriber Line\n","خط المشترك الرقمي", R.color.color4);
            addItem("SMS", "Short Message Server\n","خادم الرسائل القصيرة", R.color.color4);
            addItem("POP", "Post Office Protocol\n","بروتوكول مكتب البريد", R.color.color4);
            addItem("IP", "Internet Protocol\n","بروتوكول إنترنت", R.color.color4);
            addItem("ASP ", "Application Server Providers\n","مزودو خادم التطبيقات", R.color.color4);
            addItem("NIC", "Network Internet Card\n","بطاقة شبكة الإنترنت", R.color.color4);
            addItem("WWW", "World Web\n","الشبكة العالمية", R.color.color4);
            addItem("TCP", "Transmission Control Protocol\n","بروتوكول التحكم بالإرسال", R.color.color4);
            addItem("SMTP", "Simple Mail Transfer Protocol\n","بروتوكول نقل الإيميل البسيط", R.color.color4);
            addItem("UDP", "User Datagram Protocol\n","بروتوكول مخطط المستخدم", R.color.color4);
            addItem("DNS", "Domain Name System\n","نظام اسم المجال", R.color.color4);
            addItem("HTML", "Hyper Text Markup Language\n","لغة ترميز النصوص التشعبية", R.color.color4);
            addItem("SGML", "Standard Generalized Markup Language\n","لغة الترميز المعممة القياسية", R.color.color4);
            addItem("DTP", "Desktop Publisher\n","الناشر المكتبي", R.color.color4);
            addItem("ISP", "Internet Server Provider.\n","مزود خدمة الانترنت", R.color.color4);
            addItem("IRC", "Internet Relay Chat\n","دردشة ترحيل الانترنت", R.color.color4);
            addItem("PIM", "Personal Information Manager\n","مدير المعلومات الشخصية", R.color.colorPrimary);
            addItem("DVD", "Digital Video Disk\n","قرص فيديو رقمي", R.color.colorPrimary);

        }
    private void addItem(final String title, String x,String x2, int colorRes) {
        //Let's create an item with R.layout.expanding_layout
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
                configureSubItem(item, view, x,x2);
            }


        }


    }

    private void configureSubItem(ExpandingItem item, View view, String subItem,String subitem2) {
        ((TextView) view.findViewById(R.id.sub_title)).setText(subItem);
        ((TextView) view.findViewById(R.id.sub_title2)).setText(subitem2);


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
