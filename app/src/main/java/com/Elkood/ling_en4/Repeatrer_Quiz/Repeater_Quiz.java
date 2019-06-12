package com.Elkood.ling_en4.Repeatrer_Quiz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Elkood.ling_en4.Adapter.Adapter_repeater_Quiz;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Repeater_Quiz extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_repeater__quiz, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.repeater_quiz_recycle);
        ArrayList<item_repeater> list = new ArrayList<>();
        list.add(new item_repeater("دورة 2018 ", " ", "الدورة الاولى فقط "));
        list.add(new item_repeater("دورة 2017 ", " ", "الدورة الاولى + الثانية  "));
        list.add(new item_repeater("دورة 2016 ", "", "الدورة الاولى  + التكميلية  "));
        list.add(new item_repeater("دورة 2015 ", " ", "الدورة الاولى فقط "));
        list.add(new item_repeater("دورة 2014 ", "", "الدورة الاولى فقط  "));
        list.add(new item_repeater("دورة 2013 ", " ", "الدورة الاولى فقط "));
        list.add(new item_repeater("دورة 2012 ", "", "الدورة الثالثة فقط "));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setHasFixedSize(true);
        Adapter_repeater_Quiz repeater_quiz = new Adapter_repeater_Quiz(list, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(repeater_quiz);

        return view;
    }


}
