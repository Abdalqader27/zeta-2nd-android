package com.Elkood.ling_en4.data.model

/** The nine legacy "exam paper" variants, in card display order (newest first).
 * R2017/R2017_ALT = legacy r2017/r2017_2; R2016/R2016_ALT = legacy r2016/R2016_3. */
enum class ExamPaper(val cardTitle: String, val cardSubtitle: String) {
    R2018("دورة 2018 ", "الدورة الاولى فقط "),
    R2017("دورة 2017 ", "الأولى "),
    R2017_ALT("دورة 2017 ", "الثانية "),
    R2016("دورة 2016 ", "الأولى "),
    R2016_ALT("دورة 2016 ", "التكميلية"),
    R2015("دورة 2015 ", "الدورة الاولى فقط "),
    R2014("دورة 2014 ", "الدورة الاولى فقط  "),
    R2013("دورة 2013 ", "الدورة الاولى فقط "),
    R2012("دورة 2012 ", "الدورة الثالثة فقط "),
}
