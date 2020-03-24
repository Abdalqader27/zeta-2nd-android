package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Important_quiz.Extinsions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

class QuizDbHelper_Extinsions extends SQLiteOpenHelper {
    private static final String Data_Base_NAME = "MyAwsomeQuaizExtin.db";
    private static final int Version = 7;
    private SQLiteDatabase db;
    private Context Mycontext;

    public QuizDbHelper_Extinsions(@Nullable Context context) {
        super(context, Data_Base_NAME, null, Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQl_CREAT_QUATION_TABEL = "CREATE TABLE " + QuaizContract_Extinsions.QuestionTabel.Tabel_Name + " ( " +
                QuaizContract_Extinsions.QuestionTabel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuaizContract_Extinsions.QuestionTabel.COLMUN_QUESTION + " TEXT, " +
                QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION1 + " TEXT, " +
                QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION2 + " TEXT, " +
                QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION3 + " TEXT, " +
                QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION4 + " TEXT, " +
                QuaizContract_Extinsions.QuestionTabel.COLMUN_ANSWER_NR + " INTEGER "
                + ")";

        db.execSQL(SQl_CREAT_QUATION_TABEL);
        fillQuestionTabel();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuaizContract_Extinsions.QuestionTabel.Tabel_Name);
        onCreate(db);

    }

    private void fillQuestionTabel() {

        Qutions_Extinsoins q1 = new Qutions_Extinsoins(".aero : ",
                "Auto industry ",
                "Aviation industry",
                "aviation management",
                "Auto management",
                2);
        addQustion(q1);
        Qutions_Extinsoins q2 = new Qutions_Extinsoins(".biz : ",
                "business card ",
                "business man ",
                "businesses",
                "bizarre",
                3);
        addQustion(q2);
        Qutions_Extinsoins q3 = new Qutions_Extinsoins(".com  : ",
                "Community",
                "Communication",
                "Company ",
                "Commercial",
                4);
        addQustion(q3);
        Qutions_Extinsoins q4 = new Qutions_Extinsoins(".coop  : ",
                "Cooperatives",
                "Cooper",
                "Cooperate ",
                "Coopers",
                1);
        addQustion(q4);
        Qutions_Extinsoins q5 = new Qutions_Extinsoins(".edu  or .ac : ",
                "Educational and entertainment",
                "Educational and research",
                "educational institution",
                "educational attainment",
                2);
        addQustion(q5);
        Qutions_Extinsoins q6 = new Qutions_Extinsoins(".gov : ",
                "Govt",
                "Governments",
                "Government",
                "govt employee",
                3);
        addQustion(q6);
        Qutions_Extinsoins q7 = new Qutions_Extinsoins(".info : ",
                "Inform ",
                "Special use",
                "Information",
                "General use",
                4);
        addQustion(q7);
        Qutions_Extinsoins q8 = new Qutions_Extinsoins(".int  : ",
                "International organization ",
                "international relations",
                "international trade",
                "international business",
                1);
        addQustion(q8);
        Qutions_Extinsoins q9 = new Qutions_Extinsoins(".mil  : ",
                "Military agency ",
                "military service",
                "military status",
                "military base",
                1);
        addQustion(q9);
        Qutions_Extinsoins q10 = new Qutions_Extinsoins(".museums  : ",
                "Museums and art galleries ",
                "Museums",
                "museums and galleries",
                "masculine",
                2);
        addQustion(q10);
        Qutions_Extinsoins q11 = new Qutions_Extinsoins(".name  : ",
                "Names ",
                "Nameplates ",
                "Individuals",
                "nameplate data",
                3);
        addQustion(q11);
        Qutions_Extinsoins q12 = new Qutions_Extinsoins(".net  : ",
                "gateway or internet ",
                "gateway drug ",
                "gateway city",
                "Gateway or host",
                4);
        addQustion(q12);
        Qutions_Extinsoins q13 = new Qutions_Extinsoins(".org  : ",
                "non-profit Organization ",
                "profit Organization  ",
                "Education Organization",
                "World Health Organization",
                1);
        addQustion(q13);
        Qutions_Extinsoins q14 = new Qutions_Extinsoins(".pro  : ",
                "Professional ",
                "Professionals  ",
                "Programs ",
                "Social Networking Programs",
                2);
        addQustion(q14);
        Qutions_Extinsoins q15 = new Qutions_Extinsoins(".firm  : ",
                "Informatics  ",
                "Firm  ",
                "Informative ",
                "Firms ",
                3);
        addQustion(q15);
        Qutions_Extinsoins q16 = new Qutions_Extinsoins(".nom   : ",
                "Recreational  ",
                "Entertainment  ",
                "nomination ",
                "nomination form ",
                1);
        addQustion(q16);
        Qutions_Extinsoins q17 = new Qutions_Extinsoins(".store   : ",
                "Stores   ",
                "Cultural or entertainment  ",
                "Store",
                "cultural or recreational",
                2);
        addQustion(q17);
        Qutions_Extinsoins q18 = new Qutions_Extinsoins(".web   : ",
                "Website    ",
                "Web",
                "Personal  ",
                "Websites",
                3);
        addQustion(q18);
        Qutions_Extinsoins q19 = new Qutions_Extinsoins(".arts   : ",
                "arts and cultures    ",
                "art and design",
                "Artist   ",
                "Film or agency",
                4);
        addQustion(q19);
        Qutions_Extinsoins q20 = new Qutions_Extinsoins(".rec   : ",
                "Online retail shop",
                "receive payments",
                "receive    ",
                "Receive payments",
                1);
        addQustion(q20);
    }


    private void addQustion(Qutions_Extinsoins qutions) {
        ContentValues cv = new ContentValues();
        cv.put(QuaizContract_Extinsions.QuestionTabel.COLMUN_QUESTION, qutions.getQution());
        cv.put(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION1, qutions.getOption1());
        cv.put(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION2, qutions.getOption2());
        cv.put(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION3, qutions.getOption3());
        cv.put(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION4, qutions.getOption4());
        cv.put(QuaizContract_Extinsions.QuestionTabel.COLMUN_ANSWER_NR, qutions.getAnswer());
        db.insert(QuaizContract_Extinsions.QuestionTabel.Tabel_Name, null, cv);

    }

    public ArrayList<Qutions_Extinsoins> getAllQustion() {
        ArrayList<Qutions_Extinsoins> qutionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuaizContract_Extinsions.QuestionTabel.Tabel_Name, null);
        if (c.moveToFirst()) {
            do {
                Qutions_Extinsoins qutions = new Qutions_Extinsoins();
                qutions.setQution(c.getString(c.getColumnIndex(QuaizContract_Extinsions.QuestionTabel.COLMUN_QUESTION)));
                qutions.setOption1(c.getString(c.getColumnIndex(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION1)));
                qutions.setOption2(c.getString(c.getColumnIndex(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION2)));
                qutions.setOption3(c.getString(c.getColumnIndex(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION3)));
                qutions.setOption4(c.getString(c.getColumnIndex(QuaizContract_Extinsions.QuestionTabel.COLMUN_OPTION4)));
                qutions.setAnswer(c.getInt(c.getColumnIndex(QuaizContract_Extinsions.QuestionTabel.COLMUN_ANSWER_NR)));


                qutionsList.add(qutions);
            } while (c.moveToNext());

        }

        c.close();
        return qutionsList;
    }

}
