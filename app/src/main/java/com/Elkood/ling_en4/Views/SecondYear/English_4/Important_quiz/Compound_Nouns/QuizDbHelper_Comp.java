package com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz.Compound_Nouns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

class QuizDbHelper_Comp extends SQLiteOpenHelper {
    private static final String Data_Base_NAME = "MyAwsomeQuaizComp.db";
    private static final int Version = 9;
    private SQLiteDatabase db;
    private Context Mycontext;

    public QuizDbHelper_Comp(@Nullable Context context) {
        super(context, Data_Base_NAME, null, Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQl_CREAT_QUATION_TABEL = "CREATE TABLE " + QuaizContract_comp.QuestionTabel.Tabel_Name + " ( " +
                QuaizContract_comp.QuestionTabel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuaizContract_comp.QuestionTabel.COLMUN_QUESTION + " TEXT, " +
                QuaizContract_comp.QuestionTabel.COLMUN_OPTION1 + " TEXT, " +
                QuaizContract_comp.QuestionTabel.COLMUN_OPTION2 + " TEXT, " +
                QuaizContract_comp.QuestionTabel.COLMUN_OPTION3 + " TEXT, " +
                QuaizContract_comp.QuestionTabel.COLMUN_OPTION4 + " TEXT, " +
                QuaizContract_comp.QuestionTabel.COLMUN_ANSWER_NR + " INTEGER "
                + ")";

        db.execSQL(SQl_CREAT_QUATION_TABEL);
        fillQuestionTabel();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuaizContract_comp.QuestionTabel.Tabel_Name);
        onCreate(db);

    }

    private void fillQuestionTabel() {

        Qutions_Comp q1 = new Qutions_Comp("Barcode : ",
                "Engine",
                "Reader",
                "Computer",
                "Printer",
                2);
        addQustion(q1);
        Qutions_Comp q2 = new Qutions_Comp("Mainframe : ",
                "Engine",
                "Reader",
                "Computer",
                "Printer",
                3);
        addQustion(q2);
        Qutions_Comp q3 = new Qutions_Comp("Laser : ",
                "Engine",
                "Reader",
                "Computer",
                "Printer",
                4);
        addQustion(q3);
        Qutions_Comp q4 = new Qutions_Comp("Expansion : ",
                "Card",
                "Reader",
                "Computer",
                "Printer",
                1);
        addQustion(q4);
        Qutions_Comp q5 = new Qutions_Comp("Search : ",
                "Card",
                "Engine",
                "Computer",
                "Printer",
                2);
        addQustion(q5);
        Qutions_Comp q6 = new Qutions_Comp("Control : ",
                "Card",
                "Engine",
                "Bus",
                "Printer",
                3);
        addQustion(q6);
        Qutions_Comp q7 = new Qutions_Comp("Supervisor : ",
                "Card",
                "Engine",
                "Bus",
                "Program",
                4);
        addQustion(q7);
        Qutions_Comp q8 = new Qutions_Comp("Task : ",
                "Bar",
                "Engine",
                "Bus",
                "Program",
                1);
        addQustion(q8);
        Qutions_Comp q9 = new Qutions_Comp("System : ",
                "Bar",
                "Tray",
                "Bus",
                "Program",
                2);
        addQustion(q9);
        Qutions_Comp q10 = new Qutions_Comp("Explorer : ",
                "Bar",
                "Tray",
                "Bus",
                "Program",
                4);
        addQustion(q10);
        Qutions_Comp q11 = new Qutions_Comp("Bulletin : ",
                "Bar",
                "Tray",
                "Board",
                "Program",
                3);
        addQustion(q11);
        Qutions_Comp q12 = new Qutions_Comp("Domain : ",
                "Bar",
                "Tray",
                "Board",
                "Name",
                4);
        addQustion(q12);
        Qutions_Comp q13 = new Qutions_Comp("File : ",
                "Name",
                "Tray",
                "Board",
                "Link",
                1);
        addQustion(q13);
        Qutions_Comp q14 = new Qutions_Comp("Graphical : ",
                "Name",
                "Button",
                "Board",
                "Link",
                2);
        addQustion(q14);
        Qutions_Comp q15 = new Qutions_Comp("Mobile : ",
                "Name",
                "Button",
                "Phone",
                "Link",
                3);
        addQustion(q15);
        Qutions_Comp q16 = new Qutions_Comp("Search : ",
                "Name",
                "Button",
                "Engine",
                "Link",
                3);
        addQustion(q16);
        Qutions_Comp q17 = new Qutions_Comp("Site : ",
                "Name",
                "Button",
                "Engine",
                "Map",
                4);
        addQustion(q17);
        Qutions_Comp q18 = new Qutions_Comp("Synchronous : ",
                "Transmission",
                "Button",
                "Engine",
                "Map",
                1);
        addQustion(q18);
        Qutions_Comp q19 = new Qutions_Comp("Text : ",
                "Transmission",
                "Message",
                "Engine",
                "Map",
                2);
        addQustion(q19);
        Qutions_Comp q20 = new Qutions_Comp("Web : ",
                "Transmission",
                "Message",
                "Engine",
                "Page",
                4);
        addQustion(q20);
    }


    private void addQustion(Qutions_Comp qutions) {
        ContentValues cv = new ContentValues();
        cv.put(QuaizContract_comp.QuestionTabel.COLMUN_QUESTION, qutions.getQution());
        cv.put(QuaizContract_comp.QuestionTabel.COLMUN_OPTION1, qutions.getOption1());
        cv.put(QuaizContract_comp.QuestionTabel.COLMUN_OPTION2, qutions.getOption2());
        cv.put(QuaizContract_comp.QuestionTabel.COLMUN_OPTION3, qutions.getOption3());
        cv.put(QuaizContract_comp.QuestionTabel.COLMUN_OPTION4, qutions.getOption4());
        cv.put(QuaizContract_comp.QuestionTabel.COLMUN_ANSWER_NR, qutions.getAnswer());
        db.insert(QuaizContract_comp.QuestionTabel.Tabel_Name, null, cv);

    }

    public ArrayList<Qutions_Comp> getAllQustion() {
        ArrayList<Qutions_Comp> qutionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuaizContract_comp.QuestionTabel.Tabel_Name, null);
        if (c.moveToFirst()) {
            do {
                Qutions_Comp qutions = new Qutions_Comp();
                qutions.setQution(c.getString(c.getColumnIndex(QuaizContract_comp.QuestionTabel.COLMUN_QUESTION)));
                qutions.setOption1(c.getString(c.getColumnIndex(QuaizContract_comp.QuestionTabel.COLMUN_OPTION1)));
                qutions.setOption2(c.getString(c.getColumnIndex(QuaizContract_comp.QuestionTabel.COLMUN_OPTION2)));
                qutions.setOption3(c.getString(c.getColumnIndex(QuaizContract_comp.QuestionTabel.COLMUN_OPTION3)));
                qutions.setOption4(c.getString(c.getColumnIndex(QuaizContract_comp.QuestionTabel.COLMUN_OPTION4)));
                qutions.setAnswer(c.getInt(c.getColumnIndex(QuaizContract_comp.QuestionTabel.COLMUN_ANSWER_NR)));


                qutionsList.add(qutions);
            } while (c.moveToNext());

        }

        c.close();
        return qutionsList;
    }

}
