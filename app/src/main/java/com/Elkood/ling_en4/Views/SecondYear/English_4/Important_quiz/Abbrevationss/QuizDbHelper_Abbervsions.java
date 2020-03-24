package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Abbrevationss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

class QuizDbHelper_Abbervsions extends SQLiteOpenHelper {
    private static final String Data_Base_NAME = "MyAwsomeQuaizAbbervsion.db";
    private static final int Version = 7;
    private SQLiteDatabase db;
    private Context Mycontext;

    public QuizDbHelper_Abbervsions(@Nullable Context context) {
        super(context, Data_Base_NAME, null, Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQl_CREAT_QUATION_TABEL = "CREATE TABLE " + QuaizContract_Abberveations.QuestionTabel.Tabel_Name + " ( " +
                QuaizContract_Abberveations.QuestionTabel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuaizContract_Abberveations.QuestionTabel.COLMUN_QUESTION + " TEXT, " +
                QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION1 + " TEXT, " +
                QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION2 + " TEXT, " +
                QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION3 + " TEXT, " +
                QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION4 + " TEXT, " +
                QuaizContract_Abberveations.QuestionTabel.COLMUN_ANSWER_NR + " INTEGER "
                + ")";

        db.execSQL(SQl_CREAT_QUATION_TABEL);
        fillQuestionTabel();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuaizContract_Abberveations.QuestionTabel.Tabel_Name);
        onCreate(db);

    }

    private void fillQuestionTabel() {

        Qutions_Abberv q1 = new Qutions_Abberv("GPS : ",
                "Globular placement shape",
                "Global Positioning System",
                "Globe position setup",
                "Globe place second",
                2);
        addQustion(q1);
        Qutions_Abberv q2 = new Qutions_Abberv("IMAP : ",
                "Insurance mail achievement protocols",
                "Internet mail access protocol",
                "Idea main arrivals people",
                "Information magazine arrive paper",
                2);
        addQustion(q2);
        Qutions_Abberv q3 = new Qutions_Abberv("LAN : ",
                "Local area network",
                "Logical ambit net",
                "Lose account number ",
                "Lucky administrator new",
                1);
        addQustion(q3);
        Qutions_Abberv q4 = new Qutions_Abberv("POP : ",
                "Piece of Popular",
                "Place or Put ",
                "Point of Presence",
                "Post Office Protocol",
                4);
        addQustion(q4);
        Qutions_Abberv q5 = new Qutions_Abberv("FTP : ",
                "Folder transmit protocols",
                "Fold transmission package",
                "File transfer protocol  ",
                "Factory tea paper",
                3);
        addQustion(q5);
        Qutions_Abberv q6 = new Qutions_Abberv("TA ",
                "Top adapter",
                "Terminal adapter",
                "Transformation adapt ",
                "Transponders alter",
                2);
        addQustion(q6);
        Qutions_Abberv q7 = new Qutions_Abberv("DSL  ",
                "Digital subscriber line",
                "Danger supporter lane ",
                "Daily site library ",
                "Different second lineup",
                1);
        addQustion(q7);
        Qutions_Abberv q8 = new Qutions_Abberv("SMS ",
                "Summarized Missive Screen ",
                "Stumpy Medicine Server ",
                "Short Message Server ",
                "Spotty Map Mection",
                3);
        addQustion(q8);
        Qutions_Abberv q9 = new Qutions_Abberv("Mp3 :",
                "Music Player 3",
                "MHz Audio Layer 3",
                "MIDI Player 3 ",
                "MPEG Audio Layer 3",
                4);
        addQustion(q9);
        Qutions_Abberv q10 = new Qutions_Abberv("IP :",
                "Internet Problem",
                "Internet Protocol",
                "Internet Provider",
                "Internet Penetration",
                2);
        addQustion(q10);
        Qutions_Abberv q11 = new Qutions_Abberv("ASP : ",
                "Application Service Provider.",
                "Average Sales Price ",
                "Advanced Simple Profile ",
                "Alternative School Programs",
                1);
        addQustion(q11);
        Qutions_Abberv q12 = new Qutions_Abberv("NIC : ",
                "Network Internet Capacity",
                "Network Internet Card",
                "Network Internet Capable",
                "Network Internet Capacitor ",
                2);
        addQustion(q12);
        Qutions_Abberv q13 = new Qutions_Abberv("TCP ",
                "Translation Control Problem  ",
                "Transmission Control Provider",
                "Transmission Control Protocol",
                "Translation Control Protocol   ",
                3);
        addQustion(q13);
        Qutions_Abberv q14 = new Qutions_Abberv("UDP  ",
                "User Datagram Problem ",
                "User Database Provider ",
                "User Database Problem",
                "User Datagram Protocol",
                4);
        addQustion(q14);
        Qutions_Abberv q15 = new Qutions_Abberv("DNS ",
                " Domain Name Service",
                "Domain Name System",
                "Dominant Name Service",
                "Dome Name System",
                2);
        addQustion(q15);
        Qutions_Abberv q16 = new Qutions_Abberv("SMTP ",
                " Simple Mail Transfer Protocol ",
                "Small Message Transfer Protocol ",
                "Simple Mail Transfer Provider",
                "Simple  Message Transfer Protocol",
                1);
        addQustion(q16);
        Qutions_Abberv q17 = new Qutions_Abberv("HTML : ",
                " Hyper Text Market Language ",
                "Hyper Text Markup Language  ",
                "Hygiene Text Market Language ",
                "Hygiene Text Markup Language",
                2);
        addQustion(q17);
        Qutions_Abberv q18 = new Qutions_Abberv("SGML :  ",
                "Standard General Markup Language ",
                "Standard Generalized Market Language ",
                "Standard Generalized Markup Language ",
                "Standard General Market Language",
                3);
        addQustion(q18);
        Qutions_Abberv q19 = new Qutions_Abberv("DTP : ",
                "Desk Transfer Process",
                "Design Transfer Point  ",
                "Design Transfer Protocol",
                "Desktop Publisher ",
                4);
        addQustion(q19);
        Qutions_Abberv q20 = new Qutions_Abberv("ISP :",
                " Internet Server Provider ",
                "Internet System Problem ",
                "Internet Server Protocol.",
                "Internet System Penetration ",
                1);
        addQustion(q20);
        Qutions_Abberv q21 = new Qutions_Abberv("IRC",
                " Information Resource Center  ",
                "Internet Relay Chat ",
                "Innovation Relay Center",
                "International Relations Center",
                2);
        addQustion(q21);
        Qutions_Abberv q22 = new Qutions_Abberv("PIM :    ",
                " Personal Information Manager",
                "Personal Information Machine  ",
                "Personality Intelligent Manager ",
                "Product information management ",
                1);
        addQustion(q22);
        Qutions_Abberv q23 = new Qutions_Abberv("DVD :  ",
                " Dependable Video Discus  ",
                "Digital Virus Disease",
                "Dual View Display",
                "Digital Video Disk ",
                4);
        addQustion(q23);

    }


    private void addQustion(Qutions_Abberv qutions) {
        ContentValues cv = new ContentValues();
        cv.put(QuaizContract_Abberveations.QuestionTabel.COLMUN_QUESTION, qutions.getQution());
        cv.put(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION1, qutions.getOption1());
        cv.put(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION2, qutions.getOption2());
        cv.put(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION3, qutions.getOption3());
        cv.put(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION4, qutions.getOption4());
        cv.put(QuaizContract_Abberveations.QuestionTabel.COLMUN_ANSWER_NR, qutions.getAnswer());
        db.insert(QuaizContract_Abberveations.QuestionTabel.Tabel_Name, null, cv);

    }

    public ArrayList<Qutions_Abberv> getAllQustion() {
        ArrayList<Qutions_Abberv> qutionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuaizContract_Abberveations.QuestionTabel.Tabel_Name, null);
        if (c.moveToFirst()) {
            do {
                Qutions_Abberv qutions = new Qutions_Abberv();
                qutions.setQution(c.getString(c.getColumnIndex(QuaizContract_Abberveations.QuestionTabel.COLMUN_QUESTION)));
                qutions.setOption1(c.getString(c.getColumnIndex(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION1)));
                qutions.setOption2(c.getString(c.getColumnIndex(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION2)));
                qutions.setOption3(c.getString(c.getColumnIndex(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION3)));
                qutions.setOption4(c.getString(c.getColumnIndex(QuaizContract_Abberveations.QuestionTabel.COLMUN_OPTION4)));
                qutions.setAnswer(c.getInt(c.getColumnIndex(QuaizContract_Abberveations.QuestionTabel.COLMUN_ANSWER_NR)));


                qutionsList.add(qutions);
            } while (c.moveToNext());

        }

        c.close();
        return qutionsList;
    }

}
