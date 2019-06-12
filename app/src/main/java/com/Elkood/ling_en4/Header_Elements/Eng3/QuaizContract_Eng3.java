package com.Elkood.ling_en4.Header_Elements.Eng3;

import android.provider.BaseColumns;

final class QuaizContract_Eng3 {
    private QuaizContract_Eng3() {
    }

    public static class QuestionTabel implements BaseColumns {
        public static final String Tabel_Name = "quaiz_quation";
        public static final String COLMUN_QUESTION = "question";
        public static final String COLMUN_OPTION1 = "option1";
        public static final String COLMUN_OPTION2 = "option2";
        public static final String COLMUN_OPTION3 = "option3";
        public static final String COLMUN_OPTION4 = "option4";
        public static final String COLMUN_ANSWER_NR = "answer_anr";


    }
}
