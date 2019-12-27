package com.Elkood.ling_en4.EN4.Utils;

public class item_words {
    private String Words;
    private String Means;

    public String getWords() {
        return Words;
    }

    public void setWords(String words) {
        Words = words;
    }

    public String getMeans() {
        return Means;
    }

    public void setMeans(String means) {
        Means = means;
    }

    public item_words(String words, String means) {
        Words = words;
        Means = means;
    }
}
