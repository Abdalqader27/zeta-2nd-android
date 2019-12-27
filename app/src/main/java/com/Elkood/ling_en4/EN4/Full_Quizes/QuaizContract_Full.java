package com.Elkood.ling_en4.EN4.Full_Quizes;


import android.provider.BaseColumns;

final class QuaizContract_Full {
    private QuaizContract_Full() {
    }

    public static class QuestionTabel implements BaseColumns {
        public static final String Tabel_Name = "quaiz_quation_Full";
        public static final String COLMUN_QUESTION = "question_full";
        public static final String COLMUN_OPTION1 = "option1_full";
        public static final String COLMUN_OPTION2 = "option2_full";
        public static final String COLMUN_OPTION3 = "option3_full";
        public static final String COLMUN_OPTION4 = "option4_full";
        public static final String COLMUN_ANSWER_NR = "answer_anr_full";


    }
}
