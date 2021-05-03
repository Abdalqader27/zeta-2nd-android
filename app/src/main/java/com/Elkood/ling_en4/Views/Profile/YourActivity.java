package com.Elkood.ling_en4.Views.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Elkood.ling_en4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourActivity extends Fragment {

    public YourActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_activity, container, false);
    }
}
