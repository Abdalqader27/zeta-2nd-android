package com.Elkood.ling_en4.Adapter;


import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Home.Home_fragment;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz.Repeater_Quiz;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


class TabPageAdapetr extends FragmentStatePagerAdapter {

    public TabPageAdapetr(FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                return new Home_fragment();


            case 1:
                return new Repeater_Quiz();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
