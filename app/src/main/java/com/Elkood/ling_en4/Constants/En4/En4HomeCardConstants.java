package com.Elkood.ling_en4.Constants.En4;

import com.Elkood.ling_en4.Models.CardHome;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

public class En4HomeCardConstants {
    public ArrayList<CardHome> list = new ArrayList<>();

    public En4HomeCardConstants() {
        list.add(new CardHome("الكلمات  ", " العدد : 7 وحدات   ", "هناك العديد من المفردات الجديدة الموجودة في المقرَّر التي يتوجَّب عليك حفظها  ", R.drawable.ic_001_student3) );
        list.add(new CardHome("المرادفات ", " العدد : 45 ", "إليك هذه المفردات بأسلوب مبسط يسهِّل عليك استذكارها  ", R.drawable.ic_012_professor));
        list.add(new CardHome("صح أو خطأ ", " العدد : 36 ", " هنا يوجد عدد من الجمل التي يجب الحكم عليها بالنفي أو الإيجاب", R.drawable.ic_029_graduate));
        list.add(new CardHome("الاختصارات ", " العدد : 25 ", "في هذا القسم سنتعرَّف على بعض المصطلحات بالشكل المختصر وبالشكل الكامل", R.drawable.ic_031_graduate));
        list.add(new CardHome("الأسماء المركبة ", " العدد : 20 ", "فلنطّلع سويّاً على بعض الكلمات ذات المعاني المختلفة التي تعطي بارتباطها معنى جديد", R.drawable.ic_student));
        list.add(new CardHome("لواحق المواقع  ", " العدد : 20 ", "فلنتعرَّف على مجموعة من اللواحق التي عادةً ما تتبع لبعض المواقع ونستذكرها معاً ", R.drawable.ic_036_speaker));
        list.add(new CardHome("ملخص النصوص  ", " العدد : 7 وحدات   ", "إليك مختصر لأبرز النصوص الهامَّة الواردة في المقرَّر", R.drawable.ic_teacher));
    }
}
