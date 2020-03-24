package com.Elkood.ling_en4.Models;


public class TrueFalse {
    private String text;
    private String desc;
    private int Image;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public TrueFalse(String text, String desc, int image) {
        this.text = text;
        this.desc = desc;
        Image = image;
    }

    public TrueFalse(String text, int image) {
        this.text = text;
        Image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
