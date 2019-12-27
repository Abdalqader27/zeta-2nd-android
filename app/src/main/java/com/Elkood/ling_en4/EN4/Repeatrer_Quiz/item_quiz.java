package com.Elkood.ling_en4.EN4.Repeatrer_Quiz;

import android.widget.ImageButton;

public class item_quiz {
    private String quiz;
    private int R1_check;
    private int R2_check;
    private int R3_check;
    private int R4_check;
    private String R1_text;
    private String R2_text;
    private String R3_text;
    private String R4_text;
    private ImageButton button;

    public ImageButton getButton() {
        return button;
    }

    public void setButton(ImageButton button) {
        this.button = button;
    }

    public item_quiz(String quiz, int r1_check, int r2_check, int r3_check, int r4_check, String r1_text, String r2_text, String r3_text, String r4_text, ImageButton button) {
        this.quiz = quiz;
        R1_check = r1_check;
        R2_check = r2_check;
        R3_check = r3_check;
        R4_check = r4_check;
        R1_text = r1_text;
        R2_text = r2_text;
        R3_text = r3_text;
        R4_text = r4_text;
        this.button = button;
    }

    public item_quiz(String quiz, int r1_check, int r2_check, int r3_check, int r4_check, String r1_text, String r2_text, String r3_text, String r4_text) {
        this.quiz = quiz;
        R1_check = r1_check;
        R2_check = r2_check;
        R3_check = r3_check;
        R4_check = r4_check;
        R1_text = r1_text;
        R2_text = r2_text;
        R3_text = r3_text;
        R4_text = r4_text;
    }

    public String getR1_text() {
        return R1_text;
    }

    public void setR1_text(String r1_text) {
        R1_text = r1_text;
    }

    public String getR2_text() {
        return R2_text;
    }

    public void setR2_text(String r2_text) {
        R2_text = r2_text;
    }

    public String getR3_text() {
        return R3_text;
    }

    public void setR3_text(String r3_text) {
        R3_text = r3_text;
    }

    public String getR4_text() {
        return R4_text;
    }

    public void setR4_text(String r4_text) {
        R4_text = r4_text;
    }

    public item_quiz(String quiz, int r1_check, int r2_check, int r3_check, int r4_check) {
        this.quiz = quiz;
        R1_check = r1_check;
        R2_check = r2_check;
        R3_check = r3_check;
        R4_check = r4_check;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public int getR1_check() {
        return R1_check;
    }

    public void setR1_check(int r1_check) {
        R1_check = r1_check;
    }

    public int getR2_check() {
        return R2_check;
    }

    public void setR2_check(int r2_check) {
        R2_check = r2_check;
    }

    public int getR3_check() {
        return R3_check;
    }

    public void setR3_check(int r3_check) {
        R3_check = r3_check;
    }

    public int getR4_check() {
        return R4_check;
    }

    public void setR4_check(int r4_check) {
        R4_check = r4_check;
    }
}