package com.Elkood.ling_en4.Views.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.Elkood.ling_en4.Adapter.SectionPagerAdapter;
import com.Elkood.ling_en4.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Profile extends Fragment {
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView settings;
    private  TextView navUsername;
    private static SharedPreferences preferences;

    public static Profile getInstance() {
        return new Profile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        preferences = view.getContext().getSharedPreferences("saveData", MODE_PRIVATE);

        initUI(view);
        setClicks();
        setName();
        return view;

    }

    private void initUI(View view) {
        viewPager = view.findViewById(R.id.viewPager_main);
        tabLayout = view.findViewById(R.id.tablyout);
        settings=view.findViewById(R.id.action_settings);
        navUsername=view.findViewById(R.id.theDonkey);
    }
    private  void setClicks(){
        settings.setOnClickListener(v->{
            Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.Settings.class);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.selectTab(tabLayout.getTabAt(2));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new YourScores(), "النتائج");
        adapter.addFragment(new YourActivity(), "نشاطاتك");
        adapter.addFragment(new YourMaterial(), "موادك");
        viewPager.setAdapter(adapter);
    }
    private void setName() {
        navUsername.setText(preferences.getString("Name", ""));

    }

}
