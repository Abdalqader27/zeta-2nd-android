package com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz.Extinsions;

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

public class Extinsions extends AppCompatActivity {
    private ExpandingList mExpandingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extinsions);
        mExpandingList = findViewById(R.id.expanding_list_main_extin);
        createItems();
    }
    private void createItems() {
        addItem(".aero", "aviation industry \n","صناعة الطيران", R.color.colorPrimary);
        addItem(".biz", "businesses \n","الأعمال", R.color.colorPrimary);
        addItem(".com (.co in UK)", "commercial \n","تجاري", R.color.colorPrimary);
        addItem(".coop", "cooperatives \n","التعاونيات", R.color.colorPrimary);
        addItem(".edu (.ac in UK)", "educational and research \n","التعليمية والبحثية", R.color.colorPrimary);
        addItem(".gov", "government\n","الحكومي", R.color.colorPrimary);
        addItem(".info", "general use\n","استخدام عام", R.color.colorPrimary);
        addItem(".int", "international organization \n","منظمة عالمية", R.color.color4);
        addItem(".mil", "military agency \n","وكالة عسكرية", R.color.color4);
        addItem(".museums", "museums\n","المتاحف", R.color.color4);
        addItem(".name", "individuals\n","الأفراد", R.color.color4);
        addItem(".net ", "gateway or host\n","بوابة أو المضيف", R.color.color4);
        addItem(".org", "non-profit organization\n","منظمة غير ربحية", R.color.color4);
        addItem(".pro", "Professionals\n","المهنيين",  R.color.color4);
        addItem(".firm", "Informative\n","غنيا بالمعلومات", R.color.color4);
        addItem(".store", "Cultural or entertainment\n","ثقافي أو ترفيهي", R.color.color4);
        addItem(".web", "Personal\n","الشخصية", R.color.color4);
        addItem(".arts", "Film or agency\n","فيلم أو وكالة", R.color.color4);
        addItem(".rec", "Online retail shop\n","متجر التجزئة على الإنترنت", R.color.color4);
        addItem(".nom", "recreational\n","ترفيهية", R.color.color4);
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

    private void configureSubItem(ExpandingItem item, View view, String subItem,String subItem2) {
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
