package com.Elkood.ling_en4.Repeatrer_Quiz;

public class item_repeater {
    private String Name;
    private String Type;
    private  String Num;

    item_repeater(String name, String type) {
        Name = name;
        Type = type;
    }

    public item_repeater(String name, String type, String num) {
        Name = name;
        Type = type;
        Num = num;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
