package com.Elkood.ling_en4;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.EN4.Adapter.Adapter_Home;
import com.Elkood.ling_en4.EN4.BuilderManager;
import com.Elkood.ling_en4.EN4.Home.item_home_card;
import com.Elkood.ling_en4.EN4.Important_quiz.Abbrevationss.QuizActivity_Abberv;
import com.Elkood.ling_en4.EN4.Important_quiz.Compound_Nouns.QuizActivity_Comp;
import com.Elkood.ling_en4.EN4.Important_quiz.Extinsions.QuizActivity_Extinsons;
import com.Elkood.ling_en4.EN4.Important_quiz.True_false.QuizActivity_True_false;
import com.Elkood.ling_en4.EN4.Important_quiz.Vocabulary.QuizActivity_Vocabulary;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListenerAdapter;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class menuGold extends Fragment {
    private BoomMenuButton bmb;

    public menuGold() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_gold, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_home);
        ArrayList<item_home_card> list = new ArrayList<>();
        list.add(new item_home_card("الكلمات  ", " العدد : 7 وحدات   ", "هناك العديد من المفردات الجديدة الموجودة في المقرَّر التي يتوجَّب عليك حفظها  ", R.drawable.ic_001_student3));
        list.add(new item_home_card("المرادفات ", " العدد : 45 ", "إليك هذه المفردات بأسلوب مبسط يسهِّل عليك استذكارها  ", R.drawable.ic_012_professor));
        list.add(new item_home_card("صح أو خطأ ", " العدد : 36 ", " هنا يوجد عدد من الجمل التي يجب الحكم عليها بالنفي أو الإيجاب", R.drawable.ic_029_graduate));
        list.add(new item_home_card("الاختصارات ", " العدد : 25 ", "في هذا القسم سنتعرَّف على بعض المصطلحات بالشكل المختصر وبالشكل الكامل", R.drawable.ic_031_graduate));
        list.add(new item_home_card("الأسماء المركبة ", " العدد : 20 ", "فلنطّلع سويّاً على بعض الكلمات ذات المعاني المختلفة التي تعطي بارتباطها معنى جديد", R.drawable.ic_student));
        list.add(new item_home_card("لواحق المواقع  ", " العدد : 20 ", "فلنتعرَّف على مجموعة من اللواحق التي عادةً ما تتبع لبعض المواقع ونستذكرها معاً ", R.drawable.ic_036_speaker));
        list.add(new item_home_card("ملخص النصوص  ", " العدد : 7 وحدات   ", "إليك مختصر لأبرز النصوص الهامَّة الواردة في المقرَّر", R.drawable.ic_teacher));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        Adapter_Home adapter_home = new Adapter_Home(list, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter_home);
        bmb = view.findViewById(R.id.bmb);

        assert bmb != null;
        bmb.setUnableColor(getResources().getColor(R.color.color4));
        bmb.setDimColor(getResources().getColor(R.color.blueberry));
        bmb.setShadowColor(getResources().getColor(R.color.color4));
        bmb.setUnableColor(getResources().getColor(R.color.backber));
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("أسئلة اختبار المرادفات ", "45 سؤال فقط ")
                .normalImageRes(R.drawable.ic_tout).unableColor(R.color.backber));

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
        return view;
    }    private void changeBoomButton(int index) {
        // From version 2.0.9, BMB supports a new feature to change contents in boom-button
        // by changing contents in the corresponding builder.
        // Please notice that not every method supports this feature. Only the method whose comment
        // contains the "Synchronicity" tag supports.
        // For more details, check:
        // https://github.com/Nightonke/BoomMenu/wiki/Change-Boom-Buttons-Dynamically
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
