package com.Elkood.ling_en4.Models;

public class WordsMeans {
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

    public WordsMeans(String words, String means) {
        Words = words;
        Means = means;
    }
}
