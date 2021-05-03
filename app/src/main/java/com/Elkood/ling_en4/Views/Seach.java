package com.Elkood.ling_en4.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Elkood.ling_en4.R;

public class Seach extends Fragment {

    public static Seach getInstance()    {
        return new Seach();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seach, container, false);
    }
}
