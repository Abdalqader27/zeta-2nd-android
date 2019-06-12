package com.Elkood.ling_en4.Important_quiz.Vocabulary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

class QuizDbHelper_Vocabulary extends SQLiteOpenHelper {
    private static final String Data_Base_NAME = "MyAwsomeQuaizVoca1.db";
    private static final int Version = 5;
    private SQLiteDatabase db;

    public QuizDbHelper_Vocabulary(@Nullable Context context) {
        super(context, Data_Base_NAME, null, Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQl_CREAT_QUATION_TABEL = "CREATE TABLE " + QuaizContract_Vocabulary.QuestionTabel.Tabel_Name + " ( " +
                QuaizContract_Vocabulary.QuestionTabel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuaizContract_Vocabulary.QuestionTabel.COLMUN_QUESTION + " TEXT, " +
                QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION1 + " TEXT, " +
                QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION2 + " TEXT, " +
                QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION3 + " TEXT, " +
                QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION4 + " TEXT, " +
                QuaizContract_Vocabulary.QuestionTabel.COLMUN_ANSWER_NR + " INTEGER "
                + ")";

        db.execSQL(SQl_CREAT_QUATION_TABEL);
        fillQuestionTabel();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuaizContract_Vocabulary.QuestionTabel.Tabel_Name);
        onCreate(db);

    }

    private void fillQuestionTabel() {

        Qutions_Vocabulary q1 = new Qutions_Vocabulary("Website : ",
                "Using reference work like encyclopedias",
                "Collection of related webpages.",
                "Composing Music on PC.",
                "Type of compression used for Bitmap image",
                2);
        addQustion(q1);
        Qutions_Vocabulary q2 = new Qutions_Vocabulary("Virus : ",
                "Self-replicating program.",
                "Formula used for decompressing component of data stream.",
                "the amount of data transferred to the cache at any one time ",
                "Composing Music on PC.",
                1);
        addQustion(q2);
        Qutions_Vocabulary q3 = new Qutions_Vocabulary("Office Suite : ",
                "Downloading Music from the internet",
                "Set of Standard Programs used in an Office.",
                "Collection of related webpages.",
                "Common type of Compression used for video data.",
                2);
        addQustion(q3);
        Qutions_Vocabulary q4 = new Qutions_Vocabulary("Bandwidth : ",
                "High Capacity of internet connection",
                "a combination of text with sound, graphic and video",
                "Composing Music on PC. ",
                "Capacity of a network connection.",
                4);
        addQustion(q4);
        Qutions_Vocabulary q5 = new Qutions_Vocabulary("Broadband : ",
                "Capacity of a network connection.",
                "Composing Music on PC",
                "High Capacity of internet connection  ",
                "a combination of text with sound, graphic and video ",
                3);
        addQustion(q5);
        Qutions_Vocabulary q6 = new Qutions_Vocabulary("Data Center",
                "a system that allows users to interact with a combination of inputs  ",
                "Facility for storing Large Amount of information",
                "Common Enterprise resource Planning tool ",
                "software assistant that performs tasks such as automatic repetitive tasks   ",
                2);
        addQustion(q6);
        Qutions_Vocabulary q7 = new Qutions_Vocabulary("SAP ",
                "Common Enterprise resource Planning tool",
                "Facility for storing Large Amount of information",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                "a system that allows users to interact with a combination of inputs    ",
                1);
        addQustion(q7);
        Qutions_Vocabulary q8 = new Qutions_Vocabulary("MIDI",
                "Common Enterprise resource Planning tool ",
                "Facility for storing Large Amount of information",
                " standard for interconnecting electronic musical instruments and computers.",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                3);
        addQustion(q8);
        Qutions_Vocabulary q9 = new Qutions_Vocabulary("Mp3 :",
                "Common Enterprise resource Planning tool ",
                "Facility for storing Large Amount of information",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                "Downloading Music from the internet. ",
                4);
        addQustion(q9);
        Qutions_Vocabulary q10 = new Qutions_Vocabulary("DVD :",
                "Composing Music on PC ",
                "Watching Movie ",
                "Common Enterprise resource Planning tool",
                "Downloading Music from the internet. ",
                2);
        addQustion(q10);

        Qutions_Vocabulary q12 = new Qutions_Vocabulary("Algorithm : ",
                "Common Enterprise resource Planning tool ",
                "Formula used for decompressing component of data stream. ",
                "Facility for storing Large Amount of information",
                "Using reference work like encyclopedias. ",
                2);
        addQustion(q12);
        Qutions_Vocabulary q13 = new Qutions_Vocabulary("I-Frame ",
                "Abstucting and Indexing  ",
                "Common Enterprise resource Planning tool ",
                "Compressed video frame that contains the complete Image Information",
                "Using reference work like encyclopedias.   ",
                3);
        addQustion(q13);
        Qutions_Vocabulary q14 = new Qutions_Vocabulary("JPEG  ",
                "Using reference work like encyclopedias. ",
                "Composing Music on PC ",
                "Formula used for decompressing component of data stream",
                "Type of compression used for Bitmap image.",
                4);
        addQustion(q14);
        Qutions_Vocabulary q15 = new Qutions_Vocabulary("P-Frame ",
                " Type of compression used for Bitmap image. ",
                "Compressed video frame known as Predicted Frame.  ",
                "Compressed video frame that stores Changes between the frame before it and the frame after it. ",
                "Using reference work like encyclopedias.",
                2);
        addQustion(q15);
        Qutions_Vocabulary q16 = new Qutions_Vocabulary("B-Frame :",
                " Compressed video frame that stores Changes between the frame before it and the frame after it.  ",
                "Compressed video frame known as Predicted Frame.  ",
                "Type of compression used for Bitmap image. ",
                "Using reference work like encyclopedias.",
                1);
        addQustion(q16);
        Qutions_Vocabulary q17 = new Qutions_Vocabulary("MPEG : ",
                " Using reference work like encyclopedias.  ",
                "Common type of Compression used for video data  ",
                "Type of compression used for Bitmap image. ",
                "Downloading Music from the internet. ",
                2);
        addQustion(q17);
        Qutions_Vocabulary q18 = new Qutions_Vocabulary("Bracketing :  ",
                " Using reference work like encyclopedias.  ",
                "Watching Movie   ",
                "Set boundaries for the beginning and end of message  ",
                "Mathematical calculation based on the content of data",
                3);
        addQustion(q18);
        Qutions_Vocabulary q19 = new Qutions_Vocabulary("Checksum : ",
                "Formula used for decompressing component of data stream",
                "Common type of Compression used for video data     ",
                "Set boundaries for the beginning and end of message  ",
                "Mathematical calculation based on the content of data ",
                4);
        addQustion(q19);
        Qutions_Vocabulary q20 = new Qutions_Vocabulary("Half-Duplex :",
                " Transmission mode in which each computer takes turn sending and receiving  ",
                "Transmission mode in which both computers send and receive at the same time  ",
                "Set boundaries for the beginning and end of message     ",
                "Mathematical calculation based on the content of data  ",
                1);
        addQustion(q20);
        Qutions_Vocabulary q21 = new Qutions_Vocabulary("Full-Duplex",
                " Transmission mode in which each computer takes turn sending and receiving  ",
                "Transmission mode in which both computers send and receive at the same time  ",
                "Set boundaries for the beginning and end of message     ",
                "Mathematical calculation based on the content of data  ",
                2);
        addQustion(q21);
        Qutions_Vocabulary q22 = new Qutions_Vocabulary("IRC :    ",
                " Chatting to other users in real-time. ",
                "Formula used for decompressing component of data stream  ",
                "Set boundaries for the beginning and end of message    ",
                "Mathematical calculation based on the content of data   ",
                1);
        addQustion(q22);
        Qutions_Vocabulary q23 = new Qutions_Vocabulary("Moos :  ",
                " Chatting to other users in real-time.    ",
                "Formula used for decompressing component of data stream",
                "Mathematical calculation based on the content of data  ",
                "Taking part in simulation in shared environment  ",
                4);
        addQustion(q23);
        Qutions_Vocabulary q24 = new Qutions_Vocabulary("E-Mail : ",
                "Chatting to other users in real-time.   ",
                "Mathematical calculation based on the content of data ",
                "Sending and receiving message   ",
                "Taking part in simulation in shared environment   ",
                3);
        addQustion(q24);
        Qutions_Vocabulary q25 = new Qutions_Vocabulary("FTP : ",
                "Downloading file from server.",
                "Sending and receiving message ",
                "Taking part in simulation in shared environment     ",
                "Chatting to other users in real-time. ",
                1);
        addQustion(q25);
        Qutions_Vocabulary q26 = new Qutions_Vocabulary("WWW :  ",
                " Downloading file from server.  ",
                "Browsing web page  ",
                "branch computer      ",
                "Taking part in simulation in shared environment ",
                2);
        addQustion(q26);
        Qutions_Vocabulary q27 = new Qutions_Vocabulary("Telnet :  ",
                "Logging on to your computer at a distance  ",
                "Accessing web pages ",
                "Browsing web page  ",
                "Chatting to other users in real-time.",
                1);
        addQustion(q27);
        Qutions_Vocabulary q28 = new Qutions_Vocabulary("Usenet : ",
                " Browsing web page ",
                "Accessing web pages ",
                "branch computer     ",
                "Chatting to other users in real-time.",
                2);
        addQustion(q28);
        Qutions_Vocabulary q29 = new Qutions_Vocabulary("Router : ",
                " Browsing web page ",
                "Accessing web pages ",
                "Special computer that’s directs communications.  ",
                "Main transmission path handling major data traffic",
                3);
        addQustion(q29);
        Qutions_Vocabulary q30 = new Qutions_Vocabulary("backbone : ",
                " Special computer that’s directs communications. ",
                "Taking part in simulation in shared environment  ",
                "branch computer   ",
                "Main transmission path handling major data traffic ",
                4);
        addQustion(q30);
        Qutions_Vocabulary q31 = new Qutions_Vocabulary("Internet Address ",
                " Main transmission path handling major data traffic ",
                "Accessing web pages ",
                "Taking part in simulation in shared environment ",
                "A 32-bit number identifying anode on an IP network ",
                4);
        addQustion(q31);
        Qutions_Vocabulary q32 = new Qutions_Vocabulary("Resolution Protocol",
                " Standard used for software that routes data through get way ",
                "A 32-bit number identifying anode on an IP network",
                "Main transmission path handling major data traffic  ",
                "Taking part in simulation in shared environment ",
                1);
        addQustion(q32);
        Qutions_Vocabulary q33 = new Qutions_Vocabulary("Look-up Table  ",
                " Taking part in simulation in shared environment",
                "Stored information used to route data through get way.",
                "Main transmission path handling major data traffic  ",
                "Standard used for software that routes data through get way ",
                2);
        addQustion(q33);
        Qutions_Vocabulary q34 = new Qutions_Vocabulary("Get Way :  ",
                " Device for connecting dissimilar networks. ",
                "Standard used for software that routes data through get way",
                "Main transmission path handling major data traffic  ",
                "Taking part in simulation in shared environment ",
                1);
        addQustion(q34);
        Qutions_Vocabulary q35 = new Qutions_Vocabulary("User Datagram Protocol(UDB) ",
                " Device for connecting dissimilar networks. ",
                "Taking part in simulation in shared environment",
                "Standard used by software that moves information to the correct application on the receiving system of a network.   ",
                "Main transmission path handling major data traffic ",
                3);
        addQustion(q35);
        Qutions_Vocabulary q36 = new Qutions_Vocabulary("Transmission Control Protocol(TCP) ",
                " Device for connecting dissimilar networks. ",
                "Taking part in simulation in shared environment",
                "Standard used by software that moves information to the correct application on the receiving system of a network.   ",
                "Standard used by software that manage communication exchanges between computers on the internet  ",
                4);
        addQustion(q36);
        Qutions_Vocabulary q37 = new Qutions_Vocabulary("ISMTP  ",
                "Device for connecting dissimilar networks.",
                "Simple Mail Transfer Protocol that is used to send message between server",
                " Device for connecting dissimilar networks. ",
                "Taking part in simulation in shared environment",
                2);
        addQustion(q37);
        Qutions_Vocabulary q38 = new Qutions_Vocabulary("Push' Operation ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                3);
        addQustion(q38);
        Qutions_Vocabulary q39 = new Qutions_Vocabulary("Pull' Operation  ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                4);
        addQustion(q39);
        Qutions_Vocabulary q40 = new Qutions_Vocabulary("POP   ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "A Message-Retrieval protocol that download all E-Mail messages at the same time ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                3);
        addQustion(q40);
        Qutions_Vocabulary q41 = new Qutions_Vocabulary("IMAP   ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "Mail transfer protocol that initially only retrieves the message handers ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                3);
        addQustion(q41);
        Qutions_Vocabulary q42 = new Qutions_Vocabulary("Metadata   ",
                " Data about Data  ",
                "Device for connecting dissimilar networks.  ",
                "A Message-Retrieval protocol that download all E-Mail messages at the same time ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                1);
        addQustion(q42);
        Qutions_Vocabulary q43 = new Qutions_Vocabulary("GMetalanguage   ",
                " Data about Data  ",
                "Language from which you can create other Language. ",
                "A Message-Retrieval protocol that download all E-Mail messages at the same time ",
                "Device for connecting dissimilar networks.  ",
                2);
        addQustion(q43);
        Qutions_Vocabulary q44 = new Qutions_Vocabulary("HTML  ",
                " Data about Data  ",
                "Language from which you can create other Language. ",
                "example of page presentation Language.",
                "Device for connecting dissimilar networks.  ",
                3);
        addQustion(q44);
        Qutions_Vocabulary q45 = new Qutions_Vocabulary("XML ",
                " Data about Data  ",
                "Language from which you can create other Language. ",
                "example of page presentation Language.",
                "extensible markup Language ",
                4);
        addQustion(q45);
        Qutions_Vocabulary q46 = new Qutions_Vocabulary("Markup Language ",
                " coding system used for structuring and formatting documents. ",
                "Language from which you can create other Language. ",
                "example of page presentation Language.",
                "extensible markup Language ",
                1);
        addQustion(q46);


    }


    private void addQustion(Qutions_Vocabulary qutions) {
        ContentValues cv = new ContentValues();
        cv.put(QuaizContract_Vocabulary.QuestionTabel.COLMUN_QUESTION, qutions.getQution());
        cv.put(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION1, qutions.getOption1());
        cv.put(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION2, qutions.getOption2());
        cv.put(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION3, qutions.getOption3());
        cv.put(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION4, qutions.getOption4());
        cv.put(QuaizContract_Vocabulary.QuestionTabel.COLMUN_ANSWER_NR, qutions.getAnswer());
        db.insert(QuaizContract_Vocabulary.QuestionTabel.Tabel_Name, null, cv);

    }

    public ArrayList<Qutions_Vocabulary> getAllQustion() {
        ArrayList<Qutions_Vocabulary> qutionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuaizContract_Vocabulary.QuestionTabel.Tabel_Name, null);
        if (c.moveToFirst()) {
            do {
                Qutions_Vocabulary qutions = new Qutions_Vocabulary();
                qutions.setQution(c.getString(c.getColumnIndex(QuaizContract_Vocabulary.QuestionTabel.COLMUN_QUESTION)));
                qutions.setOption1(c.getString(c.getColumnIndex(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION1)));
                qutions.setOption2(c.getString(c.getColumnIndex(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION2)));
                qutions.setOption3(c.getString(c.getColumnIndex(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION3)));
                qutions.setOption4(c.getString(c.getColumnIndex(QuaizContract_Vocabulary.QuestionTabel.COLMUN_OPTION4)));
                qutions.setAnswer(c.getInt(c.getColumnIndex(QuaizContract_Vocabulary.QuestionTabel.COLMUN_ANSWER_NR)));


                qutionsList.add(qutions);
            } while (c.moveToNext());

        }

        c.close();
        return qutionsList;
    }

}
