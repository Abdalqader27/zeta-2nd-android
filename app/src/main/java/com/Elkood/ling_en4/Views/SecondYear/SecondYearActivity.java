package com.Elkood.ling_en4.Views.SecondYear;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Views.SecondYear.English_4.Home.Home_fragment;
import com.Elkood.ling_en4.Views.SecondYear.English_4.Home.MenuListFragment;
import com.Elkood.ling_en4.Views.SecondYear.English_4.BankItemsQuiz;
import com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz.Courses_Quiz_Screen;
import com.google.android.material.tabs.TabLayout;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static java.security.AccessController.getContext;

public class SecondYearActivity extends AppCompatActivity {
    Toolbar  toolbar;
    ViewPager viewPager;
    FlowingDrawer mDrawer;
    private SweetAlertDialog dialog;
    private static String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_year);
        toolbar=findViewById(R.id.toolbar);
        viewPager=findViewById(R.id.viewPager_main);

        mDrawer=findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setupToolbar();
        setupMenu();


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(getApplicationContext())
                .add("الدورات", Courses_Quiz_Screen.class)
                .add("القائمة", BankItemsQuiz.class)
                .add("الرئيسية", Home_fragment.class)
                .create());


        final TabLayout tabLayout = findViewById(R.id.tablyout);
        viewPager=findViewById(R.id.viewPager_main);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.selectTab(tabLayout.getTabAt(2));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 1) {

                    toolbar.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                            R.drawable.guide_check_in_wave_4));

                } else {
                    toolbar.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wave_5));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void setupToolbar() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_three_horizontal_lines_symbol);
        toolbar.setNavigationOnClickListener(v -> mDrawer.toggleMenu());
    }


    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            SweetAlertDialog dialog = new SweetAlertDialog(getWindow().getContext(), SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText(" هل حقا تريد المغادرة ")
                    .setCancelText("لا ")
                    .setConfirmText("نعم ")
                    .setConfirmClickListener(sDialog -> {
                        super.onBackPressed();
                        sDialog.dismiss();
                    }).show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//        int id = item.getItemId();
//        if (id == R.id.Exit) {
//            dialog = new SweetAlertDialog(getWindow().getContext(), SweetAlertDialog.SUCCESS_TYPE);
//            handler = new Handler();
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    handler.postDelayed(this, 1000);
//                    try {
//                        if (getContext() == null) return;
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//                        // Please here set your event date//YYYY-MM-DD
//                        Date futureDate = dateFormat.parse("2019-6-13");
//                        Date currentDate = new Date();
//                        if (!currentDate.after(futureDate)) {
//                            assert futureDate != null;
//                            long diff = futureDate.getTime()
//                                    - currentDate.getTime();
//                            long days = diff / (24 * 60 * 60 * 1000);
//                            diff -= days * (24 * 60 * 60 * 1000);
//                            long hours = diff / (60 * 60 * 1000);
//                            diff -= hours * (60 * 60 * 1000);
//                            long minutes = diff / (60 * 1000);
//                            diff -= minutes * (60 * 1000);
//                            long seconds = diff / 1000;
//
//                            x = String.format(Locale.ENGLISH, "%02d %s %02d  :  %02d  :  %02d  ", days, "يوم   ", hours, minutes, seconds);
//                            dialog.setTitleText(" الفترة المتبقية " + "\n" + x)
//                                    .setConfirmText("تم ");
//                        } else {
//                            dialog.setTitleText("الاصدار الحالي :  1.4")
//                                    .setConfirmText("تم ");
//                        }
//                        dialog.setCancelable(false);
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            handler.postDelayed(runnable, 1000);
//            dialog.setConfirmClickListener(Dialog::dismiss).show();
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}