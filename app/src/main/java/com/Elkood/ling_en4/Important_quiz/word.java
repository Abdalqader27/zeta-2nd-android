package com.Elkood.ling_en4.Important_quiz;

import android.os.Bundle;
import android.view.View;

import com.Elkood.ling_en4.Adapter.Adapter_Unit;
import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Utils.item_words;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class word extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        NestedScrollView nestedScrollView;
        nestedScrollView = findViewById(R.id.nested_words);

        RecyclerView recyclerView = findViewById(R.id.words_unit);
        ArrayList<item_words> list = new ArrayList<>();
        list.add(new item_words("Unit 8", "عدد :  51"));
        list.add(new item_words("Unit 9", "عدد :  68"));
        list.add(new item_words("Unit 10", "عدد :  0"));
        list.add(new item_words("Unit 11", "عدد :  119"));
        list.add(new item_words("Unit 12", "عدد :  50"));
        list.add(new item_words("Unit 13", "عدد :  105"));
        list.add(new item_words("Unit 14", "عدد :  84"));




        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setHasFixedSize(true);
        Adapter_Unit adapter_home = new Adapter_Unit(list, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.smoothScrollTo(0, 0);
        nestedScrollView.getParent().requestChildFocus(nestedScrollView, nestedScrollView);

    }
}
