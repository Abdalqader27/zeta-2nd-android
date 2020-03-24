package com.Elkood.ling_en4.Models;

public class CardHome {
    private String Title;
    private  String desc;
    private  String date;
    private int image;

    public CardHome(String title, String desc, String date, int image) {
        Title = title;
        this.desc = desc;
        this.date = date;
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
