package com.Elkood.ling_en4.Important_quiz.Compound_Nouns;

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

public class Compound_Nouns extends AppCompatActivity {
    private ExpandingList mExpandingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound__nouns);
        mExpandingList = findViewById(R.id.expanding_list_main_comp);
        createItems();
    }
    private void createItems() {
        //Vocablury
        addItem("Barcode", "Reader \n","قارئ", R.color.colorPrimary);
        addItem("Mainframe", "Computer\n","حاسوب", R.color.colorPrimary);
        addItem("Laser", "Printer\n","طابعة", R.color.colorPrimary);
        addItem("Expansion", "Card\n","بطاقة", R.color.colorPrimary);
        addItem("Search", "Engine\n","محرّك", R.color.colorPrimary);
        addItem("Control", "Bus\n","نقل", R.color.colorPrimary);
        addItem("Supervisor", "Program\n","برنامج", R.color.colorPrimary);
        addItem("Task", "Bar\n","شريط", R.color.color4);
        addItem("System", "Tray\n","علبة", R.color.color4);
        addItem("Explorer", "Program\n","برنامج", R.color.color4);
        addItem("Bulletin", "Board\n","لوح", R.color.color4);
        addItem("Domain ", "Name\n","اسم ", R.color.color4);
        addItem("File", "Name\n","اسم", R.color.color4);
        addItem("Graphical", "Button\n","زر", R.color.color4);
        addItem("Mobile", "Phone\n","هاتف", R.color.color4);
        addItem("Search", "Engine\n","محرّك", R.color.color4);
        addItem("Site", "Map\n","خريطة", R.color.color4);
        addItem("Synchronous", "Transmissionn\n","انتقال", R.color.color4);
        addItem("Text", "Message\n","رسالة", R.color.color4);
        addItem("Web", "Page\n","صفحة", R.color.color4);

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

    private void configureSubItem(ExpandingItem item, View view, String subItem,String s) {
        ((TextView) view.findViewById(R.id.sub_title)).setText(subItem);
        ((TextView) view.findViewById(R.id.sub_title2)).setText(s);

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
