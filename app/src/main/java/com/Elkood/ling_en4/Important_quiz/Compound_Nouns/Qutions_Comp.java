package com.Elkood.ling_en4.Important_quiz.Compound_Nouns;

import android.os.Parcel;
import android.os.Parcelable;

public class Qutions_Comp implements Parcelable {

    private String qution;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private int Answer;

    public Qutions_Comp(){}

    public Qutions_Comp(String qution, String option1, String option2, String option3, String option4, int answer) {
        this.qution = qution;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4=option4;
        Answer = answer;
    }

    private Qutions_Comp(Parcel in) {
        qution = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4=in.readString();

        Answer = in.readInt();
        //deffucity=in.readString();
    }

    public static final Creator<Qutions_Comp> CREATOR = new Creator<Qutions_Comp>() {
        @Override
        public Qutions_Comp createFromParcel(Parcel in) {
            return new Qutions_Comp(in);
        }

        @Override
        public Qutions_Comp[] newArray(int size) {
            return new Qutions_Comp[size];
        }
    };

    public void setQution(String qution) {
        this.qution = qution;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }


    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }


    public void setAnswer(int answer) {
        Answer = answer;
    }

    public String getQution() {
        return qution;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }


    public int getAnswer() {
        return Answer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(qution);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeInt(Answer); }


}
