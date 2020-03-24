package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.AdapterHome;
import com.Elkood.ling_en4.Constants.En4.En4HomeCardConstants;
import com.Elkood.ling_en4.R;
import com.Elkood.ling_en4.Utils.BuilderManager;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Abbrevationss.QuizActivity_Abberv;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Compound_Nouns.QuizActivity_Comp;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Extinsions.QuizActivity_Extinsons;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.True_false.QuizActivity_True_false;
import com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Vocabulary.QuizActivity_Vocabulary;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListenerAdapter;

import java.util.Objects;


/**
 *
 */
public class MenuQuiz extends Fragment {
    private BoomMenuButton bmb;
    private En4HomeCardConstants en4HomeCardConstants = new En4HomeCardConstants();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterHome adapter_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_gold, container, false);
        initItems(view);

        setAdapter_home();

        setBmb();

        return view;
    }

    private void initItems(View view) {
        recyclerView = view.findViewById(R.id.recycle_home);
        layoutManager = new LinearLayoutManager(getContext());
        adapter_home = new AdapterHome(en4HomeCardConstants.list, getContext());
        bmb = view.findViewById(R.id.bmb);
    }

    private void setAdapter_home() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter_home);
    }

    private void setBmb() {

        assert bmb != null;
        bmb.setUnableColor(getResources().getColor(R.color.color4));
        bmb.setDimColor(getResources().getColor(R.color.blueberry));
        bmb.setShadowColor(getResources().getColor(R.color.color4));
        bmb.setUnableColor(getResources().getColor(R.color.backber));
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("أسئلة اختبار المرادفات ", "45 سؤال فقط ").normalImageRes(R.drawable.ic_tout).unableColor(R.color.backber));

        bmb.addBuilder(BuilderManager.getHamButtonBuilder("أسئلة اختبار الصح و الخطأ", "36 سؤال فقط")
                .normalImageRes(R.drawable.ic_tutors_purchase_image).unableColor(R.color.backber));
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("أسئلة اختبار الاختصارات", "23  سؤال فقط")
                .normalImageRes(R.drawable.ic_tutors_purchase_image2).unableColor(R.color.backber));

        bmb.addBuilder(BuilderManager.getHamButtonBuilder("أسئلة اختبار الأسماء المركبة", "20  سؤال فقط")
                .normalImageRes(R.drawable.ic_duo_girl_tablet).unableColor(R.color.backber));


        bmb.addBuilder(BuilderManager.getHamButtonBuilder("أسئلة اختبار لواحق المواقع", "20  سؤال فقط").
                normalImageRes(R.drawable.ic_tutors_permissions_camera).unableColor(R.color.backber));


        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                super.onClicked(index, boomButton);
                changeBoomButton(index);
            }
        });
    }

    private void changeBoomButton(int index) {
        HamButton.Builder builder = (HamButton.Builder) bmb.getBuilder(index);
        if (index == 0) {
            Intent intent = new Intent(getContext(), QuizActivity_Vocabulary.class);
            Objects.requireNonNull(getView()).getContext().startActivity(intent);
        } else if (index == 1) {
            Intent intent = new Intent(getContext(), QuizActivity_True_false.class);
            Objects.requireNonNull(getView()).getContext().startActivity(intent);

        } else if (index == 2) {
            Intent intent = new Intent(getContext(), QuizActivity_Abberv.class);
            Objects.requireNonNull(getView()).getContext().startActivity(intent);
        } else if (index == 3) {
            Intent intent = new Intent(getContext(), QuizActivity_Comp.class);
            Objects.requireNonNull(getView()).getContext().startActivity(intent);

        } else if (index == 4) {
            Intent intent = new Intent(getContext(), QuizActivity_Extinsons.class);
            Objects.requireNonNull(getView()).getContext().startActivity(intent);
        }

    }


}
