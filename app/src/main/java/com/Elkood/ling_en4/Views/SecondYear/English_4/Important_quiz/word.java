package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Unit;
import com.Elkood.ling_en4.Constants.En4.En4WordsConstants;
import com.Elkood.ling_en4.R;

public class word extends AppCompatActivity {
    private En4WordsConstants en4WordsConstants =new En4WordsConstants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        NestedScrollView nestedScrollView;

        nestedScrollView = findViewById(R.id.nested_words);

        RecyclerView recyclerView = findViewById(R.id.words_unit);
//        ArrayList<WordsMeans> list = new ArrayList<>();
//        list.add(new WordsMeans("Unit 8", "عدد :  51"));
//        list.add(new WordsMeans("Unit 9", "عدد :  68"));
//        list.add(new WordsMeans("Unit 10", "عدد :  0"));
//        list.add(new WordsMeans("Unit 11", "عدد :  119"));
//        list.add(new WordsMeans("Unit 12", "عدد :  50"));
//        list.add(new WordsMeans("Unit 13", "عدد :  105"));
//        list.add(new WordsMeans("Unit 14", "عدد :  84"));
//
//

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setHasFixedSize(true);
        Adapter_Unit adapter_home = new Adapter_Unit(en4WordsConstants.wordsMeansArrayList, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter_home);
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.smoothScrollTo(0, 0);
        nestedScrollView.getParent().requestChildFocus(nestedScrollView, nestedScrollView);

    }
}
