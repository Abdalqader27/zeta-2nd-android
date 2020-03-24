package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Vocabulary;

import android.provider.BaseColumns;

final class QuaizContract_Vocabulary {
    private QuaizContract_Vocabulary() {
    }

    public static class QuestionTabel implements BaseColumns {
        public static final String Tabel_Name = "quaiz_quation_voca";
        public static final String COLMUN_QUESTION = "question";
        public static final String COLMUN_OPTION1 = "option1";
        public static final String COLMUN_OPTION2 = "option2";
        public static final String COLMUN_OPTION3 = "option3";
        public static final String COLMUN_OPTION4 = "option4";
        public static final String COLMUN_ANSWER_NR = "answer_anr";


    }
}
