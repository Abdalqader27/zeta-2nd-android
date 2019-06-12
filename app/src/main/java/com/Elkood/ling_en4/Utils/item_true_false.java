package com.Elkood.ling_en4.Utils;


public class item_true_false {
    private String text;
    private String desc;
    private int Image;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public item_true_false(String text, String desc, int image) {
        this.text = text;
        this.desc = desc;
        Image = image;
    }

    public item_true_false(String text, int image) {
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
