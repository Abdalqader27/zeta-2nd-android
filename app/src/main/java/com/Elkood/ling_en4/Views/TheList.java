package com.Elkood.ling_en4.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Views.FirstYear.FirstYearAct;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheList extends Fragment {
    private ViewGroup firstYear, secondYear, thirdYear, fourthYear, fiveYear;


    public static TheList getInstance() {
        return new TheList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_the_list, container, false);
        initUi(view);
        setClick(view);


        return view;
    }

    private void initUi(View view) {
        firstYear = view.findViewById(R.id.firstYear);
        secondYear = view.findViewById(R.id.secondYear);
        thirdYear = view.findViewById(R.id.thirdYear);
        fourthYear = view.findViewById(R.id.fourthYear);
        fiveYear = view.findViewById(R.id.fiveYear);
    }

    private void setClick(View view) {
        firstYear.setOnClickListener(v -> {
            Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(),
                    FirstYearAct.class);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
        secondYear = view.findViewById(R.id.secondYear);
        thirdYear = view.findViewById(R.id.thirdYear);
        fourthYear = view.findViewById(R.id.fourthYear);
        fiveYear = view.findViewById(R.id.fiveYear);
    }
}
