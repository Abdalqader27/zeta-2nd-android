package com.Elkood.ling_en4.Important_quiz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Elkood.ling_en4.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

  public class Important_Quiz extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_important__quiz, container, false);
    }

}
