package com.Elkood.ling_en4.EN4.Home;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.Elkood.ling_en4.EN4.Full_Quizes.QuizActivity_Full_Quiz;
import com.Elkood.ling_en4.R;

import java.util.Objects;

public class Home_fragment extends Fragment {


    //    AdView adView;
//    private AdView mBannerAd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
//        mBannerAd = view.findViewById(R.id.banner_AdView);
//        AdView adView = new AdView(view.getContext());
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-5222241590312997/7088863473");
//        MobileAds.initialize(getContext(), "ca-app-pub-5222241590312997~8688336863");
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        mBannerAd.loadAd(adRequest);


//        MobileAds.initialize(getContext() ,"ca-app-pub-5222241590312997/3744251504");
//        adView =view.findViewById(R.id.adView);
//        AdRequest adRequest=new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//        ImageView imageView = view. findViewById(R.id.idea);
//        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.bonce);
//        imageView.startAnimation(animation);
        Button button, result;
//        result=view.findViewById(R.id.result);
        button = view.findViewById(R.id.startExam);
        Typeface font = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "fonts/tajawal_bold.ttf");
        button.setTypeface(font);
//        result.setTypeface(font);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), QuizActivity_Full_Quiz.class);
            startActivity(intent);

        });

        NestedScrollView nestedScrollView;
        nestedScrollView = view.findViewById(R.id.nested);


        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.smoothScrollTo(0, 0);
        nestedScrollView.getParent().requestChildFocus(nestedScrollView, nestedScrollView);




//        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(
//                () -> {
//                    int x = nestedScrollView.getScrollX();
//                    int y = nestedScrollView.getScrollY();
//                    if (y > x) {
//                        bmb.setVisibility(View.GONE);
//                    } else {
//
//                        bmb.setVisibility(View.VISIBLE);
//                    }
//                });

        return view;
    }


//    public Typeface setType() {
//        Typeface font = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "fonts/tajawal_bold.ttf");
//        return font;
//    }


}
