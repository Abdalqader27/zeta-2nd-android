package com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz.Compound_Nouns;

import android.provider.BaseColumns;

final class QuaizContract_comp {
    private QuaizContract_comp() {
    }

    public static class QuestionTabel implements BaseColumns {
        public static final String Tabel_Name = "quaiz_quation_comp";
        public static final String COLMUN_QUESTION = "question_comp";
        public static final String COLMUN_OPTION1 = "option1_comp";
        public static final String COLMUN_OPTION2 = "option2_comp";
        public static final String COLMUN_OPTION3 = "option3_comp";
        public static final String COLMUN_OPTION4 = "option4_comp";
        public static final String COLMUN_ANSWER_NR = "answer_anr_comp";


    }
}
