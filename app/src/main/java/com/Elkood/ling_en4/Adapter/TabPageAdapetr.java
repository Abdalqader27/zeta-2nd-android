package com.Elkood.ling_en4.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz.Courses_Quiz_Screen;
import com.Elkood.ling_en4.Views.SecondYear.English_4.Home.Home_fragment;


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
                return new Courses_Quiz_Screen();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
