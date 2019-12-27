package com.Elkood.ling_en4.EN4.Full_Quizes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

class QuizDbHelper_Full_Quiz extends SQLiteOpenHelper {
    private static final String Data_Base_NAME = "MyAwsomeQuaizFul10.db";
    private static final int Version = 8;
    private SQLiteDatabase db;

    public QuizDbHelper_Full_Quiz(@Nullable Context context) {
        super(context, Data_Base_NAME, null, Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQl_CREAT_QUATION_TABEL = "CREATE TABLE " + QuaizContract_Full.QuestionTabel.Tabel_Name + " ( " +
                QuaizContract_Full.QuestionTabel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuaizContract_Full.QuestionTabel.COLMUN_QUESTION + " TEXT, " +
                QuaizContract_Full.QuestionTabel.COLMUN_OPTION1 + " TEXT, " +
                QuaizContract_Full.QuestionTabel.COLMUN_OPTION2 + " TEXT, " +
                QuaizContract_Full.QuestionTabel.COLMUN_OPTION3 + " TEXT, " +
                QuaizContract_Full.QuestionTabel.COLMUN_OPTION4 + " TEXT, " +
                QuaizContract_Full.QuestionTabel.COLMUN_ANSWER_NR + " INTEGER "
                + ")";

        db.execSQL(SQl_CREAT_QUATION_TABEL);
        fillQuestionTabel();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuaizContract_Full.QuestionTabel.Tabel_Name);
        onCreate(db);

    }

    private void fillQuestionTabel() {
// Vocabulary
        Qutions_Full q1 = new Qutions_Full("Website : ",
                "Using reference work like encyclopedias",
                "Collection of related webpages.",
                "Composing Music on PC.",
                "Type of compression used for Bitmap image",
                2);
        addQustion(q1);
        Qutions_Full q2 = new Qutions_Full("Virus : ",
                "Self-replicating program.",
                "Formula used for decompressing component of data stream.",
                "the amount of data transferred to the cache at any one time ",
                "Composing Music on PC.",
                1);
        addQustion(q2);
        Qutions_Full q3 = new Qutions_Full("Office Suite : ",
                "Downloading Music from the internet",
                "Set of Standard Programs used in an Office.",
                "Collection of related webpages.",
                "Common type of Compression used for video data.",
                2);
        addQustion(q3);
        Qutions_Full q4 = new Qutions_Full("Bandwidth : ",
                "High Capacity of internet connection",
                "a combination of text with sound, graphic and video",
                "Composing Music on PC. ",
                "Capacity of a network connection.",
                4);
        addQustion(q4);
        Qutions_Full q5 = new Qutions_Full("Broadband : ",
                "Capacity of a network connection.",
                "Composing Music on PC",
                "High Capacity of internet connection  ",
                "a combination of text with sound, graphic and video ",
                3);
        addQustion(q5);
        Qutions_Full q6 = new Qutions_Full("Data Center",
                "a system that allows users to interact with a combination of inputs  ",
                "Facility for storing Large Amount of information",
                "Common Enterprise resource Planning tool ",
                "software assistant that performs tasks such as automatic repetitive tasks   ",
                2);
        addQustion(q6);
        Qutions_Full q7 = new Qutions_Full("SAP ",
                "Common Enterprise resource Planning tool",
                "Facility for storing Large Amount of information",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                "a system that allows users to interact with a combination of inputs    ",
                1);
        addQustion(q7);
        Qutions_Full q8 = new Qutions_Full("MIDI",
                "Common Enterprise resource Planning tool ",
                "Facility for storing Large Amount of information",
                "standard for interconnecting electronic musical instruments and computers.",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                3);
        addQustion(q8);
        Qutions_Full q9 = new Qutions_Full("Mp3 :",
                "Common Enterprise resource Planning tool ",
                "Facility for storing Large Amount of information",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                "Downloading Music from the internet. ",
                4);
        addQustion(q9);
        Qutions_Full q10 = new Qutions_Full("DVD :",
                "Composing Music on PC ",
                "Watching Movie ",
                "Common Enterprise resource Planning tool",
                "Downloading Music from the internet. ",
                2);
        addQustion(q10);

        Qutions_Full q12 = new Qutions_Full("Algorithm : ",
                "Common Enterprise resource Planning tool ",
                "Formula used for decompressing component of data stream. ",
                "Facility for storing Large Amount of information",
                "Using reference work like encyclopedias. ",
                2);
        addQustion(q12);
        Qutions_Full q13 = new Qutions_Full("I-Frame ",
                "Abstucting and Indexing  ",
                "Common Enterprise resource Planning tool ",
                "Compressed video frame that contains the complete Image Information",
                "Using reference work like encyclopedias.   ",
                3);
        addQustion(q13);
        Qutions_Full q14 = new Qutions_Full("JPEG  ",
                "Using reference work like encyclopedias. ",
                "Composing Music on PC ",
                "Formula used for decompressing component of data stream",
                "Type of compression used for Bitmap image.",
                4);
        addQustion(q14);
        Qutions_Full q15 = new Qutions_Full("P-Frame ",
                " Type of compression used for Bitmap image. ",
                "Compressed video frame known as Predicted Frame.  ",
                "Compressed video frame that stores Changes between the frame before it and the frame after it. ",
                "Using reference work like encyclopedias.",
                2);
        addQustion(q15);
        Qutions_Full q16 = new Qutions_Full("B-Frame :",
                " Compressed video frame that stores Changes between the frame before it and the frame after it.  ",
                "Compressed video frame known as Predicted Frame.  ",
                "Type of compression used for Bitmap image. ",
                "Using reference work like encyclopedias.",
                1);
        addQustion(q16);
        Qutions_Full q17 = new Qutions_Full("MPEG : ",
                " Using reference work like encyclopedias.  ",
                "Common type of Compression used for video data  ",
                "Type of compression used for Bitmap image. ",
                "Downloading Music from the internet. ",
                2);
        addQustion(q17);
        Qutions_Full q18 = new Qutions_Full("Bracketing :  ",
                " Using reference work like encyclopedias.  ",
                "Watching Movie   ",
                "Set boundaries for the beginning and end of message  ",
                "Mathematical calculation based on the content of data",
                3);
        addQustion(q18);
        Qutions_Full q19 = new Qutions_Full("Checksum : ",
                "Formula used for decompressing component of data stream",
                "Common type of Compression used for video data     ",
                "Set boundaries for the beginning and end of message  ",
                "Mathematical calculation based on the content of data ",
                4);
        addQustion(q19);
        Qutions_Full q20 = new Qutions_Full("Half-Duplex :",
                " Transmission mode in which each computer takes turn sending and receiving  ",
                "Transmission mode in which both computers send and receive at the same time  ",
                "Set boundaries for the beginning and end of message     ",
                "Mathematical calculation based on the content of data  ",
                1);
        addQustion(q20);
        Qutions_Full q21 = new Qutions_Full("Full-Duplex",
                " Transmission mode in which each computer takes turn sending and receiving  ",
                "Transmission mode in which both computers send and receive at the same time  ",
                "Set boundaries for the beginning and end of message     ",
                "Mathematical calculation based on the content of data  ",
                2);
        addQustion(q21);
        Qutions_Full q22 = new Qutions_Full("IRC :    ",
                " Chatting to other users in real-time. ",
                "Formula used for decompressing component of data stream  ",
                "Set boundaries for the beginning and end of message    ",
                "Mathematical calculation based on the content of data   ",
                1);
        addQustion(q22);
        Qutions_Full q23 = new Qutions_Full("Moos :  ",
                " Chatting to other users in real-time.    ",
                "Formula used for decompressing component of data stream",
                "Mathematical calculation based on the content of data  ",
                "Taking part in simulation in shared environment  ",
                4);
        addQustion(q23);
        Qutions_Full q24 = new Qutions_Full("E-Mail : ",
                "Chatting to other users in real-time.   ",
                "Mathematical calculation based on the content of data ",
                "Sending and receiving message   ",
                "Taking part in simulation in shared environment   ",
                3);
        addQustion(q24);
        Qutions_Full q25 = new Qutions_Full("FTP : ",
                "Downloading file from server.",
                "Sending and receiving message ",
                "Taking part in simulation in shared environment     ",
                "Chatting to other users in real-time. ",
                1);
        addQustion(q25);
        Qutions_Full q26 = new Qutions_Full("WWW :  ",
                " Downloading file from server.  ",
                "Browsing web page  ",
                "branch computer      ",
                "Taking part in simulation in shared environment ",
                2);
        addQustion(q26);
        Qutions_Full q27 = new Qutions_Full("Telnet :  ",
                "Logging on to your computer at a distance  ",
                "Accessing web pages ",
                "Browsing web page  ",
                "Chatting to other users in real-time.",
                1);
        addQustion(q27);
        Qutions_Full q28 = new Qutions_Full("Usenet : ",
                " Browsing web page ",
                "Accessing web pages ",
                "branch computer     ",
                "Chatting to other users in real-time.",
                2);
        addQustion(q28);
        Qutions_Full q29 = new Qutions_Full("Router : ",
                " Browsing web page ",
                "Accessing web pages ",
                "Special computer that’s directs communications.  ",
                "Main transmission path handling major data traffic",
                3);
        addQustion(q29);
        Qutions_Full q30 = new Qutions_Full("backbone : ",
                " Special computer that’s directs communications. ",
                "Taking part in simulation in shared environment  ",
                "branch computer   ",
                "Main transmission path handling major data traffic ",
                4);
        addQustion(q30);
        Qutions_Full q31 = new Qutions_Full("Internet Address ",
                " Main transmission path handling major data traffic ",
                "Accessing web pages ",
                "Taking part in simulation in shared environment ",
                "A 32-bit number identifying anode on an IP network ",
                4);
        addQustion(q31);
        Qutions_Full q32 = new Qutions_Full("Resolution Protocol",
                " Standard used for software that routes data through get way ",
                "A 32-bit number identifying anode on an IP network",
                "Main transmission path handling major data traffic  ",
                "Taking part in simulation in shared environment ",
                1);
        addQustion(q32);
        Qutions_Full q33 = new Qutions_Full("Look-up Table  ",
                " Taking part in simulation in shared environment",
                "Stored information used to route data through get way.",
                "Main transmission path handling major data traffic  ",
                "Standard used for software that routes data through get way ",
                2);
        addQustion(q33);
        Qutions_Full q34 = new Qutions_Full("Get Way :  ",
                " Device for connecting dissimilar networks. ",
                "Standard used for software that routes data through get way",
                "Main transmission path handling major data traffic  ",
                "Taking part in simulation in shared environment ",
                1);
        addQustion(q34);
        Qutions_Full q35 = new Qutions_Full("User Datagram Protocol(UDB) ",
                " Device for connecting dissimilar networks. ",
                "Taking part in simulation in shared environment",
                "Standard used by software that moves information to the correct application on the receiving system of a network.   ",
                "Main transmission path handling major data traffic ",
                3);
        addQustion(q35);
        Qutions_Full q36 = new Qutions_Full("Transmission Control Protocol(TCP) ",
                " Device for connecting dissimilar networks. ",
                "Taking part in simulation in shared environment",
                "Standard used by software that moves information to the correct application on the receiving system of a network.   ",
                "Standard used by software that manage communication exchanges between computers on the internet  ",
                4);
        addQustion(q36);
        Qutions_Full q37 = new Qutions_Full("SMTP  ",
                "Device for connecting dissimilar networks.",
                "Simple Mail Transfer Protocol that is used to send message between server",
                "Device for connecting dissimilar networks. ",
                "Taking part in simulation in shared environment",
                2);
        addQustion(q37);
        Qutions_Full q38 = new Qutions_Full("Push' Operation ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                3);
        addQustion(q38);
        Qutions_Full q39 = new Qutions_Full("Pull' Operation  ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                4);
        addQustion(q39);
        Qutions_Full q40 = new Qutions_Full("POP   ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "A Message-Retrieval protocol that download all E-Mail messages at the same time ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                3);
        addQustion(q40);
        Qutions_Full q41 = new Qutions_Full("IMAP   ",
                " Simple Mail Transfer Protocol that is used to send message between server   ",
                "Device for connecting dissimilar networks.  ",
                "Mail transfer protocol that initially only retrieves the message handers ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                3);
        addQustion(q41);
        Qutions_Full q42 = new Qutions_Full("Metadata   ",
                "Data about Data  ",
                "Device for connecting dissimilar networks.  ",
                "A Message-Retrieval protocol that download all E-Mail messages at the same time ",
                "An E-Mail Transfer Process in which the receiving computer initiates the connection   ",
                1);
        addQustion(q42);
        Qutions_Full q43 = new Qutions_Full("GMetalanguage   ",
                " Data about Data  ",
                "Language from which you can create other Language. ",
                "A Message-Retrieval protocol that download all E-Mail messages at the same time ",
                "Device for connecting dissimilar networks.  ",
                2);
        addQustion(q43);
        Qutions_Full q44 = new Qutions_Full("HTML  ",
                " Data about Data  ",
                "Language from which you can create other Language. ",
                "example of page presentation Language.",
                "Device for connecting dissimilar networks.  ",
                3);
        addQustion(q44);
        Qutions_Full q45 = new Qutions_Full("XML ",
                " Data about Data  ",
                "Language from which you can create other Language. ",
                "example of page presentation Language.",
                "extensible markup Language ",
                4);
        addQustion(q45);
        Qutions_Full q46 = new Qutions_Full("Markup Language ",
                " coding system used for structuring and formatting documents. ",
                "Language from which you can create other Language. ",
                "example of page presentation Language.",
                "extensible markup Language ",
                1);
        addQustion(q46);
        Qutions_Full q47 = new Qutions_Full("the extension firm  stands for  ",
                "informative ",
                "non-profit agency",
                "personal   ",
                "company",
                1);
        addQustion(q47);
        Qutions_Full q48 = new Qutions_Full(".……...is a network computer used for accessing a service on a server  ",
                "Thin Hub",
                "Client",
                "Server",
                "Router ",
                2);
        addQustion(q48);
        Qutions_Full q49 = new Qutions_Full("The Number of Channels used by an ISDN system is ...................  ",
                "two",
                "three",
                "four",
                "five",
                2);
        addQustion(q49);
        Qutions_Full q50 = new Qutions_Full(" based services are very low-cost option when compared to other solution offering similar bandwidth.  ",
                "ADSI",
                "ISDN",
                "DSI",
                "3G",
                3);
        addQustion(q50);
        Qutions_Full q51 = new Qutions_Full(" …………IS A 32-hit number identifying a node on an IP network",
                "Internet address ",
                "Gateway address  ",
                "Transmission protocol   ",
                "mail address ",
                1);
        addQustion(q51);
        Qutions_Full q52 = new Qutions_Full(" ………………….is a network contained within a small area ",
                "LNB ",
                "WAN",
                "WAP ",
                "LAN",
                4);
        addQustion(q52);
        Qutions_Full q53 = new Qutions_Full("  ……………allows users to send emails and access information on the internet on a mobile ",
                "WAN ",
                "LAP",
                "WAP ",
                "LAN ",
                3);
        addQustion(q53);
        Qutions_Full q54 = new Qutions_Full(" ….. is an electronic device connecting all data cabling in a network",
                "backbone    ",
                "hub ",
                "bridge   ",
                "cable",
                2);
        addQustion(q54);
        Qutions_Full q55 = new Qutions_Full("  …………is a data about data",
                "Metadata   ",
                "beta data ",
                "Backup data  ",
                "Meta language ",
                1);
        addQustion(q55);
        Qutions_Full q56 = new Qutions_Full("   ………...is a way of dealing with bandwidth problems when downloading Video from the internet",
                "Buffering ",
                "connecting",
                "steaming   ",
                "streaming",
                4);
        addQustion(q56);
        Qutions_Full q57 = new Qutions_Full(" ……... opens communication and keeps it straight among all nodes on the network ",
                "Transport layer    ",
                "data link layer",
                "the session layer",
                "Physical layer",
                3);
        addQustion(q57);
        Qutions_Full q58 = new Qutions_Full(" ………. shows only drives and folders ",
                "Navigation paths ",
                "toggle bus",
                "icon",
                "windows explorer",
                4);
        addQustion(q58);
        Qutions_Full q59 = new Qutions_Full(" ………….is a common type of compressions used for video data",
                "BMP ",
                "MID",
                "MPEG ",
                "MP3",
                3);
        addQustion(q59);
        Qutions_Full q60 = new Qutions_Full(" ………………is a formation used for decompressing components of data stream ",
                "algorithm ",
                "Frame ",
                "encryption",
                "decompression",
                1);
        addQustion(q60);
        Qutions_Full q61 = new Qutions_Full(" MP3 can provide information about itself in a code  black called .........  ",
                "tag  ",
                "label",
                "frame",
                "layer",
                1);
        addQustion(q61);

        Qutions_Full q62 = new Qutions_Full(" a facility for storing a large amount of information is   ",
                "a data center ",
                "a call center",
                "data",
                "metadata",
                1);
        addQustion(q62);
        Qutions_Full q63 = new Qutions_Full("  a self-replicating program is …….. ",
                "an application",
                "an OS",
                "a virus",
                "a website",
                3);
        addQustion(q63);
        Qutions_Full q64 = new Qutions_Full("a program which dras songs from a CD to  a WAV file is a ………….  ",
                "recorder ",
                "player ",
                "decoder",
                "Sipper ",
                2);
        addQustion(q64);
        Qutions_Full q65 = new Qutions_Full(" a………. adjust the space allocated for each pane   ",
                "divider ",
                "splitter",
                "scroller",
                "guideline",
                1);
        addQustion(q65);
        Qutions_Full q66 = new Qutions_Full("a device connected on the computer to receive ISDN signal called ",
                "ADSI",
                "DSI",
                "ATM ",
                "TA",
                4);
        addQustion(q66);
        Qutions_Full q67 = new Qutions_Full(" The qualifications needed for being an operating system expert are called",
                "MCSA",
                "MCSI",
                "MCT",
                "MCSD",
                1);
        addQustion(q67);
        Qutions_Full q68 = new Qutions_Full("No one of the following companies developed Bluetooth exept",
                "Sony Ericson  ",
                "IBM  ",
                "Motorola",
                "L-G",
                2);
        addQustion(q68);
        Qutions_Full q69 = new Qutions_Full(" A rectangle with equal sides is called……",
                "square  ",
                "menu   ",
                "library ",
                "circle",
                1);
        addQustion(q69);
        Qutions_Full q70 = new Qutions_Full(" . ……...means convert to meaningful data",
                "Tenet  ",
                "tamper  ",
                "Imposter ",
                "Decipher",
                4);
        addQustion(q70);
        Qutions_Full q71 = new Qutions_Full(" It is not a good idea to keep looking at strangers. You….stare at people ",
                "shall",
                "must",
                "shouldn't",
                "should",
                3);
        addQustion(q71);
        Qutions_Full q72 = new Qutions_Full(" If you spilled coffee on the keyboard you…...it ",
                "will damage",
                "would damage",
                "would have damage ",
                "will have damage",
                2);
        addQustion(q72);
        Qutions_Full q73 = new Qutions_Full(" When you ………… to a network, you have provided an ID",
                "log on",
                "log in",
                "log for ",
                "log out",
                2);
        addQustion(q73);
        Qutions_Full q74 = new Qutions_Full(" If you are committed to …… frames on your site. you would better commit yourself to some extra work",
                "use ",
                "be using",
                "to be used",
                "using ",
                4);
        addQustion(q74);
        Qutions_Full q75 = new Qutions_Full(" A……the green family enter green in the surname box",
                "Find",
                "Be found ",
                "To be found        ",
                "to find",
                4);
        addQustion(q75);
        Qutions_Full q77 = new Qutions_Full("  Each side of DVD can have two layers ...…an enormous capacity  ",
                "give  ",
                "giving",
                "to be given",
                "be giving",
                2);
        addQustion(q77);
        Qutions_Full q78 = new Qutions_Full(" If you double click the icon, it………the program ",
                "will start  ",
                "would start   ",
                "must start ",
                "to start  ",
                1);
        addQustion(q78);
        Qutions_Full q79 = new Qutions_Full(" A gateway is a device ………………dissimilar  networks communicate     ",
                "to enable    ",
                "enabling",
                "enabled  ",
                "was enabled",
                2);
        addQustion(q79);
        Qutions_Full q80 = new Qutions_Full(" Avoid ……………access to PCs",
                "give ",
                "giving ",
                "to be given     ",
                "be giving",
                2);
        addQustion(q80);
        Qutions_Full q81 = new Qutions_Full(" NO, coffee in this lab .We……………drink coffee here.",
                "can’t ",
                "couldn’t",
                "may not    ", // انا بغضون (أسامة)
                "mustn’t",
                4);
        addQustion(q81);
        Qutions_Full q82 = new Qutions_Full(" . …………...You listen to part one. part 2 is downloading ………….",
                "as",
                "when ",
                "before  ",
                "until  ",
                1);
        addQustion(q82);
        Qutions_Full q83 = new Qutions_Full("I……………like to make a course in multimedia",
                "will  ",
                "must",
                "would   ",
                "could",
                3);
        addQustion(q83);
        Qutions_Full q84 = new Qutions_Full(" ATMs……………….Iris certainly this recognition in the future ",
                "will use    ",
                "would use ",
                "must use ",
                "can use   ",
                2);
        addQustion(q84);
        Qutions_Full q85 = new Qutions_Full("  I recommend ………………Pc diagnlidon  tool to make it secure. ",
                "use  ",
                "to use",
                "uses   ",
                "using",
                4);
        addQustion(q85);
        Qutions_Full q86 = new Qutions_Full(" you can ………to secret code to make it secure ",
                "encrypt",
                "encode",
                "enable",
                "enhance",
                2);
        addQustion(q86);
        Qutions_Full q87 = new Qutions_Full(" smart cards prevent unauthorized users……………systems  ",
                "access ",
                "to access",
                "accessing ",
                "accessed",
                3);
        addQustion(q87);
        Qutions_Full q88 = new Qutions_Full(" Hackers try to…………...passwords to penertrate systems ",
                "break down  ",
                "break into",
                "log on  ",
                "find out",
                2);
        addQustion(q88);
        Qutions_Full q89 = new Qutions_Full(" How do you…………. running this machine.  ",
                "go about ",
                "go around ",
                "go down   ",
                "go out",
                2);
        addQustion(q89);
        Qutions_Full q90 = new Qutions_Full(" Calculate all sales ……….…there are no more ",
                "while ",
                "until  ",
                "as",
                "when",
                4);
        addQustion(q90);
        Qutions_Full q91 = new Qutions_Full(" You know main frames well. You …………for IBM before ",
                "must work ",
                "would have worked ",
                "must have worked ",
                "should have worked",
                3);
        addQustion(q91);
        Qutions_Full q92 = new Qutions_Full("You…………a degree in computer science. it is not important ",
                "have not need  ",
                "will be having",
                "do need   ",
                "must need",
                1);
        addQustion(q92);
        Qutions_Full q95 = new Qutions_Full(" you   can   wide    the   picture in your monitor ……….",
                "can",
                "wide  ",
                "the ",
                "picture",
                2);
        addQustion(q95);
        //2014

        Qutions_Full q98 = new Qutions_Full("  …… is a hardware and software combination used to connect the same type of networks. ",
                "gatway",
                "modem",
                "router",
                "bridge",
                2);
        addQustion(q98);
        Qutions_Full q99 = new Qutions_Full(" A …… is a powerful computer storing data shared by all the clients in the network.",
                "server ",
                "LAN",
                "bridge",
                "Hub",
                1);
        addQustion(q99);
        Qutions_Full q100 = new Qutions_Full("An email transfer process in which the connection is initiated by the sending computer is called …",
                "messaging protocol ",
                "push operation ",
                "message retrieval ",
                "email ",
                2);
        addQustion(q100);
        Qutions_Full q101 = new Qutions_Full("..............is a TCP/IP protocol used in sending and receiving e-mail.",
                "ISP",
                "IMAP",
                "SMTP",
                "POP",
                3);
        addQustion(q101);
        Qutions_Full q102 = new Qutions_Full("stored information that is used to route data through a gateway is .....",
                "internet address",
                "transmission protocol.",
                "resolution protocol ",
                "look-up table",
                4);
        addQustion(q102);
        Qutions_Full q103 = new Qutions_Full("A 32-bit number identifying a node on an IP network is called a/an ……",
                "allocation ",
                "Internet address ",
                "memory address",
                "bus address",
                2);
        addQustion(q103);
        Qutions_Full q104 = new Qutions_Full("Downloading a file from a server is an example of ……",
                "User Datagram protocol",
                "Internet protocol",
                "File Transfer Protocol",
                "Transmission Control Protocol",
                3);
        addQustion(q104);
        Qutions_Full q105 = new Qutions_Full("The ..... is the only part of a communication process that the user sees.",
                "network layer",
                "application layer ",
                "physical layer ",
                "data-link layer",
                2);
        addQustion(q105);
        Qutions_Full q106 = new Qutions_Full("The …… encodes and sends the packets.",
                "physical layer",
                "transport layer ",
                "session layer ",
                "presentation layer ",
                1);
        addQustion(q106);
        Qutions_Full q107 = new Qutions_Full("…… is a compressed video frame known as a predicated frame.",
                "0-frame ",
                "I-frame ",
                "SGML",
                "P-frame",
                4);
        addQustion(q107);
        Qutions_Full q108 = new Qutions_Full(" search engines do a better job because of the intelligent …… content.",
                "HTML ",
                "XML ",
                "SGML ",
                "A and C",
                2);
        addQustion(q108);
        Qutions_Full q109 = new Qutions_Full(" Satellite communication is an example of …… ",
                "Integrated networks",
                "cable networks ",
                "wireless connections ",
                "splitter",
                1);
        addQustion(q109);
        Qutions_Full q110 = new Qutions_Full(" WAP phones will ………. revolutionize the way we communicate.",
                "Provable ",
                "importable ",
                "probable ",
                "probably",
                4);
        addQustion(q110);
        Qutions_Full q111 = new Qutions_Full(" It is ….. that taxis will be robot- controlled. ",
                "luckily ",
                "unlike",
                "liked",
                "likely ",
                4);
        addQustion(q111);
        Qutions_Full q112 = new Qutions_Full(" you cannot save the file …… You name it.",
                "while ",
                "after ",
                "when",
                "until",
                4);
        addQustion(q112);
        Qutions_Full q113 = new Qutions_Full("If you spilled coffee on the keyboard, you …… it. ",
                "Will damage ",
                "would damage",
                "would damaged ",
                "damaged ",
                2);
        addQustion(q113);
        Qutions_Full q114 = new Qutions_Full(" If you …….. print screen, you ….. a copy of the screen.",
                "press / can make ",
                "pressed / could make ",
                "will press / will make  ",
                "press / would made ",
                1);
        addQustion(q114);
        Qutions_Full q115 = new Qutions_Full("If there ….. a power cut while you were using the computer, you might lose unsaved data.",
                "is ",
                "was  ",
                "were ",
                "has been",
                3);
        addQustion(q115);
        Qutions_Full q116 = new Qutions_Full("It is a good idea ……. A few large websites …… some ideas on designing an effective site map",
                "visit / gets  ",
                "will visit / will get 	",
                "visit / get ",
                "to visit / to get ",
                4);
        addQustion(q116);
        Qutions_Full q117 = new Qutions_Full("Avoid …… A search function in your website as much as possible.",
                "using ",
                "use 	",
                "used ",
                "to use ",
                1);
        addQustion(q117);
        Qutions_Full q118 = new Qutions_Full("Once the DNS server …… the IP address, it sends it back to the browser.",
                "to find  ",
                "will find ",
                "has found ",
                "found ",
                3);
        addQustion(q118);
        Qutions_Full q119 = new Qutions_Full("Streaming is a way of dealing with bandwidth problems ……… you download video from the internet.",
                "when",
                "after ",
                "until ",
                "once ",
                1);
        addQustion(q119);
        Qutions_Full q120 = new Qutions_Full("Exercise in the morning rather than ……",
                "smoke ",
                "smoking ",
                "to smoke ",
                "smokes ",
                2);
        addQustion(q120);
        Qutions_Full q121 = new Qutions_Full("olient is a network computer …… for accessing a service on a server",
                "use ",
                "to use ",
                "using ",
                "used ",
                4);
        addQustion(q121);
        Qutions_Full q122 = new Qutions_Full("router is a special computer ….. messages when several networks are linked",
                "to direct ",
                "directing ",
                "directed ",
                "directs ",
                2);
        addQustion(q122);
        Qutions_Full q123 = new Qutions_Full("Each MP3 file has a tag ……… extra information to be stored on other track details.",
                "allows 	",
                "to allow ",
                "allowing ",
                "allow ",
                3);
        addQustion(q123);
        //TODO // 124 125 126 127 128
        Qutions_Full q129 = new Qutions_Full("Metadata ",
                "Set boundaries for the beginning and end-of message.",
                "Mathematical calculations based on the content of data.",
                "A coding system used for structuring and formatting documents.",
                "Data about data.",
                4);
        addQustion(q129);
        Qutions_Full q130 = new Qutions_Full("Pull operation ",
                "stored information used to route data through a gateway.",
                "A formula used for decompressing components of a data stream.",
                "The main transmission path handling major data traffic connecting LANs together.",
                "A mail transfer process in which  the receiving computer initiates the  connection.",
                4);
        addQustion(q130);
        Qutions_Full q131 = new Qutions_Full("Look-up table ",
                "stored information used to route data through a gateway.",
                "A formula used for decompressing components of a data stream.",
                "The main transmission path handling major data traffic connecting LANs together.",
                "A mail transfer process in which  the receiving computer initiales the  connection.",
                1);
        addQustion(q131);
        Qutions_Full q132 = new Qutions_Full("Bracketing",
                "Set boundaries for the beginning and end-of message.",
                "Mathematical calculations based on the content of data.",
                "A coding system used for structuring and formatting documents.",
                "Data about data.",
                1);
        addQustion(q132);
        Qutions_Full q133 = new Qutions_Full(".Algorithm",
                "stored information used to route data through a gateway.",
                "A formula used for decompressing components of a data stream.",
                "The main transmission path handling major data traffic connecting LANs together.",
                "A mail transfer process in which  the receiving computer initiales the  connection.",
                2);
        addQustion(q133);
        Qutions_Full q134 = new Qutions_Full(".backbone",
                "Set boundaries for the beginning and end-of message.",
                "Mathematical calculations based on the content of data.",
                "A coding system used for structuring and formatting documents.",
                "Main transmission path handling major data traffic.",
                4);
        addQustion(q134);
        Qutions_Full q135 = new Qutions_Full(".Checksum",
                "Set boundaries for the beginning and end-of message.",
                "Mathematical calculations based on the content of data.",
                "A coding system used for structuring and formatting documents.",
                "Data about data.",
                2);
        addQustion(q135);
        Qutions_Full q136 = new Qutions_Full(".Markup Language",
                "Set boundaries for the beginning and end-of message.",
                "Mathematical calculations based on the content of data.",
                "A coding system used for structuring and formatting documents.",
                "Data about data.",
                3);
        addQustion(q136);
        Qutions_Full q137 = new Qutions_Full("TA",
                "Target Area",
                "Technology Assessment",
                "Technical Assistance",
                "Terminal Adaptor ",
                4);
        addQustion(q137);
        Qutions_Full q138 = new Qutions_Full("TA",
                "Target Area",
                "Technology Assessment",
                "Technical Assistance",
                "Terminal Adaptor ",
                4);
        addQustion(q138);
        Qutions_Full q139 = new Qutions_Full("DSL",
                "Digital Subscriber Line ",
                "Document Summary List ",
                "Direct Swift Link ",
                "Direct Service Line ",
                1);
        addQustion(q139);
        Qutions_Full q140 = new Qutions_Full("SMS",
                "System Management ",
                "Short Message Service ",
                "School Management System ",
                "Stock market Simulation ",
                2);
        addQustion(q140);
        Qutions_Full q141 = new Qutions_Full("POP",
                "point Of Purchase",
                "Plenty Of Parking ",
                "Post Office Protocol",
                "Point Of Presence",
                3);
        addQustion(q141);
        Qutions_Full q142 = new Qutions_Full("IP",
                "Internet protocol",
                "Intellectual Property",
                "Image Processing ",
                "Interaction Point ",
                1);
        addQustion(q142);
        Qutions_Full q143 = new Qutions_Full("LAN",
                "Live Action Network",
                "Local Available Names ",
                "Local Access Network ",
                "Learn Advisory Network",
                3);
        addQustion(q143);
        Qutions_Full q144 = new Qutions_Full("Web",
                "Name",
                "Board  ",
                "Program  ",
                "Page ",
                4);
        addQustion(q144);
        Qutions_Full q145 = new Qutions_Full("File",
                "Name",
                "Board  ",
                "Program  ",
                "Page ",
                1);
        addQustion(q145);
        Qutions_Full q146 = new Qutions_Full("Bulletin",
                "Name",
                "Board  ",
                "Program  ",
                "Page ",
                2);
        addQustion(q146);
        Qutions_Full q147 = new Qutions_Full("Supervisor",
                "Name",
                "Board  ",
                "Program  ",
                "Page ",
                3);
        addQustion(q147);
        Qutions_Full q148 = new Qutions_Full("barcode",
                "engine",
                "tray",
                "computer ",
                "reader ",
                4);
        addQustion(q148);
        Qutions_Full q149 = new Qutions_Full("mainframe",
                "engine",
                "tray",
                "computer ",
                "reader ",
                3);
        addQustion(q149);
        Qutions_Full q150 = new Qutions_Full("system…. ",
                "engine",
                "tray",
                "computer ",
                "reader ",
                2);
        addQustion(q150);
        Qutions_Full q151 = new Qutions_Full("search  …. ",
                "engine",
                "tray",
                "computer ",
                "reader ",
                1);
        addQustion(q151);
        Qutions_Full q152 = new Qutions_Full("PUSH” operation  ….  ",
                "set boundaries for beginning and end of a message  ",
                "an email process in which the connection is initialized by sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving ",
                2);
        addQustion(q152);
        Qutions_Full q153 = new Qutions_Full(" Internet address .  ",
                "set boundaries for beginning and end of a message  ",
                "an email process in which the connection is initialized by sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving ",
                3);
        addQustion(q153);
        Qutions_Full q154 = new Qutions_Full(" Full-duplex   ",
                "set boundaries for beginning and end of a message  ",
                "an email process in which the connection is initialized by sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving ",
                4);
        addQustion(q154);
        Qutions_Full q155 = new Qutions_Full("bracketing ",
                "set boundaries for beginning and end of a message  ",
                "an email process in which the connection is initialized by sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving ",
                1);
        addQustion(q155);

        Qutions_Full q156 = new Qutions_Full("backbone ",
                "Main transmission path handling major data traffic ",
                "an email process in which the connection is initialized by sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving ",
                1);
        addQustion(q156);

        Qutions_Full q157 = new Qutions_Full("GPS : ",
                "Globular placement shape",
                "Global Positioning System",
                "Globe position setup",
                "Globe place second",
                2);
        addQustion(q157);
        Qutions_Full q158 = new Qutions_Full("IMAP : ",
                "Insurance mail achievement protocols",
                "Internet mail access protocol",
                "Idea main arrivals people",
                "Information magazine arrive paper",
                2);
        addQustion(q158);
        Qutions_Full q159 = new Qutions_Full("LAN : ",
                "Local area network",
                "Logical ambit net",
                "Lose account number ",
                "Lucky administrator new",
                1);
        addQustion(q159);
        Qutions_Full q160 = new Qutions_Full("POP : ",
                "Piece of Popular",
                "Place or Put ",
                "Point of Presence",
                "Post Office Protocol",
                4);
        addQustion(q160);
        Qutions_Full q161 = new Qutions_Full("FTP : ",
                "Folder transmit protocols",
                "Fold transmission package",
                "File transfer protocol  ",
                "Factory tea paper",
                3);
        addQustion(q161);
        Qutions_Full q162 = new Qutions_Full("TA ",
                "Top adapter",
                "Terminal adapter",
                "Transformation adapt ",
                "Transponders alter",
                2);
        addQustion(q162);
        Qutions_Full q163 = new Qutions_Full("DSL  ",
                "Digital subscriber line",
                "Danger supporter lane ",
                "Daily site library ",
                "Different second lineup",
                1);
        addQustion(q163);
        Qutions_Full q164 = new Qutions_Full("SMS ",
                "Summarized Missive Screen ",
                "Stumpy Medicine Server ",
                "Short Message Server ",
                "Spotty Map Mection",
                3);
        addQustion(q164);
        Qutions_Full q165 = new Qutions_Full("Mp3 :",
                "Music Player 3",
                "MHz Audio Layer 3",
                "MIDI Player 3 ",
                "MPEG Audio Layer 3",
                4);
        addQustion(q165);
        Qutions_Full q166 = new Qutions_Full("IP :",
                "Internet Problem",
                "Internet Protocol",
                "Internet Provider",
                "Internet Penetration",
                2);
        addQustion(q166);
        Qutions_Full q167 = new Qutions_Full("ASP : ",
                "Application Service Provider.",
                "Average Sales Price ",
                "Advanced Simple Profile ",
                "Alternative School Programs",
                1);
        addQustion(q167);
        Qutions_Full q168 = new Qutions_Full("NIC : ",
                "Network Internet Capacity",
                "Network Internet Card",
                "Network Internet Capable",
                "Network Internet Capacitor ",
                2);
        addQustion(q168);
        Qutions_Full q169 = new Qutions_Full("TCP ",
                "Translation Control Problem  ",
                "Transmission Control Provider",
                "Transmission Control Protocol",
                "Translation Control Protocol   ",
                3);
        addQustion(q169);
        Qutions_Full q170 = new Qutions_Full("UDP  ",
                "User Datagram Problem ",
                "User Database Provider ",
                "User Database Problem",
                "User Datagram Protocol",
                4);
        addQustion(q170);
        Qutions_Full q171 = new Qutions_Full("DNS ",
                " Domain Name Service",
                "Domain Name System",
                "Dominant Name Service",
                "Dome Name System",
                2);
        addQustion(q171);
        Qutions_Full q172 = new Qutions_Full("SMTP ",
                " Simple Mail Transfer Protocol ",
                "Small Message Transfer Protocol ",
                "Simple Mail Transfer Provider",
                "Simple  Message Transfer Protocol",
                1);
        addQustion(q172);
        Qutions_Full q173 = new Qutions_Full("HTML : ",
                " Hyper Text Market Language ",
                "Hyper Text Markup Language  ",
                "Hygiene Text Market Language ",
                "Hygiene Text Markup Language",
                2);
        addQustion(q173);
        Qutions_Full q174 = new Qutions_Full("SGML :  ",
                "Standard General Markup Language ",
                "Standard Generalized Market Language ",
                "Standard Generalized Markup Language ",
                "Standard General Market Language",
                3);
        addQustion(q174);
        Qutions_Full q175 = new Qutions_Full("DTP : ",
                "Desk Transfer Process",
                "Design Transfer Point  ",
                "Design Transfer Protocol",
                "Desktop Publisher ",
                4);
        addQustion(q175);
        Qutions_Full q176 = new Qutions_Full("ISP :",
                "Internet Server Provider ",
                "Internet System Problem ",
                "Internet Server Protocol.",
                "Internet System Penetration ",
                1);
        addQustion(q176);
        Qutions_Full q177 = new Qutions_Full("IRC",
                "Information Resource Center  ",
                "Internet Relay Chat ",
                "Innovation Relay Center",
                "International Relations Center",
                2);
        addQustion(q177);
        Qutions_Full q178 = new Qutions_Full("PIM :    ",
                "Personal Information Manager",
                "Personal Information Machine  ",
                "Personality Intelligent Manager ",
                "Product information management ",
                1);
        addQustion(q178);
        Qutions_Full q179 = new Qutions_Full("DVD :  ",
                "Dependable Video Discus  ",
                "Digital Virus Disease",
                "Dual View Display",
                "Digital Video Disk ",
                4);
        addQustion(q179);

        Qutions_Full q180 = new Qutions_Full(".aero : ",
                "Auto industry ",
                "Aviation industry",
                "aviation management",
                "Auto management",
                2);
        addQustion(q180);
        Qutions_Full q181 = new Qutions_Full(".biz : ",
                "business card ",
                "business man ",
                "businesses",
                "bizarre",
                3);
        addQustion(q181);
        Qutions_Full q182 = new Qutions_Full(".com  : ",
                "Community",
                "Communication",
                "Company ",
                "Commercial",
                4);
        addQustion(q182);
        Qutions_Full q183 = new Qutions_Full(".coop  : ",
                "Cooperatives",
                "Cooper",
                "Cooperate ",
                "Coopers",
                1);
        addQustion(q183);
        Qutions_Full q184 = new Qutions_Full(".edu  or .ac : ",
                "Educational and entertainment",
                "Educational and research",
                "educational institution",
                "educational attainment",
                2);
        addQustion(q184);
        Qutions_Full q185 = new Qutions_Full(".gov : ",
                "Govt",
                "Governments",
                "Government",
                "govt employee",
                3);
        addQustion(q185);
        Qutions_Full q186 = new Qutions_Full(".info : ",
                "Inform ",
                "Special use",
                "Information",
                "General use",
                4);
        addQustion(q186);
        Qutions_Full q187 = new Qutions_Full(".int  : ",
                "International organization ",
                "international relations",
                "international trade",
                "international business",
                1);
        addQustion(q187);

        //TODO 188
        Qutions_Full q189 = new Qutions_Full(".mil  : ",
                "Military agency ",
                "military service",
                "military status",
                "military base",
                1);
        addQustion(q189);
        Qutions_Full q190 = new Qutions_Full(".museums  : ",
                "Museums and art galleries ",
                "Museums",
                "museums and galleries",
                "masculine",
                2);
        addQustion(q190);
        Qutions_Full q191 = new Qutions_Full(".name  : ",
                "Names ",
                "Nameplates ",
                "Individuals",
                "nameplate data",
                3);
        addQustion(q191);
        Qutions_Full q192 = new Qutions_Full(".net  : ",
                "gateway or internet ",
                "gateway drug ",
                "gateway city",
                "Gateway or host",
                4);
        addQustion(q192);
        Qutions_Full q193 = new Qutions_Full(".org  : ",
                "non-profit Organization ",
                "profit Organization  ",
                "Education Organization",
                "World Health Organization",
                1);
        addQustion(q193);
        Qutions_Full q194 = new Qutions_Full(".pro  : ",
                "Professional ",
                "Professionals  ",
                "Programs ",
                "Social Networking Programs",
                2);
        addQustion(q194);
        Qutions_Full q195 = new Qutions_Full(".firm  : ",
                "Informatics  ",
                "Firm  ",
                "Informative ",
                "Firms ",
                3);
        addQustion(q195);
        Qutions_Full q196 = new Qutions_Full(".nom   : ",
                "Recreational  ",
                "Entertainment  ",
                "nomination ",
                "nomination form ",
                1);
        addQustion(q196);
        Qutions_Full q197 = new Qutions_Full(".store   : ",
                "Stores   ",
                "Cultural or entertainment  ",
                "Store",
                "cultural or recreational",
                2);
        addQustion(q197);
        Qutions_Full q198 = new Qutions_Full(".web   : ",
                "Website    ",
                "Web",
                "Personal  ",
                "Websites",
                3);
        addQustion(q198);
        Qutions_Full q199 = new Qutions_Full(".arts   : ",
                "arts and cultures    ",
                "art and design",
                "Artist   ",
                "Film or agency",
                4);
        addQustion(q199);
        Qutions_Full q200 = new Qutions_Full(".rec   : ",
                "Online retail shop",
                "receive payments",
                "receive    ",
                "Receive payments",
                1);
        addQustion(q200);
        Qutions_Full q201 = new Qutions_Full(".info    : ",
                "Information security ",
                "Information ",
                "Informative    ",
                " information technology",
                2);
        addQustion(q201);


        // compound nouns
        Qutions_Full q202 = new Qutions_Full("Barcode : ",
                "Engine",
                "Reader",
                "Computer",
                "Printer",
                2);
        addQustion(q202);
        Qutions_Full q203 = new Qutions_Full("Mainframe : ",
                "Engine",
                "Reader",
                "Computer",
                "Printer",
                3);
        addQustion(q203);
        Qutions_Full q204 = new Qutions_Full("Laser : ",
                "Engine",
                "Reader",
                "Computer",
                "Printer",
                4);
        addQustion(q204);
        Qutions_Full q205 = new Qutions_Full("Expansion : ",
                "Card",
                "Reader",
                "Computer",
                "Printer",
                1);
        addQustion(q205);
        Qutions_Full q206 = new Qutions_Full("Search : ",
                "Card",
                "Engine",
                "Computer",
                "Printer",
                2);
        addQustion(q206);
        Qutions_Full q207 = new Qutions_Full("Control : ",
                "Card",
                "Engine",
                "Bus",
                "Printer",
                3);
        addQustion(q207);
        Qutions_Full q208 = new Qutions_Full("Supervisor : ",
                "Card",
                "Engine",
                "Bus",
                "Program",
                4);
        addQustion(q208);
        Qutions_Full q209 = new Qutions_Full("Task : ",
                "Bar",
                "Engine",
                "Bus",
                "Program",
                1);
        addQustion(q209);
        Qutions_Full q210 = new Qutions_Full("System : ",
                "Bar",
                "Tray",
                "Bus",
                "Program",
                2);
        addQustion(q210);
        Qutions_Full q211 = new Qutions_Full("Explorer : ",
                "Bar",
                "Tray",
                "Bus",
                "Program",
                4);
        addQustion(q211);
        Qutions_Full q212 = new Qutions_Full("Bulletin : ",
                "Bar",
                "Tray",
                "Board",
                "Program",
                3);
        addQustion(q212);
        Qutions_Full q213 = new Qutions_Full("Domain : ",
                "Bar",
                "Tray",
                "Board",
                "Link",
                4);
        addQustion(q213);
        Qutions_Full q214 = new Qutions_Full("File : ",
                "Name",
                "Tray",
                "Board",
                "Link",
                1);
        addQustion(q214);
        Qutions_Full q215 = new Qutions_Full("Graphical : ",
                "Name",
                "Button",
                "Board",
                "Link",
                2);
        addQustion(q215);
        Qutions_Full q216 = new Qutions_Full("Mobile : ",
                "Name",
                "Button",
                "Phone",
                "Link",
                3);
        addQustion(q216);
        Qutions_Full q217 = new Qutions_Full("Search : ",
                "Name",
                "Button",
                "Engine",
                "Link",
                3);
        addQustion(q217);
        Qutions_Full q218 = new Qutions_Full("Site : ",
                "Name",
                "Button",
                "Engine",
                "Map",
                4);
        addQustion(q218);
        Qutions_Full q219 = new Qutions_Full("Synchronous : ",
                "Transmission",
                "Button",
                "Engine",
                "Map",
                1);
        addQustion(q219);
        Qutions_Full q220 = new Qutions_Full("Text : ",
                "Transmission",
                "Message",
                "Engine",
                "Map",
                2);
        addQustion(q220);
        Qutions_Full q221 = new Qutions_Full("Web : ",
                "Transmission",
                "Message",
                "Engine",
                "Page",
                4);
        addQustion(q221);


        // 2012   دورة
        Qutions_Full q222 = new Qutions_Full("………………. Is a process of spreading data across a set of disks. ",
                "Mirroring  ",
                "Striping ",
                "Parity data",
                "RAID",
                2);
        addQustion(q222);
        Qutions_Full q223 = new Qutions_Full("In a virus routine, the…………….. decides when how to activate the pay load. ",
                "Misdirection. ",
                "reproduction ",
                "trigger",
                "instruction",
                3);
        addQustion(q223);
        Qutions_Full q224 = new Qutions_Full("You can ………………. Data to make it secure. ",
                "Ensure ",
                "enhance ",
                "encrypt   ",
                "encode",
                3);
        addQustion(q224);
        Qutions_Full q225 = new Qutions_Full("Designers can offer good ideas for ……………. Your website.  ",
                "Bright",
                "making bright",
                "brighten",
                "brightening",
                4);
        addQustion(q225);
        Qutions_Full q226 = new Qutions_Full("Designers can offer good ideas for ……………. Your website.  ",
                "Bright",
                "making bright",
                "brighten",
                "brightening",
                4);
        addQustion(q226);
        Qutions_Full q227 = new Qutions_Full("……………… the technician in peak times.",
                "Never phone",
                "recommend",
                "avoid",
                "phone",
                1);
        addQustion(q227);
        Qutions_Full q228 = new Qutions_Full("The besting to do is to ……………. The sound drivers.",
                "Figure out",
                "note",
                "reinstall ",
                "describe",
                3);
        addQustion(q228);
        Qutions_Full q229 = new Qutions_Full("A computer virus is a very …………… program routine that infects a computer. ",
                "Complicated  ",
                "small",
                "biological.",
                "simple ",
                4);
        addQustion(q229);
        Qutions_Full q230 = new Qutions_Full(" A gateway ……………. Dissimilar networks to communicate.",
                "Encrypts ",
                "enables",
                "enhances ",
                "enlarges",
                2);
        addQustion(q230);
        Qutions_Full q231 = new Qutions_Full(" A program that has a payload, but doesn't have a reproduction routine is known as a/an ………….",
                "Logic bomb ",
                "belling ",
                "Trojan ",
                "Anti EXE.",
                3);
        addQustion(q231);
        Qutions_Full q232 = new Qutions_Full(" The technique of writing the same information to more than one drive is known as ………………. .",
                "Controlling     ",
                "striping ",
                "spreading   ",
                "mirroring",
                4);
        addQustion(q232);
        Qutions_Full q233 = new Qutions_Full("………………… channel is a digital channel used to carry ISDN signaling and supervisory information to the network..",
                "Splitter   ",
                "bearer",
                "data  ",
                "streaming. ",
                1);
        addQustion(q233);
        Qutions_Full q234 = new Qutions_Full("Computers connected to a satiate system ……………….. a modem",
                "Used ",
                "don't operate by.   ",
                "don't need.",
                "operate over.",
                1);
        addQustion(q234);
        Qutions_Full q235 = new Qutions_Full("	DSL system use ………… signals.",
                "Special    ",
                "normal",
                "digital",
                "analogue",
                3);
        addQustion(q235);
        Qutions_Full q236 = new Qutions_Full("	Compared to the downstream bandwidth the upstream bandwidth in an ADSL line is ……………….",
                "Larger  ",
                "wider",
                "smaller",
                "the same.",
                3);
        addQustion(q236);
        Qutions_Full q237 = new Qutions_Full("	The fastest of all RAID configurations is ............",
                "RAID O ",
                "RAID 1",
                "RAID 2-4",
                "RAID S",
                1);
        addQustion(q237);
        Qutions_Full q238 = new Qutions_Full("	………………… can audio compress digitized sound data.",
                "VOIP ",
                "codecs ",
                "jitter",
                "A buffer",
                2);
        addQustion(q238);
        Qutions_Full q239 = new Qutions_Full("	Latency is the …………….. Between the packets reaching the receiver and you hearing the sound.",
                "Jitter",
                "delay.",
                "speed.",
                "loss.",
                2);
        addQustion(q239);
        Qutions_Full q240 = new Qutions_Full("	A ……….. is a network connecting computers over a small distance such as within a company.",
                "LAN ",
                "wireless ",
                "Bluetooth   ",
                "internet.",
                1);
        addQustion(q240);
        Qutions_Full q241 = new Qutions_Full("	A ……….. is a digital channel used to carry ISDN data.",
                "Data channel ",
                "bearer channel",
                "ISNA channel",
                "ADSL channel",
                2);
        addQustion(q241);

        Qutions_Full q242 = new Qutions_Full("	The GPS was developed by………",
                "Microsoft  ",
                "an international organization",
                "An  aviation corporation ",
                "the US military ",
                4);
        addQustion(q242);
        Qutions_Full q243 = new Qutions_Full("	A ………………. Is a network transmission path handing major traffic ",
                "Backbone ",
                "server ",
                "gateway     ",
                "bridge",
                1);
        addQustion(q243);
        Qutions_Full q244 = new Qutions_Full("	A …………….. is an electronic device connecting  all the data cabling in a network",
                "Thin client   ",
                "LAN",
                "hub ",
                "gateway",
                3);
        addQustion(q244);
        Qutions_Full q245 = new Qutions_Full("	A …………….. is an electronic device connecting  all the data cabling in a network",
                "Thin client   ",
                "LAN",
                "hub ",
                "gateway",
                3);
        addQustion(q245);
        Qutions_Full q246 = new Qutions_Full(" HTML is an example of a page presentation…………",
                "Document ",
                "data",
                "link",
                "language",
                4);
        addQustion(q246);
        Qutions_Full q247 = new Qutions_Full(" POP protocol allows the user to download …………………at once in your mailbox",
                "One message  ",
                "all messages ",
                "storage devices     ",
                "communication devices.",
                2);
        addQustion(q247);
        Qutions_Full q248 = new Qutions_Full(" Email messages are stored in an SMTP system on ………",
                "The receiving server",
                "sending server ",
                "desktop ",
                "mailbox",
                2);
        addQustion(q248);
        Qutions_Full q249 = new Qutions_Full(" ……………… is a network computer used for accessing a service on a server.",
                "Thin client    ",
                "client  ",
                "bridge  ",
                "minicomputer ",
                2);
        addQustion(q249);
        Qutions_Full q250 = new Qutions_Full(" the number of channel used by an ISDN system is ………………",
                "two",
                "three ",
                "four",
                "five",
                2);
        addQustion(q250);
        Qutions_Full q251 = new Qutions_Full(" A device installed on a PC to allow it to receive ISDN signals is a ………",
                "Modem",
                "router",
                "huh ",
                "TA",
                4);
        addQustion(q251);
        Qutions_Full q252 = new Qutions_Full(" The wireless alternatives come in ………….forms",
                "Two  ",
                "three ",
                "four ",
                "five",
                1);
        addQustion(q252);
        Qutions_Full q253 = new Qutions_Full("	DSL based services are a …………. Option when compared to other solution offering similar bandwidth",
                "High cost ",
                "low cost ",
                "average cost",
                "reasonable ",
                2);
        addQustion(q253);
        Qutions_Full q254 = new Qutions_Full("	The part of the address www refers to ……..",
                "Web service ",
                "denial  ",
                "directory path  ",
                "protocol prefix",
                1);
        addQustion(q254);
        Qutions_Full q255 = new Qutions_Full("	The extension nom  stands for …….",
                "Informative ",
                "recreational",
                "firm and company ",
                "non-profit organization",
                2);
        addQustion(q255);
        Qutions_Full q256 = new Qutions_Full("………… is a mail transfer protocol that initially only retrieves the message hander's .",
                "IMAP",
                "SMTP  ",
                "POP   ",
                "push operation ",
                1);
        addQustion(q256);
        Qutions_Full q257 = new Qutions_Full("When you click on the Home button the ……… display your starting webpage. ",
                "Browser  ",
                "search engine  ",
                "server ",
                "hyperlink  ",
                1);
        addQustion(q257);
        Qutions_Full q258 = new Qutions_Full("	……………. Is a language from which you can create other languages  ",
                "Mate data ",
                "Meta language ",
                "format language ",
                "Markup language",
                2);
        addQustion(q258);
        Qutions_Full q260 = new Qutions_Full("	…………… is an email transfer process in which the receiving computer initiates the connection",
                "SMTP ",
                "POP ",
                "IMAP ",
                "pull operation ",
                2);
        addQustion(q260);
        Qutions_Full q261 = new Qutions_Full("XML is a …………. Markup language than HTML .",
                "An intelligent as  ",
                "less intelligent ",
                "more intelligent ",
                "less flexible",
                3);
        addQustion(q261);
        Qutions_Full q262 = new Qutions_Full("A ………… is a special computer directing messages when several networks are linked. …………",
                "Server ",
                "gate way  ",
                "bridge  ",
                "outer ",
                4);
        addQustion(q262);
        Qutions_Full q263 = new Qutions_Full("…………… is a redundant array of inexpensive disk",
                "A raid controller ",
                "an array     ",
                "a raid ",
                "a controller",
                3);
        addQustion(q263);
        Qutions_Full q264 = new Qutions_Full("If i.........time,I'd  like to bulid in new linkes",
                "Have  ",
                "has",
                "would have",
                "had   ",
                4);
        addQustion(q264);
        Qutions_Full q265 = new Qutions_Full("…………….. you click on a URL, your browser sends it to a DNS servere",
                "As ",
                "until ",
                "before ",
                "when ",
                4);
        addQustion(q265);
        Qutions_Full q266 = new Qutions_Full("he teacher is using new program to …… children to write stories",
                "Enable",
                "encourage    ",
                "enhance ",
                "ensure",
                2);
        addQustion(q266);
        Qutions_Full q267 = new Qutions_Full("The packets are passed from router ………..they reach the Web server",
                "as ",
                "until ",
                "before ",
                "when ",
                2);
        addQustion(q267);
        Qutions_Full q268 = new Qutions_Full("I guess, I …………have better job prospects if chose the master's",
                "Will",
                "could",
                "can ",
                "should",
                2);
        addQustion(q268);
        Qutions_Full q269 = new Qutions_Full("Before you call a technical support, you …… reboot your OC to see if the problem recurs..",
                "must ",
                "recommend",
                "should ",
                "would",
                1);
        addQustion(q269);
        Qutions_Full q270 = new Qutions_Full("	if choose the certificate, it ………… take 6 months ",
                "will  ",
                "would",
                "could",
                "might",
                1);
        addQustion(q270);
        Qutions_Full q271 = new Qutions_Full("if you spilled coffee on the keyboard, you …………. Damage it ",
                "Will ",
                "are going to  ",
                "would ",
                "can",
                3);
        addQustion(q271);


        // 2015 add it


        Qutions_Full q272 = new Qutions_Full("Web :  ",
                "Name",
                "Board",
                "Program",
                "page ",
                4);
        addQustion(q272);

        Qutions_Full q273 = new Qutions_Full("File   :  ",
                "Name",
                "Board",
                "Program",
                "page ",
                1);
        addQustion(q273);
        Qutions_Full q274 = new Qutions_Full("Bulletin   :  ",
                "Name",
                "Board",
                "Program",
                "page ",
                2);
        addQustion(q274);
        Qutions_Full q275 = new Qutions_Full("Supervisor   :  ",
                "Name",
                "Board",
                "Program",
                "page ",
                3);
        addQustion(q275);
        Qutions_Full q276 = new Qutions_Full("Metadata   :  ",
                "stored information used to route data through a gateway	 ",
                "set boundaries for the beginning and end of a message",
                "a mail transfer process in which the receiving computer initiates the connection  ",
                "data about data ",
                4);
        addQustion(q276);
        Qutions_Full q277 = new Qutions_Full("Pull operation   :  ",
                "stored information used to route data through a gateway	 ",
                "set boundaries for the beginning and end of a message",
                "a mail transfer process in which the receiving computer initiates the connection  ",
                "data about data ",
                3);
        addQustion(q277);
        Qutions_Full q278 = new Qutions_Full("Look-up table   :  ",
                "stored information used to route data through a gateway	 ",
                "set boundaries for the beginning and end of a message",
                "a mail transfer process in which the receiving computer initiates the connection  ",
                "data about data ",
                1);
        addQustion(q278);
        Qutions_Full q279 = new Qutions_Full("Bracketing  :  ",
                "stored information used to route data through a gateway	 ",
                "set boundaries for the beginning and end of a message",
                "a mail transfer process in which the receiving computer initiates the connection  ",
                "data about data ",
                2);
        addQustion(q279);
        Qutions_Full q280 = new Qutions_Full("Algorithm  :  ",
                "formula used for decompressing components of  a data  stream ",
                "set boundaries for the beginning and end of a message",
                "a mail transfer process in which the receiving computer initiates the connection  ",
                "data about data ",
                1);
        addQustion(q280);
        Qutions_Full q281 = new Qutions_Full("A backbone  :  ",
                "stored information used to route data through a gateway	 ",
                "Main transmission path handling major data traffic",
                "a mail transfer process in which the receiving computer initiates the connection  ",
                "data about data ",
                2);
        addQustion(q281);
        Qutions_Full q282 = new Qutions_Full("Checksum  :  ",
                "stored information used to route data through a gateway	 ",
                "set boundaries for the beginning and end of a message",
                "Mathematical calculation based on the content of data",
                "data about data ",
                3);
        addQustion(q282);
        Qutions_Full q283 = new Qutions_Full("Markup Language   :  ",
                "stored information used to route data through a gateway	 ",
                "set boundaries for the beginning and end of a message",
                "a mail transfer process in which the receiving computer initiates the connection  ",
                "coding system used for structuring and formatting documents",
                4);
        addQustion(q283);
        Qutions_Full q284 = new Qutions_Full("TA  :  ",
                "Target Area",
                "Technology Assessment",
                "Technical Assistance",
                "Terminal Adapter",
                4);
        addQustion(q284);
        Qutions_Full q285 = new Qutions_Full("DSL  :  ",
                "Digital subscriber Line ",
                "Document Summary List ",
                "Direct Swift Link",
                "Direct Service Line",
                1);
        addQustion(q285);
        Qutions_Full q286 = new Qutions_Full("SMS  :  ",
                "Systems Management  Server",
                "Short Message Service",
                "School Management System",
                "Stock Market Simulation",
                2);
        addQustion(q286);
        Qutions_Full q287 = new Qutions_Full("POP  :  ",
                "Point of Purchase ",
                "Plenty Of Parking",
                "Post Office Protocol",
                "Point Of Presence",
                3);
        addQustion(q287);
        Qutions_Full q288 = new Qutions_Full("LAN  :  ",
                "Live Action Network",
                "Local Available Names",
                "Local Access Network",
                "Learners Advisory Network",
                3);
        addQustion(q288);
        Qutions_Full q289 = new Qutions_Full("A ……. Is an interface that enables dissimilar networks to communicate..  :  ",
                "gateway ",
                "modem",
                "router",
                "bridge",
                1);
        addQustion(q289);
        Qutions_Full q290 = new Qutions_Full("A ……. Is an interface that enables dissimilar networks to communicate..  :  ",
                "gateway ",
                "modem",
                "router",
                "bridge",
                1);
        addQustion(q290);
        Qutions_Full q291 = new Qutions_Full(" A……. is a simple computer comprising a processor and memory, display, keyboard, mouse and hard drives only..  :  ",
                "Thin client",
                "LAN",
                "bridge ",
                "Hub",
                1);
        addQustion(q291);
        Qutions_Full q292 = new Qutions_Full("An email transfer process in which the connection is initialed by the sending computer is called...……  :  ",
                "messaging protocol",
                "push operation",
                "message retrieval  ",
                "email transfer",
                2);
        addQustion(q292);
        Qutions_Full q293 = new Qutions_Full("Downloading a file from a server is an example of ………. :  ",
                "User Datagram Protocol ",
                "Internet Protocol",
                "File Transfer Protocol ",
                "Transmission Control Protocol",
                3);
        addQustion(q293);
        Qutions_Full q294 = new Qutions_Full("The checksum is calculated by the ...……… layer which also reassembles the message segments :  ",
                "physical   ",
                "transport",
                "application",
                "Session",
                2);
        addQustion(q294);
        Qutions_Full q295 = new Qutions_Full("The ……. encodes and sends the packets.:  ",
                "physical layer     ",
                "transport layer      ",
                "session layer     ",
                "presentation layer",
                1);
        addQustion(q295);
        Qutions_Full q297 = new Qutions_Full("Search engines do a better job because of the intelligent .....…. content.   ",
                "HTML       ",
                "XML  ",
                "SGML    ",
                "A and C",
                2);
        addQustion(q297);
        Qutions_Full q298 = new Qutions_Full("GPS was developed by the US military to……….",
                "design machine ",
                "pinpoint locations ",
                "store data",
                "A and C",
                2);
        addQustion(q298);
        Qutions_Full q299 = new Qutions_Full("Once you ………on the hyperlink, you ………. for the webpage to be copied on your computer..",
                "have clicked/wait   ",
                "clicked /waited",
                "will click/will wait      ",
                "are clicking/are waiting",
                1);
        addQustion(q299);
        Qutions_Full q300 = new Qutions_Full("The house has an electronic door-keeper ……… to recognize you …...... access to family only.",
                "program / give         ",
                "programmed / gave     ",
                "programmed / give      ",
                "programmed / giving",
                2);
        addQustion(q300);
        Qutions_Full q301 = new Qutions_Full("If you spilled coffee on the keyboard, you ……. It .",
                "will damage       ",
                "would damage",
                "would damaged",
                "damaged ",
                2);
        addQustion(q301);
        Qutions_Full q302 = new Qutions_Full("If you…… Print screen, you ………. a copy of the screen.",
                "press / can make        ",
                "pressed/could make        ",
                "will press / will make   ",
                "press / would made",
                1);
        addQustion(q302);
        Qutions_Full q303 = new Qutions_Full("If there ……. A power cut while you were using the computer, you might lose unsaved data.",
                "is ",
                "was ",
                "were",
                "has been",
                2);
        addQustion(q303);
        Qutions_Full q304 = new Qutions_Full("It is a good idea ……… a few large websites……... some ideas on designing an effective site map.",
                "visits /gets        ",
                "will visit/will get ",
                "visit/get    ",
                "to visit/to get ",
                4);
        addQustion(q304);
        Qutions_Full q305 = new Qutions_Full("Avoid ……. a search function in your website as much as possible.",
                "using   ",
                "use     ",
                "used  ",
                "to use ",
                1);
        addQustion(q305);
        Qutions_Full q306 = new Qutions_Full("Once the DNS server ……… the IP address, it sends it back to the browser.",
                "to find   ",
                "will find  ",
                "has found ",
                "found",
                3);
        addQustion(q306);
        Qutions_Full q307 = new Qutions_Full("Streaming is a way of dealing with bandwidth problems ………... you download video from the Internet.",
                "when       ",
                "after",
                "until  ",
                "once",
                1);
        addQustion(q307);
        Qutions_Full q308 = new Qutions_Full("Exercise in the morning rather than ……....",
                "smoke",
                "smoking ",
                "to smoke  ",
                "smokes",
                2);
        addQustion(q308);
        Qutions_Full q309 = new Qutions_Full("A client is a network computer ……. For accessing a service on a server",
                "use   ",
                "to use    ",
                "using   ",
                "used ",
                4);
        addQustion(q309);


        //2017


        Qutions_Full q310 = new Qutions_Full(" Barcode : ",
                "Engine",
                "Tray",
                "Computer",
                "Reader ",
                4);
        addQustion(q310);
        Qutions_Full q311 = new Qutions_Full(" mainframe  : ",
                "Engine",
                "Tray",
                "Computer",
                "Reader ",
                3);
        addQustion(q311);
        Qutions_Full q312 = new Qutions_Full(" System  : ",
                "Engine",
                "Tray",
                "Computer",
                "Reader ",
                2);
        addQustion(q312);
        Qutions_Full q313 = new Qutions_Full(" Search  : ",
                "Engine",
                "Tray",
                "Computer",
                "Reader ",
                1);
        addQustion(q313);
        Qutions_Full q314 = new Qutions_Full(" Push” operation   : ",
                "set boundaries for beginning and end of a message ",
                "an email process in which the connection is initialized by the sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving",
                2);
        addQustion(q314);
        Qutions_Full q315 = new Qutions_Full("Internet address : ",
                "set boundaries for beginning and end of a message ",
                "an email process in which the connection is initialized by the sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving",
                3);
        addQustion(q315);
        Qutions_Full q316 = new Qutions_Full("Full-duplex : ",
                "set boundaries for beginning and end of a message ",
                "an email process in which the connection is initialized by the sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving",
                4);
        addQustion(q316);
        Qutions_Full q317 = new Qutions_Full("Bracketing : ",
                "set boundaries for beginning and end of a message ",
                "an email process in which the connection is initialized by the sending computer",
                "a 32-bit number identifying a node on an IP network ",
                "a transmission mode in which each computer takes turns sending and receiving",
                1);
        addQustion(q317);
        Qutions_Full q318 = new Qutions_Full("Backbone : ",
                "collection of related webpages ",
                "high capacity internet connection",
                "a formula used for compressing component of a data stream",
                "the main transmission path handling the major data",
                4);
        addQustion(q318);
        Qutions_Full q319 = new Qutions_Full("Algorithm : ",
                "collection of related webpages ",
                "high capacity internet connection",
                "a formula used for compressing component of a data stream",
                "the main transmission path handling the major data",
                3);
        addQustion(q319);
        Qutions_Full q320 = new Qutions_Full("Broadband : ",
                "collection of related webpages ",
                "high capacity internet connection",
                "a formula used for compressing component of a data stream",
                "the main transmission path handling the major data",
                2);
        addQustion(q320);
        Qutions_Full q321 = new Qutions_Full("Website : ",
                "collection of related webpages ",
                "high capacity internet connection",
                "a formula used for compressing component of a data stream",
                "the main transmission path handling the major data",
                1);
        addQustion(q321);
        Qutions_Full q322 = new Qutions_Full("IP : ",
                "Internet Protocol ",
                "Image Processing ",
                "Interaction Point",
                "Instrument Panel",
                1);
        addQustion(q322);
        Qutions_Full q323 = new Qutions_Full("IRC : ",
                "Information Resource Center ",
                "Internet Relay Chatcessing  ",
                "Innovation Relay Centre",
                "International Relations Center",
                2);
        addQustion(q323);
        Qutions_Full q324 = new Qutions_Full("SMTP : ",
                "Small Mail Transfer Protocol",
                "Strategic Medium Tenn Plan",
                "Simple Mail Transfer Protocol",
                "Simple MessageTransfer Protocol",
                3);
        addQustion(q324);

        Qutions_Full q325 = new Qutions_Full("FTP : ",
                "Failure To Progress",
                "File Transfer Process",
                "File Transfer Protocol",
                "Fuel Transfer Point",
                3);
        addQustion(q325);
        Qutions_Full q326 = new Qutions_Full("ASP : ",
                "Application Service Provider",
                "Average Sales Price",
                "Advanced Simple Profile",
                "Alternative School Programs",
                1);
        addQustion(q326);
        Qutions_Full q327 = new Qutions_Full("A………. is an interface that enables dissimilar networks to communicate. : ",
                "gatway",
                "modem",
                "router",
                "bridge",
                1);
        addQustion(q327);
        Qutions_Full q328 = new Qutions_Full("A………. is a simple computer comprising a processor and memory, display, keyboard, mouse and hard drives only ",
                "Thin client",
                "LAN",
                "bridge",
                "Hub",
                1);
        addQustion(q328);
        Qutions_Full q329 = new Qutions_Full("The checksum is calculated by the ………...layer which also reassembles the message segments. ",
                "physical",
                "transport",
                "application ",
                "session",
                2);
        addQustion(q329);
        Qutions_Full q330 = new Qutions_Full("The…………. encodes and sends the packets. ",
                "physical layer",
                "transport layer",
                "session layer",
                "presentation layer",
                1);
        addQustion(q330);
        Qutions_Full q331 = new Qutions_Full("………….is logging on to your computer at a distance.",
                "IRC",
                "FTP",
                "Telnet",
                "MOOs",
                3);
        addQustion(q331);
        Qutions_Full q332 = new Qutions_Full("A self-replicating program is a/an………....",
                "accounting system",
                "SAP",
                "office suite",
                "virus",
                4);
        addQustion(q332);
        Qutions_Full q333 = new Qutions_Full(" MP3 files can provide information about itself in coded block called a ………..",
                "tag ",
                "URL",
                "layer",
                "sector",
                1);
        addQustion(q333);
        Qutions_Full q334 = new Qutions_Full("A program that rips songs from a CD and turns them into WAV file is a………….",
                "recorder",
                "ripper",
                "encoder ",
                "decoder",
                2);
        addQustion(q334);
        Qutions_Full q335 = new Qutions_Full("A………. network is linked by radio waves",
                "NIC",
                "LAN",
                "Wifi",
                "cable",
                3);
        addQustion(q335);
        Qutions_Full q336 = new Qutions_Full("…………is used by servers and PCs to send mails",
                "TCP/IP",
                "MAPI ",
                "POP",
                "SMTP",
                4);
        addQustion(q336);
        Qutions_Full q337 = new Qutions_Full("……… allows you to download message headers before downloading the message.",
                "TCP/IP",
                "MAPI ",
                "POP",
                "SMTP",
                4);
        addQustion(q337);
        Qutions_Full q338 = new Qutions_Full("Once you………. on the by perlink. You ………. for the webpage to be copied on your computer.",
                "have clicked / wait",
                "clicked / waited",
                "will click / will wait",
                "are clicking / are waiting",
                1);
        addQustion(q338);
        Qutions_Full q339 = new Qutions_Full("You cannot save the file……. you name it.",
                "while",
                "after",
                "when",
                "until",
                4);
        addQustion(q339);
        Qutions_Full q340 = new Qutions_Full("If you spilled coffee on the keyboard. you…….it.",
                "will damage",
                "would damage",
                "would damaged",
                "damaged",
                2);
        addQustion(q340);
        Qutions_Full q341 = new Qutions_Full(" If you……. Print Screen. You……. a copy of the screen",
                "press /can make",
                "pressed / could make   ",
                "will press / will make",
                "press / would made",
                1);
        addQustion(q341);
        Qutions_Full q342 = new Qutions_Full("If there………. a power cut while you were using the computer. you might lose unsaved data.",
                "is ",
                "was ",
                "were ",
                "has been",
                3);
        addQustion(q342);
        Qutions_Full q343 = new Qutions_Full("Avoid……… a search function in your website as much as possible",
                "using",
                "use ",
                "used ",
                "to use",
                1);
        addQustion(q343);
        Qutions_Full q344 = new Qutions_Full("Streaming is a way of dealing with bandwidth problems ……… you download video from the Internet.",
                "when",
                "after    ",
                "until    ",
                "once ",
                1);
        addQustion(q344);
        Qutions_Full q345 = new Qutions_Full("Exercise in the morning rather than……….",
                "smoke",
                "smoking  ",
                "to smoke ",
                "smokes",
                2);
        addQustion(q345);
        addQustion(q344);
        Qutions_Full q346 = new Qutions_Full("Exercise in the morning rather than……….",
                "smoke",
                "smoking  ",
                "to smoke ",
                "smokes",
                2);
        addQustion(q346);
        Qutions_Full q347 = new Qutions_Full("A client is a network computer ………… for accessing a service on a server.",
                "use",
                "to use",
                "using",
                "used",
                4);
        addQustion(q347);
        Qutions_Full q348 = new Qutions_Full("A router is a special computer ……… messages when several networks are linked.",
                "to direct",
                "directing",
                "directed",
                "directs",
                2);
        addQustion(q348);
        Qutions_Full q349 = new Qutions_Full("Mainframe : .",
                "Card ",
                "Reader",
                "Engine ",
                "Computer",
                4);
        addQustion(q349);
        Qutions_Full q350 = new Qutions_Full("Expansion : ",
                "Card ",
                "Reader",
                "Engine ",
                "Computer",
                1);
        addQustion(q350);
        Qutions_Full q351 = new Qutions_Full("Barcode : ",
                "Card ",
                "Reader",
                "Engine ",
                "Computer",
                2);
        addQustion(q351);
        Qutions_Full q352 = new Qutions_Full("Search : ",
                "Card ",
                "Reader",
                "Engine ",
                "Computer",
                3);
        addQustion(q352);
        Qutions_Full q353 = new Qutions_Full("Half duplex : ",
                "a special computer that directs communicating messages when several nets are connected",
                "a self-duplicating program",
                "connection initiated by the sending computer rather than the receiving computer",
                "Each computer take turns sending and receiving",
                4);
        addQustion(q353);
        Qutions_Full q354 = new Qutions_Full("Push” operation : ",
                "special computer that directs communicating messages when several nets are connected",
                "a self-duplicating program",
                "connection initiated by the sending computer rather than the receiving computer",
                "Each computer take turns sending and receiving",
                3);
        addQustion(q354);
        Qutions_Full q355 = new Qutions_Full("Router : ",
                "a special computer that directs communicating messages when several nets are connected",
                "a self-duplicating program",
                "connection initiated by the sending computer rather than the receiving computer",
                "Each computer take turns sending and receiving",
                1);
        addQustion(q355);
        Qutions_Full q356 = new Qutions_Full("Virus : ",
                "a special computer that directs communicating messages when several nets are connected",
                "a self-duplicating program",
                "connection initiated by the sending computer rather than the receiving computer",
                "Each computer take turns sending and receiving",
                2);
        addQustion(q356);
        Qutions_Full q357 = new Qutions_Full("Algorithm : ",
                "stored information used to route data through a gateway ",
                "set boundaries for the beginning and end of a message",
                "the main transmission path handling major data traffic",
                "a formula used for decompressing components of a data stream",
                4);
        addQustion(q357);
        Qutions_Full q358 = new Qutions_Full("Backbone : ",
                "stored information used to route data through a gateway ",
                "set boundaries for the beginning and end of a message",
                "the main transmission path handling major data traffic",
                "a formula used for decompressing components of a data stream",
                3);
        addQustion(q358);
        Qutions_Full q359 = new Qutions_Full("Bracketing : ",
                "stored information used to route data through a gateway ",
                "set boundaries for the beginning and end of a message",
                "the main transmission path handling major data traffic",
                "a formula used for decompressing components of a data stream",
                2);
        addQustion(q359);
        Qutions_Full q360 = new Qutions_Full("Look-up table  : ",
                "stored information used to route data through a gateway ",
                "set boundaries for the beginning and end of a message",
                "the main transmission path handling major data traffic",
                "a formula used for decompressing components of a data stream",
                1);
        addQustion(q360);
        Qutions_Full q361 = new Qutions_Full("Look-up table  : ",
                "stored information used to route data through a gateway ",
                "set boundaries for the beginning and end of a message",
                "the main transmission path handling major data traffic",
                "a formula used for decompressing components of a data stream",
                1);
        addQustion(q361);
        Qutions_Full q362 = new Qutions_Full("PIM : ",
                "Personal Information Manager",
                "Processor in Memory",
                "Print Image Matching",
                "Parallel Inference Machine",
                1);
        addQustion(q362);
        Qutions_Full q363 = new Qutions_Full("ASP : ",
                "Advanced Simple Profile ",
                "Application Service Provider",
                "Advanced Study Program",
                "Analog Signal Processing",
                2);
        addQustion(q363);
        Qutions_Full q364 = new Qutions_Full("DVD : ",
                "Digital Virus Disease  ",
                "Disk Video Digital  ",
                "Digital Video Disc ",
                "Dual View Display",
                3);
        addQustion(q364);
        Qutions_Full q365 = new Qutions_Full("LAN : ",
                "Live Action Network",
                "Local Available Names",
                "Local Access Network",
                "Learners Advisory Network",
                3);
        addQustion(q365);
        Qutions_Full q366 = new Qutions_Full("FTP : ",
                "Failure To Progress",
                "File Transfer Process",
                "File Transfer Protocol",
                "Fuel Transfer Point",
                3);
        addQustion(q366);
        Qutions_Full q367 = new Qutions_Full("TCP : ",
                "Trusted Computer Platform",
                "Traffic Control Point",
                "Transmission Control Protocol",
                "Tool Center Point",
                3);
        addQustion(q367);
        Qutions_Full q368 = new Qutions_Full("POP : ",
                "Point Of Presence",
                "Post Office Protocol",
                "Protocol Of Post Office ",
                "Program Operating Plan ",
                2);
        addQustion(q368);
        Qutions_Full q369 = new Qutions_Full("ORG : ",
                "Organization",
                "Origin ",
                "Open Rights Group  ",
                "Operations Research Group",
                1);
        addQustion(q369);
        Qutions_Full q370 = new Qutions_Full(" A………. is an interface that enables dissimilar networks to communicate.  : ",
                "gatway",
                "modem",
                "router",
                "bridge",
                1);
        addQustion(q370);
        Qutions_Full q371 = new Qutions_Full(" Doctors access a…………..that holds the records of all the patients in the practice. : ",
                "database",
                "LAN",
                "CD",
                "Router",
                1);
        addQustion(q371);
        Qutions_Full q372 = new Qutions_Full(" ..........Logging on to your computer from a distance is an example of ",
                "UDP",
                "IRC",
                "Talent ",
                "FTP",
                3);
        addQustion(q372);
        Qutions_Full q373 = new Qutions_Full(" The checksum is calculated by the ……… layer which also reassembles the message segments. ",
                "physical",
                "transport",
                "application ",
                "Session",
                2);
        addQustion(q373);

        Qutions_Full q375 = new Qutions_Full(" ……. is a simple message transfer protocol used to send messages between servers",
                "ISP",
                "IMAP",
                "SMTP",
                "POP",
                3);
        addQustion(q375);
        Qutions_Full q376 = new Qutions_Full(" The practice manager uses a payroll package based on …… to calculate salaries.",
                "word processor",
                "Spreadsheet ",
                "database",
                "both A and C",
                2);
        addQustion(q376);
        Qutions_Full q377 = new Qutions_Full("  A/AN ………….. is a set of related webpages .",
                "data center",
                "Website ",
                "office suite",
                "both A and C",
                2);
        addQustion(q377);
        Qutions_Full q378 = new Qutions_Full(" The standard developed  for audio  compression is called…………. .",
                "Mp3 ",
                "Audio CD",
                "WAN",
                "MIDI",
                1);
        addQustion(q378);
        Qutions_Full q379 = new Qutions_Full(" The program that converts songs from a CD to W A V files is called ……………. .",
                "ripper",
                "decoder",
                "recorder",
                "both b and c",
                1);
        addQustion(q379);
        Qutions_Full q380 = new Qutions_Full("You can tell which folder is currently being displayed by the ………… icon .",
                "dragged",
                "deleted ",
                "displayed",
                "highlighted",
                4);
        addQustion(q380);
        Qutions_Full q381 = new Qutions_Full("Junk mail are good sources for ……..",
                "viruses",
                "attacks ",
                "services",
                "both A and B",
                1);
        addQustion(q381);
        Qutions_Full q382 = new Qutions_Full("You can refine your search on Google by adding ....…. ",
                "define",
                "Language",
                "more words",
                "brackets",
                1);
        addQustion(q382);
        Qutions_Full q383 = new Qutions_Full("Once you ………on the hyperlink, you ………. for the webpage to be copied on your computer. ",
                "have clicked / wait",
                "clicked / waited ",
                "wil click / will wait ",
                "are clicking / are waiting",
                1);
        addQustion(q383);
        Qutions_Full q384 = new Qutions_Full("You cannot save the file……. you name it. ",
                "while",
                "after",
                "when",
                "until",
                4);
        addQustion(q384);

        Qutions_Full q385 = new Qutions_Full(" If you spilled coffee on the keyboard. you…….it.  ",
                "will damage",
                "would damage",
                "would damaged",
                "damaged",
                2);
        addQustion(q385);
        Qutions_Full q386 = new Qutions_Full("  If you……. Print Screen. You……. a copy of the screen ",
                "press / can make",
                "pressed / could make   ",
                "will press / will make",
                "press / would made",
                1);
        addQustion(q386);
        Qutions_Full q387 = new Qutions_Full(" A MIDI message ……. Sound into 8 bite of digital information ",
                "encode ",
                "encoding ",
                "encoded ",
                "encodes",
                4);
        addQustion(q387);
        Qutions_Full q388 = new Qutions_Full(" Exercise in the morning rather than ……... ",
                "smoke",
                "smoking ",
                "to smoke ",
                "smokes",
                2);
        addQustion(q388);
        Qutions_Full q389 = new Qutions_Full("First ,  enter  the search criteria by ……… on the personal tab. ",
                "click",
                "to click  ",
                "clicking ",
                "clicks",
                3);
        addQustion(q389);
        Qutions_Full q390 = new Qutions_Full("Each MP3 file has a tag ………... extra information to be stored on other track details.",
                "allows",
                "to allow",
                "allowing",
                "allow ",
                3);
        addQustion(q390);
        Qutions_Full q391 = new Qutions_Full("A network is a number of computers ………. together.",
                "links",
                "to link  ",
                "linking ",
                "linked",
                4);
        addQustion(q391);
        Qutions_Full q392 = new Qutions_Full("Avoid …………….. financial information in a chat room",
                "gives",
                "to give",
                "giving",
                "gave",
                3);
        addQustion(q392);
        Qutions_Full q393 = new Qutions_Full(".……….. you listen to the first part , the next part is downloading",
                "As",
                "After",
                "Until",
                "Once",
                1);
        addQustion(q393);
        Qutions_Full q394 = new Qutions_Full("laser : ",
                "pane ",
                "Bar ",
                "Tray",
                "printer ",
                4);
        addQustion(q394);
        Qutions_Full q395 = new Qutions_Full("system : ",
                "pane ",
                "Bar ",
                "Tray",
                "printer ",
                3);
        addQustion(q395);
        Qutions_Full q396 = new Qutions_Full("Task : ",
                "pane ",
                "Bar ",
                "Tray",
                "printer ",
                2);
        addQustion(q396);
        Qutions_Full q397 = new Qutions_Full("Explorer : ",
                "program ",
                "Bar ",
                "Tray",
                "printer ",
                1);
        addQustion(q397);
        Qutions_Full q398 = new Qutions_Full("Office suite  : ",
                "a common type of compression used for video data ",
                "mathematical calculation based on the content of data",
                "capacity of a network connection",
                "set of standard  programs used in an office",
                4);
        addQustion(q398);
        Qutions_Full q399 = new Qutions_Full("Bandwidth : ",
                "a common type of compression used for video data ",
                "mathematical calculation based on the content of data",
                "capacity of a network connection",
                "set of standard  programs used in an office",
                3);
        addQustion(q399);
        Qutions_Full q400 = new Qutions_Full("MPEG : ",
                "a common type of compression used for video data ",
                "mathematical calculation based on the content of data",
                "capacity of a network connection",
                "set of standard  programs used in an office",
                1);
        addQustion(q400);
        Qutions_Full q401 = new Qutions_Full("checksum : ",
                "a common type of compression used for video data ",
                "mathematical calculation based on the content of data",
                "capacity of a network connection",
                "set of standard  programs used in an office",
                2);
        addQustion(q401);
        Qutions_Full q402 = new Qutions_Full("Internet address : ",
                "a advice for converting digital signals and vice-versa",
                "a hardware and software combination used to connect the same type of networks",
                "an email transfer process in which the receiving computer initiates the connection",
                "a 32 bit number identifying a node on an IP network",
                4);
        addQustion(q402);
        Qutions_Full q403 = new Qutions_Full("pull operation : ",
                "a advice for converting digital signals and vice-versa ",
                "a hardware and software combination used to connect the same type of networks",
                "an email transfer process in which the receiving computer initiates the connection",
                "a 32 bit number identifying a node on an IP network",
                3);
        addQustion(q403);
        Qutions_Full q404 = new Qutions_Full("A modem : ",
                "a advice for converting digital signals and vice-versa ",
                "a hardware and software combination used to connect the same type of networks",
                "an email transfer process in which the receiving computer initiates the connection",
                "a 32 bit number identifying a node on an IP network",
                1);
        addQustion(q404);
        Qutions_Full q405 = new Qutions_Full("A bridge : ",
                "a advice for converting digital signals and vice-versa ",
                "a hardware and software combination used to connect the same type of networks",
                "an email transfer process in which the receiving computer initiates the connection",
                "a 32 bit number identifying a node on an IP network",
                2);
        addQustion(q405);
        Qutions_Full q406 = new Qutions_Full("PIM : ",
                "Personal Information Manger ",
                "Processor In Memory ",
                "Print Image Matching",
                "Parallel Inference Machine",
                1);
        addQustion(q406);
        Qutions_Full q407 = new Qutions_Full("LAN : ",
                "Live Action Network ",
                "Local Available Names  ",
                "Local Access Network",
                "Learners Advisory Network",
                3);
        addQustion(q407);
        Qutions_Full q408 = new Qutions_Full("FTP : ",
                "Files To Peers",
                "File Transfer Process",
                "Fuel Transfer Point",
                "File Transfer Protocol",
                4);
        addQustion(q408);
        Qutions_Full q409 = new Qutions_Full("TCP : ",
                "Trusted Computer Platform",
                "Traffic Control",
                "Transmission Control Protocol",
                "Tool Center Point.",
                3);
        addQustion(q409);
        Qutions_Full q410 = new Qutions_Full("POP : ",
                "Point Of Presence",
                "Post Office Protocol",
                "Protocol Of Post Office",
                "Program Operation Plan",
                2);
        addQustion(q410);
        Qutions_Full q411 = new Qutions_Full(". biz : ",
                "Busy",
                "Business Site",
                "Business introduction /one ",
                "Business internet / one.",
                4);
        addQustion(q411);
        Qutions_Full q412 = new Qutions_Full(" A……….is an interface that enables dissimilar networks to communicate. : ",
                "gateway",
                "modem",
                "router",
                "bridge",
                1);
        addQustion(q412);
        Qutions_Full q413 = new Qutions_Full("  Doctors access a…………..that holds the records of all the patients in the practice : ",
                "database",
                "LAN  ",
                "CD  ",
                "Router ",
                1);
        addQustion(q413);
        Qutions_Full q414 = new Qutions_Full("  Logging on to your computer from a distance is an example of…………. : ",
                "UDP",
                "IRC",
                "Telnet  ",
                "FTP  ",
                3);
        addQustion(q414);
        Qutions_Full q415 = new Qutions_Full("  The ………. encodes and sends the packets . : ",
                "physical layer",
                "transport layer",
                "session layer",
                "presentation layer",
                1);
        addQustion(q415);
        Qutions_Full q416 = new Qutions_Full("…………… is a simple message transfer protocol used to send messages between servers. : ",
                "ISP  ",
                "IMAP",
                "SMTP  ",
                "POP",
                3);
        addQustion(q416);
        Qutions_Full q417 = new Qutions_Full("The checksum is calculated by the …………….. layer which also reassembles the message segments………... : ",
                "physical ",
                "transport",
                "application",
                "session",
                2);
        addQustion(q417);
        Qutions_Full q418 = new Qutions_Full("A/AN ………….. is a set of related webpages  : ",
                "data center",
                "website ",
                "office suite",
                "both A and C",
                2);
        addQustion(q418);
        Qutions_Full q419 = new Qutions_Full("The standard developed  for audio  compression is called…………  : ",
                "MP3 ",
                "Audio CD",
                "WAN",
                "MIDI",
                1);
        addQustion(q419);
        Qutions_Full q420 = new Qutions_Full("The program that converts songs from a CD to W A V files is called …………  : ",
                "ripper",
                "decoder ",
                "recorder  ",
                "both B and C",
                1);
        addQustion(q420);
        Qutions_Full q421 = new Qutions_Full("You can tell which folder is currently being displayed by the ………… icon   : ",
                "dragged  ",
                "deleted ",
                "displayed",
                "highlighted",
                4);
        addQustion(q421);
        Qutions_Full q422 = new Qutions_Full("Junk mail are good sources for …...   : ",
                "viruses",
                "attacks ",
                "services   ",
                "both A and B ",
                4);
        addQustion(q422);
        Qutions_Full q423 = new Qutions_Full("To narrow down your search on Google search using .   : ",
                "define  ",
                "language ",
                "categories ",
                "brackets",
                3);
        addQustion(q423);
        Qutions_Full q424 = new Qutions_Full("……….. is a way of dealing with bandwidth problem when you download a video from the net.",
                "Ripping  ",
                "Playback ",
                "Decompression",
                "Streaming",
                4);
        addQustion(q424);
        Qutions_Full q425 = new Qutions_Full("……….. you listen to the first part , the next part is downloading .",
                "AS",
                "After ",
                "Until ",
                "Once",
                1);
        addQustion(q425);
        Qutions_Full q426 = new Qutions_Full("you cannot save the file ……… you name it ..",
                "while",
                "after",
                "when",
                "until",
                4);
        addQustion(q426);
        Qutions_Full q427 = new Qutions_Full("Once you ………. On the hyperlink , you ……… for the webpage to be copied on your computer. ",
                "have clicked / wait",
                "clicked / waited",
                "will click / will wait",
                "are clicking / are waiting",
                1);
        addQustion(q427);
        Qutions_Full q428 = new Qutions_Full("If you spilled coffee on the keyboard , you ………….. it",
                "will damage",
                "would damage   ",
                "would have damaged ",
                "damaged",
                2);
        addQustion(q428);
        Qutions_Full q429 = new Qutions_Full("If you ……….print Screen , you can make a copy of the screen",
                "press ",
                "pressed   ",
                "will press ",
                "to press",
                1);
        addQustion(q429);
        Qutions_Full q430 = new Qutions_Full("First ,  enter  the search criteria by ……… on the personal tab",
                "click",
                "to click ",
                "clicking ",
                "clicks",
                3);
        addQustion(q430);
        Qutions_Full q431 = new Qutions_Full("Exercise in the morning rather than ….......",
                "smoke",
                "smoking  ",
                "to smoke ",
                "smokes",
                2);
        addQustion(q431);
        Qutions_Full q432 = new Qutions_Full("Each MP3 file has a tag ………. extra information to be stored on other track details.",
                "allows",
                "to allow  ",
                "allowing    ",
                " allow ",
                3);
        addQustion(q432);
        Qutions_Full q433 = new Qutions_Full(" Finally , click on find to ……………. The search",
                "starts",
                "to start   ",
                "starting ",
                " start",
                4);
        addQustion(q433);
        Qutions_Full q434 = new Qutions_Full(" A network is a number of computers ………. together.",
                "links",
                "to link",
                "linking",
                "linked",
                4);
        addQustion(q434);
        Qutions_Full q435 = new Qutions_Full(" A network is a number of computers ………. together.",
                "gives  ",
                "to give ",
                "giving",
                "gave",
                3);
        addQustion(q435);

    }


    private void addQustion(Qutions_Full qutions) {
        ContentValues cv = new ContentValues();
        cv.put(QuaizContract_Full.QuestionTabel.COLMUN_QUESTION, qutions.getQution());
        cv.put(QuaizContract_Full.QuestionTabel.COLMUN_OPTION1, qutions.getOption1());
        cv.put(QuaizContract_Full.QuestionTabel.COLMUN_OPTION2, qutions.getOption2());
        cv.put(QuaizContract_Full.QuestionTabel.COLMUN_OPTION3, qutions.getOption3());
        cv.put(QuaizContract_Full.QuestionTabel.COLMUN_OPTION4, qutions.getOption4());
        cv.put(QuaizContract_Full.QuestionTabel.COLMUN_ANSWER_NR, qutions.getAnswer());
        db.insert(QuaizContract_Full.QuestionTabel.Tabel_Name, null, cv);

    }

    public ArrayList<Qutions_Full> getAllQustion() {
        ArrayList<Qutions_Full> qutionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuaizContract_Full.QuestionTabel.Tabel_Name, null);
        if (c.moveToFirst()) {
            do {
                Qutions_Full qutions = new Qutions_Full();
                qutions.setQution(c.getString(c.getColumnIndex(QuaizContract_Full.QuestionTabel.COLMUN_QUESTION)));
                qutions.setOption1(c.getString(c.getColumnIndex(QuaizContract_Full.QuestionTabel.COLMUN_OPTION1)));
                qutions.setOption2(c.getString(c.getColumnIndex(QuaizContract_Full.QuestionTabel.COLMUN_OPTION2)));
                qutions.setOption3(c.getString(c.getColumnIndex(QuaizContract_Full.QuestionTabel.COLMUN_OPTION3)));
                qutions.setOption4(c.getString(c.getColumnIndex(QuaizContract_Full.QuestionTabel.COLMUN_OPTION4)));
                qutions.setAnswer(c.getInt(c.getColumnIndex(QuaizContract_Full.QuestionTabel.COLMUN_ANSWER_NR)));


                qutionsList.add(qutions);
            } while (c.moveToNext());

        }

        c.close();
        return qutionsList;
    }

}
