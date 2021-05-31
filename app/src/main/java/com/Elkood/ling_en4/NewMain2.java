package com.Elkood.ling_en4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.Elkood.ling_en4.Utils.TypefaceUtil;
import com.Elkood.ling_en4.Views.Home;
import com.Elkood.ling_en4.Views.Profile.Profile;
import com.Elkood.ling_en4.Views.Seach;
import com.Elkood.ling_en4.Views.TheList;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class NewMain2 extends AppCompatActivity {
    Fragment SelectFragment = null;
    MeowBottomNavigation bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main2);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/tajawal_medium.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        setDefaultFragment();
        setMeowBottomNavigation();
    }

    private void setMeowBottomNavigation() {
        SelectFragment = Home.getInstance();
        bottom_navigation = findViewById(R.id.bottomNavigation);
        bottom_navigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_user));
        bottom_navigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_student));
        bottom_navigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_search));
        bottom_navigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_home));
        bottom_navigation.show(4, true);

        bottom_navigation.setOnClickMenuListener(item -> {
            switch (item.getId()) {
                case 4:
                    SelectFragment = Home.getInstance();
                    break;
                case 2:
                    SelectFragment = TheList.getInstance();
                    break;
                case 3:
                    SelectFragment = Seach.getInstance();
                    break;
                case 1:
                    SelectFragment = Profile.getInstance();

                default:
                    break;
            }
            _setFragments();
            return null;
        });
    }

    private void _setFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Frame_Continuer, SelectFragment)
                .commit();
    }

    private void setDefaultFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Frame_Continuer, new Home())
                .commit();
    }
}