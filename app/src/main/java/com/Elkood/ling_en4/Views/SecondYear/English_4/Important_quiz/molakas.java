package com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.molakhs.munit11;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.molakhs.munit12;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.molakhs.munit13;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.molakhs.munit14;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.molakhs.munit8;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.molakhs.munit9;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import devlight.io.library.ntb.NavigationTabBar;

public class molakas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_molakas);
        initUI();
    }

    private void initUI() {
        final ViewPager viewPager = findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 7;
            }

            @Override
            public boolean isViewFromObject(@NonNull final View view, @NonNull final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(@NonNull final View container, final int position, @NonNull final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp_list, null, false);


                container.addView(view);
                return view;
            }
        });

        final String colors = "#0FB2C0";

        final NavigationTabBar navigationTabBar = findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_books_stack),
                        Color.parseColor(colors))
                        .title("Unit 8 ")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_books_stack),
                        Color.parseColor(colors))
                        .title("Unit 9 ")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_books_stack),
                        Color.parseColor(colors))
                        .title("Unit 11 ")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_books_stack),
                        Color.parseColor(colors))
                        .title("Unit 12 ")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_books_stack),
                        Color.parseColor(colors))
                        .title("Unit 13 ")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_books_stack),
                        Color.parseColor(colors))
                        .title("Unit 14 ")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.continer, new munit8()).commit();
        navigationTabBar.post(() -> {
            View viewPager1 = findViewById(R.id.vp_horizontal_ntb);
            ((ViewGroup.MarginLayoutParams) viewPager1.getLayoutParams()).topMargin =
                    (int) -navigationTabBar.getBadgeMargin();
            viewPager1.requestLayout();
        });
        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
                if (index == 0) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                            .replace(R.id.continer, new munit8()).commit();
                } else if (index == 1) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                            .replace(R.id.continer, new munit9()).commit();
                } else if (index == 2) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                            .replace(R.id.continer, new munit11()).commit();
                } else if (index == 3) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                            .replace(R.id.continer, new munit12()).commit();
                } else if (index == 4) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                            .replace(R.id.continer, new munit13()).commit();
                } else if (index == 5) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                            .replace(R.id.continer, new munit14()).commit();
                }
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
            }
        });

    }

}
