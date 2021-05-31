package com.Elkood.ling_en4.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Views.FirstYear.FirstYearAct;
import com.Elkood.ling_en4.Views.SecondYear.SecondYearActivity;

import java.util.Objects;

public class TheList extends Fragment {
    Context context;

    public static TheList getInstance() {
        return new TheList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_the_list, container, false);
        initUi();
        setClick(view);


        return view;
    }

    private void initUi() {
        context = requireActivity().getApplicationContext();
    }


    private void setClick(View view) {
        view.findViewById(R.id.firstYear).setOnClickListener(v -> {
            Intent intent = new Intent(context, FirstYearAct.class);
            requireActivity().startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        view.findViewById(R.id.fourthYear).setOnClickListener(v -> {
            Intent intent = new Intent(context, SecondYearActivity.class);
            requireActivity().startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}
