package com.Elkood.ling_en4;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.Elkood.ling_en4.EN4.Home.Home_fragment;
import com.Elkood.ling_en4.EN4.MenuListFragment;
import com.Elkood.ling_en4.EN4.Repeatrer_Quiz.Repeater_Quiz;
import com.Elkood.ling_en4.EN4.Utils.TypefaceUtil;
import com.google.android.material.tabs.TabLayout;
import com.instabug.library.Instabug;
import com.instabug.library.ui.onboarding.WelcomeMessage;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity {
//    private InterstitialAd mInterstitialAd;

    private FlowingDrawer mDrawer;
    private static Window window;
    private Toolbar toolbar;
    private Handler handler;
    private SweetAlertDialog dialog;
    private static String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/tajawal_medium.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        setContentView(R.layout.activity_main);
//        MobileAds.initialize(this,
//                "ca-app-pub-5222241590312997~8688336863");
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId("ca-app-pub-5222241590312997/5308043794");
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());


//        mBannerAd = findViewById(R.id.Interstitial_AdView);
//        AdView adView = new AdView(this);
//        adView.setAdUnitId("ca-app-pub-5222241590312997/6739656914");
//        MobileAds.initialize(this,"ca-app-pub-5222241590312997~8688336863");
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        mBannerAd.loadAd(adRequest);
        try {
            new Instabug.Builder(getApplication(), "348f181f387aa9dd9939182cdb7bdeb6")
                    .build();
            Instabug.setWelcomeMessageState(WelcomeMessage.State.DISABLED);
        } catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
        SharedPreferences preferences = getSharedPreferences("saveData", MODE_PRIVATE);
        window = getWindow();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimary));
        }
        mDrawer = findViewById(R.id.drawerlayout);
        mDrawer = findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setupToolbar();
        setupMenu();


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(getApplicationContext())
                .add("الدورات", Repeater_Quiz.class)
                .add("القائمة", menuGold.class)
                .add("الرئيسية", Home_fragment.class)

                .create());
        final TabLayout tabLayout = findViewById(R.id.tablyout);
        ViewPager viewPager = findViewById(R.id.viewPager_main);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.selectTab(tabLayout.getTabAt(2));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.color4));
                    }
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.color4));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.color4));
                }
                else if (tab.getPosition() == 1) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.backberddd));
                    }
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.backberddd));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.backberddd));
                }
                else if (tab.getPosition() == 2) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.blueberry));
                    }
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.blueberry));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.blueberry));
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
        toolbar = findViewById(R.id.toolbar);
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
//        mInterstitialAd.show();

        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {

//            mInterstitialAd.setAdListener(new AdListener() {
//                @Override
//                public void onAdClosed() {
//                    // Load the next interstitial.
//                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
//                }
//
//            });

            SweetAlertDialog dialog = new SweetAlertDialog(getWindow().getContext(), SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText(" هل حقا تريد المغادرة ")
                    .setCancelText("لا ")
                    .setConfirmText("نعم ")
                    .setConfirmClickListener(sDialog -> {
                        try {
                            MainActivity.this.finishAffinity();
                        } catch (Exception e) {
                            MainActivity.super.onBackPressed();
                        }

                        sDialog.dismiss();

                    }).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if (id == R.id.Exit) {
            dialog = new SweetAlertDialog(getWindow().getContext(), SweetAlertDialog.SUCCESS_TYPE);
            handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000);
                    try {
                        if (getContext() == null) return;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        // Please here set your event date//YYYY-MM-DD
                        Date futureDate = dateFormat.parse("2019-6-13");
                        Date currentDate = new Date();
                        if (!currentDate.after(futureDate)) {
                            long diff = futureDate.getTime()
                                    - currentDate.getTime();
                            long days = diff / (24 * 60 * 60 * 1000);
                            diff -= days * (24 * 60 * 60 * 1000);
                            long hours = diff / (60 * 60 * 1000);
                            diff -= hours * (60 * 60 * 1000);
                            long minutes = diff / (60 * 1000);
                            diff -= minutes * (60 * 1000);
                            long seconds = diff / 1000;

                            x = String.format(Locale.ENGLISH, "%02d %s %02d  :  %02d  :  %02d  ", days, "يوم   ", hours, minutes, seconds);
                            dialog.setTitleText(String.valueOf(" الفترة المتبقية " + "\n" + x))
                                    .setConfirmText("تم ");
                            dialog.setCancelable(false);
                        } else {
                            dialog.setTitleText(String.valueOf("الاصدار الحالي :  1.4"))
                                    .setConfirmText("تم ");
                            dialog.setCancelable(false);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 1000);
            dialog.setConfirmClickListener(Dialog::dismiss).show();

        }
//        else if(id==R.id.point_member){
//            SweetAlertDialog dialog = new SweetAlertDialog(getWindow().getContext(), SweetAlertDialog.NORMAL_TYPE);
//
//            dialog.setTitleText("هذه الميزة غير متوفر حاليا ربما تتوفر في التحديث القادم ")
//                    .setConfirmText("تم ")
//                    .setConfirmClickListener(Dialog::dismiss).show();        }


        return super.onOptionsItemSelected(item);
    }
//    public static void setProgresss(Context context){
//
//        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
//           dialog.setTitleText("الرجاء الأنتظار  ")
//                    .setConfirmText("تم ")
//                  .setConfirmClickListener(Dialog::dismiss).show();
//    }


}
