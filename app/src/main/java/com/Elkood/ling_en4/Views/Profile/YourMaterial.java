package com.Elkood.ling_en4.Views.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Elkood.ling_en4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourMaterial extends Fragment {

    public static YourMaterial getInstance()    {
        return new YourMaterial();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_your_material, container, false);
    }
}
