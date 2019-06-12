package com.Elkood.ling_en4.Home;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Elkood.ling_en4.Adapter.Adapter_Home;
import com.Elkood.ling_en4.BuilderManager;
import com.Elkood.ling_en4.Full_Quizes.QuizActivity_Full_Quiz;
import com.Elkood.ling_en4.Important_quiz.Abbrevationss.QuizActivity_Abberv;
import com.Elkood.ling_en4.Important_quiz.Compound_Nouns.QuizActivity_Comp;
import com.Elkood.ling_en4.Important_quiz.Extinsions.QuizActivity_Extinsons;
import com.Elkood.ling_en4.Important_quiz.True_false.QuizActivity_True_false;
import com.Elkood.ling_en4.Important_quiz.Vocabulary.QuizActivity_Vocabulary;
import com.Elkood.ling_en4.R;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListenerAdapter;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Home_fragment extends Fragment {

    private BoomMenuButton bmb;
    //    AdView adView;
//    private AdView mBannerAd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
//        mBannerAd = view.findViewById(R.id.banner_AdView);
//        AdView adView = new AdView(view.getContext());
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-5222241590312997/7088863473");
//        MobileAds.initialize(getContext(), "ca-app-pub-5222241590312997~8688336863");
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        mBannerAd.loadAd(adRequest);


//        MobileAds.initialize(getContext() ,"ca-app-pub-5222241590312997/3744251504");
//        adView =view.findViewById(R.id.adView);
//        AdRequest adRequest=new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//        ImageView imageView = view. findViewById(R.id.idea);
//        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.bonce);
//        imageView.startAnimation(animation);
        Button button, result;
//        result=view.findViewById(R.id.result);
        button = view.findViewById(R.id.startExam);
        Typeface font = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "fonts/tajawal_bold.ttf");
        button.setTypeface(font);
//        result.setTypeface(font);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), QuizActivity_Full_Quiz.class);
            startActivity(intent);

        });

        NestedScrollView nestedScrollView;
        nestedScrollView = view.findViewById(R.id.nested);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_home);
        ArrayList<item_home_card> list = new ArrayList<>();
        list.add(new item_home_card("الكلمات  ", " العدد : 7 وحدات   ", "هناك العديد من المفردات الجديدة الموجودة في المقرَّر التي يتوجَّب عليك حفظها  ", R.drawable.ic_studento));
        list.add(new item_home_card("المرادفات ", " العدد : 45 ", "إليك هذه المفردات بأسلوب مبسط يسهِّل عليك استذكارها  ", R.drawable.ic_tout));
        list.add(new item_home_card("صح أو خطأ ", " العدد : 36 ", " هنا يوجد عدد من الجمل التي يجب الحكم عليها بالنفي أو الإيجاب", R.drawable.ic_tutors_purchase_image));
        list.add(new item_home_card("الاختصارات ", " العدد : 25 ", "في هذا القسم سنتعرَّف على بعض المصطلحات بالشكل المختصر وبالشكل الكامل", R.drawable.ic_tutors_purchase_image2));
        list.add(new item_home_card("الأسماء المركبة ", " العدد : 20 ", "فلنطّلع سويّاً على بعض الكلمات ذات المعاني المختلفة التي تعطي بارتباطها معنى جديد", R.drawable.ic_duo_girl_tablet));
        list.add(new item_home_card("لواحق المواقع  ", " العدد : 20 ", "فلنتعرَّف على مجموعة من اللواحق التي عادةً ما تتبع لبعض المواقع ونستذكرها معاً ", R.drawable.ic_tutors_permissions_camera));
        list.add(new item_home_card("ملخص النصوص  ", " العدد : 7 وحدات   ", "إليك مختصر لأبرز النصوص الهامَّة الواردة في المقرَّر", R.drawable.ic_teacher));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        Adapter_Home adapter_home = new Adapter_Home(list, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter_home);
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.smoothScrollTo(0, 0);
        nestedScrollView.getParent().requestChildFocus(nestedScrollView, nestedScrollView);

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


//        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(
//                () -> {
//                    int x = nestedScrollView.getScrollX();
//                    int y = nestedScrollView.getScrollY();
//                    if (y > x) {
//                        bmb.setVisibility(View.GONE);
//                    } else {
//
//                        bmb.setVisibility(View.VISIBLE);
//                    }
//                });

        return view;
    }

    private void changeBoomButton(int index) {
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

//    public Typeface setType() {
//        Typeface font = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "fonts/tajawal_bold.ttf");
//        return font;
//    }


}
