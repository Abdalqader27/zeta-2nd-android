package com.Elkood.ling_en4.EN4.Header_Elements.Eng3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

import androidx.annotation.Nullable;

class QuizDbHelper_Eng3 extends SQLiteOpenHelper {
    private static final String Data_Base_NAME = "MyAwsome.db";
    private static final int Version = 1;
    private SQLiteDatabase db;
    private Context Mycontext;

    public QuizDbHelper_Eng3(@Nullable Context context) {
        super(context, Data_Base_NAME, null, Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQl_CREAT_QUATION_TABEL = "CREATE TABLE " + QuaizContract_Eng3.QuestionTabel.Tabel_Name + " ( " +
                QuaizContract_Eng3.QuestionTabel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuaizContract_Eng3.QuestionTabel.COLMUN_QUESTION + " TEXT, " +
                QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION1 + " TEXT, " +
                QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION2 + " TEXT, " +
                QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION3 + " TEXT, " +
                QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION4 + " TEXT, " +
                QuaizContract_Eng3.QuestionTabel.COLMUN_ANSWER_NR + " INTEGER "
                + ")";

        db.execSQL(SQl_CREAT_QUATION_TABEL);
        fillQuestionTabel();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuaizContract_Eng3.QuestionTabel.Tabel_Name);
        onCreate(db);

    }

    private void fillQuestionTabel() {

        Qutions q1 = new Qutions("Data flows -------- the CPU and memory",
                "over",
                "between",
                "at",
                "in",
                2);
        addQustion(q1);
        Qutions q2 = new Qutions("The definition of  Telecommute is",
                "use the computer to stay in touch with the office while working at home ",
                "a combination of text with sound, graphic and video",
                "the amount of data transferred to the cache at any one time ",
                "a process of filtering through large amount of data for useful information ",
                1);
        addQustion(q2);
        Qutions q3 = new Qutions("The definition of Multimedia  is",
                "use the computer to stay in touch with the office while working at home ",
                "a combination of text with sound, graphic and video",
                "the amount of data transferred to the cache at any one time ",
                "a process of filtering through large amount of data for useful information ",
                2);
        addQustion(q3);
        Qutions q4 = new Qutions("The definition of  Line Size  is",
                "use the computer to stay in touch with the office while working at home ",
                "a combination of text with sound, graphic and video",
                "a process of filtering through large amount of data for useful information ",
                "the amount of data transferred to the cache at any one time  ",
                4);
        addQustion(q4);
        Qutions q5 = new Qutions("The definition of Data mining is ",
                "use the computer to stay in touch with the office while working at home ",
                "a combination of text with sound, graphic and video",
                "a process of filtering through large amount of data for useful information  ",
                "the amount of data transferred to the cache at any one time  ",
                3);
        addQustion(q5);
        Qutions q6 = new Qutions("The definition of Big Blue is",
                "a system that allows users to interact with a combination of inputs  ",
                "IBM",
                "one thousand gigabits  ",
                "software assistant that performs tasks such as automatic repetitive tasks   ",
                2);
        addQustion(q6);
        Qutions q7 = new Qutions("The definition of Terabit   is",
                "one thousand gigabits   ",
                "IBM",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                "a system that allows users to interact with a combination of inputs    ",
                1);
        addQustion(q7);
        Qutions q8 = new Qutions("The definition of  Intelligent agent is",
                "one thousand gigabits   ",
                "IBM",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                "a system that allows users to interact with a combination of inputs    ",
                3);
        addQustion(q8);
        Qutions q9 = new Qutions("The definition of Multimodal interface  is",
                "one thousand gigabits   ",
                "IBM",
                "software assistant that performs tasks such as automatic repetitive tasks  ",
                "a system that allows users to interact with a combination of inputs    ",
                4);
        addQustion(q9);
        Qutions q10 = new Qutions("The meaning of OS  is ",
                "On Service   ",
                "Operation System ",
                "On Sheet ",
                "Outer Space  ",
                2);
        addQustion(q10);
        Qutions q11 = new Qutions("The meaning of AFM  is  ",
                "Automatic Force Microscopy  ",
                "Audio Frequency | Modulation  ",
                "Adobe font Metrics  ",
                "Assistant Floor Manager   ",
                1);
        addQustion(q11);
        Qutions q12 = new Qutions("The meaning of TTS is   ",
                "Track Transit System  ",
                "Text To Speech   ",
                "Technology Transfer Setting ",
                "Trouble Ticket System   ",
                2);
        addQustion(q12);
        Qutions q13 = new Qutions("The meaning of  AI  is   ",
                "Abstucting and Indexing  ",
                "Assistant Instructor",
                "Artificial Intelligence",
                "Action Instructions    ",
                3);
        addQustion(q13);
        Qutions q14 = new Qutions("The meaning of EAN  is  ",
                " Expedia Affiliate Network  ",
                "Europe Access Network ",
                "Export Authorization Number",
                "European Article Number",
                4);
        addQustion(q14);
        Qutions q15 = new Qutions("…… enables computers and software that might otherwise be incompatible to communicate ",
                " Distance Learning  ",
                "connectivity  ",
                "Multimedia Systems ",
                "Edutainment",
                2);
        addQustion(q15);
        Qutions q16 = new Qutions("A video controller for controlling the screen and processing 3D images is a/an  ",
                " AGP  ",
                "CPU  ",
                "ROM ",
                "RAM",
                1);
        addQustion(q16);
        Qutions q17 = new Qutions("The main processing chip that is designed manufactured by Intel is  ",
                " AGP  ",
                "CPU  ",
                "ROM ",
                "RAM",
                2);
        addQustion(q17);
        Qutions q18 = new Qutions("A speed trap contains a radar set, a ------ and a camera with a flash ",
                " microscope  ",
                "microwave   ",
                "microprocessor  ",
                "micro sensor",
                3);
        addQustion(q18);
        Qutions q19 = new Qutions("The hardware needs ------ to make it run ",
                " hard disks  ",
                "RAM    ",
                "CDs  ",
                "software ",
                4);
        addQustion(q19);
        Qutions q20 = new Qutions("The ------ program remains in the memory and controls the entire system ",
                " supervisor  ",
                "non-resident    ",
                "driver   ",
                "executable  ",
                1);
        addQustion(q20);
        Qutions q21 = new Qutions("A type of software development where any programmer can develop or fix bugs in the software is  ",
                " kernel   ",
                "source code  ",
                "open source    ",
                "free software  ",
                3);
        addQustion(q21);
        Qutions q22 = new Qutions("A/An ----- is a complete OS kit with all the utilities and applications you need to make it do useful things   ",
                " distribution    ",
                "X system  ",
                "kernel   ",
                "free software  ",
                1);
        addQustion(q22);
        Qutions q23 = new Qutions("when the content of a folder does not fit in the window, click the -------   ",
                " icon    ",
                "status bar ",
                "menu bar    ",
                "scrollbar  ",
                4);
        addQustion(q23);
        Qutions q24 = new Qutions("When the processor is successful in finding the data in the cache, this is ----  ",
                " a line size     ",
                "a cache controller ",
                "a cache hit    ",
                "cache coherency  ",
                3);
        addQustion(q24);
        Qutions q25 = new Qutions("….. is data free from duplicate and erroneous information   ",
                " Cleansed data ",
                "Raw data ",
                "A flat file     ",
                "a data warehouse ",
                1);
        addQustion(q25);
        Qutions q26 = new Qutions("The ----- converts barcode into electric pulses  ",
                " display  ",
                "scanner  ",
                "branch computer      ",
                "till",
                2);
        addQustion(q26);
        Qutions q27 = new Qutions("The main starting point for most of your actions is ",
                " start button   ",
                "status box  ",
                "my computer      ",
                "recycle Bin",
                1);
        addQustion(q27);
        Qutions q28 = new Qutions("Data flows -------- the CPU and memory",
                " over   ",
                "between ",
                "in  ",
                "at",
                2);
        addQustion(q28);
        Qutions q29 = new Qutions("….. You ever ----- a video email attachment ",
                " Did / send   ",
                "Had / been sending  ",
                "Have / sent   ",
                "Do / send ",
                3);
        addQustion(q29);
        Qutions q30 = new Qutions("When ----- you ----- the video email ? ",
                " had / been sending  ",
                "do / send ",
                "have / sent  ",
                "did / send ",
                4);
        addQustion(q30);
        Qutions q31 = new Qutions("When ----- you ----- the video email ? ",
                " had / been sending  ",
                "do / send ",
                "have / sent  ",
                "did / send ",
                4);
        addQustion(q31);
        Qutions q32 = new Qutions("I ----- my paintings into themes yet ",
                " haven't organized ",
                "didn't organized",
                "have organized ",
                "have been organized ",
                1);
        addQustion(q32);
        Qutions q33 = new Qutions("The mouse is used for ------ the cursor ",
                " to control ",
                "controlling",
                "controlled  ",
                "control ",
                2);
        addQustion(q33);
        Qutions q34 = new Qutions("The function of the processor is ---- all the operations in the computer ",
                " to control ",
                "controlling",
                "controlled  ",
                "control ",
                1);
        addQustion(q34);
        Qutions q35 = new Qutions("The hard disk is ------ a sealed case   ",
                " outside ",
                "across",
                "inside   ",
                "between ",
                3);
        addQustion(q35);
        Qutions q36 = new Qutions("A letter to the owner of the vehicle ----- using mailmerge   ",
                " prints  ",
                "will print ",
                "is print    ",
                "is printed  ",
                4);
        addQustion(q36);
        Qutions q37 = new Qutions("With digital cameras you can delete unsatisfactory images ---- with conventional ones you cannot ",
                " ; however   ",
                ";however , ",
                "however     ",
                ",however   ",
                2);
        addQustion(q37);
        Qutions q38 = new Qutions("You can delete files from hard disks whereas you ------ from CDs ",
                " can   ",
                "will  ",
                "cannot ",
                "do   ",
                3);
        addQustion(q38);
        Qutions q39 = new Qutions("To avoid losing data, you should ------ your files ",
                " free up   ",
                "build up ",
                "set up ",
                "pack up  ",
                4);
        addQustion(q39);
        Qutions q40 = new Qutions("He ------ a website to advertise  for his travel company  ",
                " upgrades ",
                "updates , ",
                "set up ",
                "uploads ",
                3);
        addQustion(q40);
        Qutions q41 = new Qutions("The information ------ to the second unit  ",
                " is relayed  ",
                "relay  ",
                "relays",
                "relayed ",
                1);
        addQustion(q41);
        Qutions q42 = new Qutions("Data flows -------- the CPU and memory ",
                " between",
                "over ",
                "in",
                "at ",
                1);
        addQustion(q42);
        Qutions q43 = new Qutions("A speed trap contains a radar set ,------- and acamera with a flash. ",
                " microwave",
                "microprocessor",
                "microsensor",
                "microscope ",
                2);
        addQustion(q43);
        Qutions q44 = new Qutions("Digital cameras store pictures on ---------.",
                " hard disks",
                "floppy disks  ",
                "memory cards",
                "CDs ",
                3);
        addQustion(q44);
        Qutions q45 = new Qutions("The hardware needs ------- to make it run..",
                " RAM",
                "hard disks ",
                "CDs",
                "software ",
                4);
        addQustion(q45);
        Qutions q46 = new Qutions("The ------ program remains in the memory and controls the entire system.",
                " supervisor",
                "resident ",
                "non-resident",
                "software ",
                1);
        addQustion(q46);
        Qutions q47 = new Qutions("When the content of a folder does not fit in the window,click the -----.",
                " menu bar",
                "scrollbar ",
                "status bar",
                "icon ",
                2);
        addQustion(q47);
        Qutions q48 = new Qutions(" ------ is data free from duplicate and erroneous information . ",
                " Raw data",
                "Aflat file ",
                "cleansed data",
                "Adata warehouse ",
                3);
        addQustion(q48);
        Qutions q49 = new Qutions(" ----- is materials with a combination of educational and entertainment content.",
                " Vidoconferencing",
                "Multimedia",
                "Entertainment",
                "Edutainment ",
                4);
        addQustion(q49);
        Qutions q50 = new Qutions("A small tall,narrow style of case containing the computer system is a -----.",
                " chassis",
                "monitor",
                "compact disk",
                "video card ",
                1);
        addQustion(q50);
        Qutions q51 = new Qutions("The process of successfully finding data in the cach is a -----.",
                " Write-back cache",
                "cache hit",
                "cache controller",
                "write though cache ",
                2);
        addQustion(q51);
        Qutions q52 = new Qutions("The mouse is used for ------ the cursor.",
                " control",
                "to control",
                "controlling",
                "controlled ",
                3);
        addQustion(q52);
        Qutions q53 = new Qutions("The data capacity of astorage device measured in bits per square inch is-----.",
                " AFM",
                "Moore's Law",
                "holographic",
                "areal density ",
                4);
        addQustion(q53);
        Qutions q54 = new Qutions(" ------ is a phenomenon that threatens to make densely packed bits unstable.",
                " Super paramaganetism",
                "Super centenarian",
                "Super cluster",
                "Super conductivity ",
                1);
        addQustion(q54);
        Qutions q55 = new Qutions("  ------- you ever ----- a video email attachment?",
                "Did/send",
                "Have/sent",
                "Do/send",
                "Had/been sending ",
                2);
        addQustion(q55);
        Qutions q56 = new Qutions("When ------- you ------- the video email?",
                "Did/send",
                "Have/sent",
                "Do/send",
                "Had/been sending ",
                1);
        addQustion(q56);
        Qutions q57= new Qutions("The function of the processor is ------ all the operations in the computer.",
                "control",
                "to control",
                "controlling",
                "controlled ",
                2);
        addQustion(q57);
        Qutions q58= new Qutions("The hard disk is ------- a sealed case.",
                "across",
                "inside",
                "outside",
                "between ",
                2);
        addQustion(q58);
        Qutions q59= new Qutions("A letter to the owner of the vehicle ------- using mailmerge.",
                "is print",
                "will print ",
                "is printed",
                "prints ",
                3);
        addQustion(q59);
        Qutions q60= new Qutions("With digital cameras you can delete unsatisfactory images ------ with conventional ones you cannot",
                ",however",
                ";however",
                ";however,",
                "however ",
                3);
        addQustion(q60);
        Qutions q61= new Qutions("you can delet files from hard disks whereas you ----- from CDs.",
                "cannot",
                "do",
                "can",
                "will ",
                1);
        addQustion(q61);
        Qutions q62= new Qutions("To avoid losing data, you should ----- your files.",
                "free up",
                "back up",
                "build up",
                "set up ",
                2);
        addQustion(q62);
        Qutions q63= new Qutions("The definition of Telecommute",
                "Use computer to stay in touch with the office while working at home",
                "Ensuring changes are written to bath the cache and main memory and vice-versa",
                "A software that enables computer to think like experts",
                "The amount of datd transferred to the cache at any time  ",
                1);
        addQustion(q63);
        Qutions q64= new Qutions("The definition of Expert System",
                "Ensuring changes are written to bath the cache and main memory and vice-versa",
                "A software that enables computer to think like experts",
                "The amount of datd transferred to the cache at any time ",
                "Use computer to stay in touch with the office while working at home ",
                2);
        addQustion(q64);
        Qutions q65= new Qutions("He ------- a website to advertist for his travel company.",
                "uploads",
                "sets up",
                "upgrades",
                "updates",
                2);
        addQustion(q65);
        Qutions q66= new Qutions("The meaning  of TTS",
                "Text ToSpeech",
                "Tarck Transit System",
                "Technology Transfer Setting",
                "Trouble Ticket System",
                1);
        addQustion(q66);
        Qutions q67= new Qutions("The meaning  of IBM",
                "Industrial Business Machine",
                "Incredibly Bad Machine",
                "Inteligent Business Machines",
                "International Business Machines",
                4);
        addQustion(q67);
        Qutions q68= new Qutions("The meaning  of CCD",
                "Cell Copying Database",
                "Common Core Data",
                "Control Circuit Diagram",
                "Charged-coupled Device",
                4);
        addQustion(q68);
        Qutions q69= new Qutions("The meaning  of AI",
                "Aritificial Intelligence",
                "Action Instructions",
                "Abstracting and Indexing",
                "Assistant Instructor",
                1);
        addQustion(q69);
        Qutions q70=new Qutions("The meaning  of EAN",
                "ExpediaAffiliate Network",
                "Europe Access Network",
                "European Articale Number",
                "Export Authorization Number",
                3);
        addQustion(q70);
        Qutions q71=new Qutions("The meaning  of ATM",
                "Automated Teller Machine",
                "Automated Telephone Manager",
                "Air Traffic Management",
                "Asynchronous Transfer Mode",
                1);
        addQustion(q71);
        Qutions q72=new Qutions("The meaning  of DDR",
                "Dynamic Document Review",
                "Digital Disk Recorder",
                "Drag, Drop, and Replace",
                "Double Data Rate ",
                4);
        addQustion(q72);
        Qutions q73=new Qutions("The definition of Broadband",
                "Software assistant that performs tasks repetitively",
                "High capacity Internet connection",
                "Capacity of a network connection",
                "A system that allows user to interact with the computer using a combination of inputs",
                2);
        addQustion(q73);
        Qutions q74=new Qutions("The definition of Bandwidth",
                "Software assistant that performs tasks repetitively",
                "High capacity Internet connection",
                "Capacity of a network connection",
                "A system that allows user to interact with the computer using a combination of inputs",
                3);
        addQustion(q74);
        Qutions q75=new Qutions("The definition of MUltiModel interface",
                "Software assistant that performs tasks repetitively",
                "High capacity Internet connection",
                "Capacity of a network connection",
                "A system that allows user to interact with the computer using a combination of inputs",
                4);
        addQustion(q75);
        Qutions q76=new Qutions("The definition of Intelligent Agent",
                "SA system that allows user to interact with the computer using a combination of inputs",
                "High capacity Internet connection",
                "Capacity of a network connection",
                "Software assistant that performs tasks repetitively",
                4);
        addQustion(q76);
        Qutions q77=new Qutions("The definition of M y Computer",
                "The core of the operating system that makes sure everything is running.",
                "A complete operating system with kit and utilities.",
                "Let you browse the files stored on your PC.",
                "The approximate radius of an atom.",
                3);
        addQustion(q77);
        Qutions q78=new Qutions("The definition of Kernel",
                "Let you browse the files stored on your Pc",
                "A complete operating system with kit and utilities.",
                "The core of the operating system that makes sure everything is running.",
                "The approximate radius of an atom.",
                3);
        addQustion(q78);
        Qutions q79=new Qutions("The definition of A distribution",
                "Let you browse the files stored on your Pc",
                "A complete operating system with kit and utilities.",
                "Let you browse the files stored on your PC.",
                "The approximate radius of an atom.",
                2);
        addQustion(q79);
        Qutions q80=new Qutions("The definition of Angstrom",
                "The approximate radius of an atom.",
                "The core of the operating system that makes sure everything is running.",
                "A complete operating system with kit and utilities..",
                "Let you browse the files stored on your PC.",
                1);
        addQustion(q80);
        Qutions q81=new Qutions("The definition of Line Size",
                "Use computer to stay in touch with the office while working at home",
                "A software that enables computer to think like experts",
                "Ensuring changes are written to bath the cache and main memory and vice-versa.",
                "The amount of datd transferred to the cache at any time ",
                4);
        addQustion(q81);
        Qutions q82=new Qutions("The definition of Cache Coherency",
                "Use computer to stay in touch with the office while working at home",
                "A software that enables computer to think like experts",
                "Ensuring changes are written to bath the cache and main memory and vice-versa.",
                "The amount of datd transferred to the cache at any time ",
                3);
        addQustion(q82);
        Qutions q83= new Qutions("The definition of Expert System",
                "Ensuring changes are written to bath the cache and main memory and vice-versa",
                "A software that enables computer to think like experts",
                "The amount of datd transferred to the cache at any time ",
                "Use computer to stay in touch with the office while working at home ",
                2);
        addQustion(q83);
        Qutions q84= new Qutions("He ------- a website to advertist for his travel company.",
                "uploads",
                "sets up",
                "upgrades",
                "updates",
                2);
        addQustion(q84);
        Qutions q85= new Qutions("To avoid losing data, you should ----- your files.",
                "free up",
                "back up",
                "build up",
                "set up ",
                2);
        addQustion(q85);
        Qutions q86 = new Qutions("You can delete files from hard disks whereas you ------ from CDs ",
                " can   ",
                "will  ",
                "cannot ",
                "do   ",
                3);
        addQustion(q86);
        Qutions q87 = new Qutions("A video controller for controlling the screen and processing 3D images is a/an  ",
                " AGP  ",
                "CPU  ",
                "ROM ",
                "RAM",
                1);
        addQustion(q87);
        Qutions q88 = new Qutions("…… enables computers and software that might otherwise be incompatible to communicate ",
                " Distance Learning  ",
                "connectivity  ",
                "Multimedia Systems ",
                "Edutainment",
                2);
        addQustion(q88);
        Qutions q89= new Qutions("With digital cameras you can delete unsatisfactory images ------ with conventional ones you cannot",
                ",however",
                ";however",
                ";however,",
                "however ",
                3);
        addQustion(q89);
        Qutions q90= new Qutions("A letter to the owner of the vehicle ------- using mailmerge.",
                "is print",
                "will print ",
                "is printed",
                "prints ",
                3);
        addQustion(q90);
        Qutions q91= new Qutions("The hard disk is ------- a sealed case.",
                "across",
                "inside",
                "outside",
                "between ",
                2);
        addQustion(q91);
        Qutions q92= new Qutions("The function of the processor is ------ all the operations in the computer.",
                "control",
                "to control",
                "controlling",
                "controlled ",
                2);
        addQustion(q92);
        Qutions q93 = new Qutions("The mouse is used for ------ the cursor.",
                " control",
                "to control",
                "controlling",
                "controlled ",
                3);
        addQustion(q93);
        Qutions q94 = new Qutions("When ------- you ------- the video email?",
                "Did/send",
                "Have/sent",
                "Do/send",
                "Had/been sending ",
                1);
        addQustion(q94);
        Qutions q95 = new Qutions("  ------- you ever ----- a video email attachment?",
                "Did/send",
                "Have/sent",
                "Do/send",
                "Had/been sending ",
                2);
        addQustion(q95);
        Qutions q96 = new Qutions(" ------ is a phenomenon that threatens to make densely packed bits unstable.",
                " Super paramaganetism",
                "Super centenarian",
                "Super cluster",
                "Super conductivity ",
                1);
        addQustion(q96);
        Qutions q97 = new Qutions("A speed trap contains a radar set ,------- and acamera with a flash. ",
                " microwave",
                "microprocessor ",
                "microsensor",
                "microscope ",
                2);
        addQustion(q97);
        Qutions q98 = new Qutions("When the processor is successful in finding the data in the cache, this is ----  ",
                " a line size     ",
                "a cache controller ",
                "a cache hit    ",
                "cache coherency  ",
                3);
        addQustion(q98);
        Qutions q99 = new Qutions("The hardware needs ------- to make it run..",
                " RAM",
                "hard disks  ",
                "CDs",
                "software ",
                4);
        addQustion(q99);
        Qutions q100 = new Qutions("The ------ program remains in the memory and controls the entire system ",
                " supervisor  ",
                "non-resident    ",
                "driver   ",
                "executable  ",
                1);
        addQustion(q100);
        Qutions q101 = new Qutions("When the content of a folder does not fit in the window,click the -----.",
                " menu bar",
                "scrollbar ",
                "status bar",
                "icon ",
                2);
        addQustion(q101);
        Qutions q102 = new Qutions("A/An ----- is a complete OS kit with all the utilities and applications you need to make it do useful things   ",
                " distribution    ",
                "X system  ",
                "kernel   ",
                "free software  ",
                1);
        addQustion(q102);
        Qutions q103 = new Qutions("….. is data free from duplicate and erroneous information   ",
                " Cleansed data ",
                "Raw data ",
                "A flat file     ",
                "a data warehouse ",
                1);
        addQustion(q103);
        Qutions q104 = new Qutions(" ----- is materials with a combination of educational and entertainment content.",
                " Vidoconferencing",
                "Multimedia",
                "Entertainment",
                "Edutainment ",
                4);
        addQustion(q104);
        Qutions q105 = new Qutions("The meaning of TTS is   ",
                "Track Transit System  ",
                "Text To Speech   ",
                "Technology Transfer Setting ",
                "Trouble Ticket System   ",
                2);
        addQustion(q105);
        Qutions q106 = new Qutions("The meaning of OS  is ",
                "On Service   ",
                "Operation System ",
                "On Sheet ",
                "Outer Space  ",
                2);
        addQustion(q106);
        Qutions q107 = new Qutions("When the processor is successful in finding the data in the cache, this is ----  ",
                " a line size     ",
                "a cache controller ",
                "a cache hit    ",
                "cache coherency  ",
                3);
        addQustion(q107);
        Qutions q108 = new Qutions("Speech recognition is likely to completely replace other input device  ",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q108);
        Qutions q109 = new Qutions("keyboards and mice will soon not be required for using PCs",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q109);
        Qutions q110 = new Qutions("The Internet Connection Wizard is a special program that helps you get on the internet ",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q110);
        Qutions q111 = new Qutions("The taskbar shows you the programs that you are currently running",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q111);
        Qutions q112 = new Qutions("Minix is based on Unix",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q112);
        Qutions q113 = new Qutions("Minix was created by a university student  ",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q113);
        Qutions q114 = new Qutions("The development of AFM is more advanced than holographic storage  ",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q114);
        Qutions q115 = new Qutions("Holography works in 3D",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q115);
        Qutions q116 = new Qutions("Data mining is used to detect false insurance claims",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q116);
        Qutions q117 = new Qutions("The processor looks for data in the main memory first ",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q117);
        Qutions q118 = new Qutions("Desktop organizer are programs that require desktop computers ",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q118);
        Qutions q119 = new Qutions("The data capacity of a storage device measured in bits per square inch is ------ ",
                "holografic storage ",
                "Areal density  ",
                "Moore's law",
                "AFM",
                2);
        addQustion(q119);
        Qutions q120 = new Qutions("A small tall ,narrow style of case containing the computer system is a  ",
                "compact disk ",
                "videp card",
                "chassis",
                "monitor",
                3);
        addQustion(q120);
        Qutions q121 = new Qutions("A small tall ,narrow style of case containing the computer system is a  ",
                "compact disk ",
                "videp card",
                "chassis",
                "monitor",
                3);
        addQustion(q121);
        Qutions q122 = new Qutions("Electronic classrooms are now accessible to people in remote locations. ",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q122);
        Qutions q123 = new Qutions("Software and hardware are the only critical elements to ensure efficient use of computers.. ",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q123);
        Qutions q124 = new Qutions("The main chip designed by intel corporation is called pentium 4.. ",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q124);
        Qutions q125 = new Qutions("Video card is an electronics for driving the sound input.. ",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q125);
        Qutions q126 = new Qutions("Cach memory is faster than RAM . ",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q126);
        Qutions q127 = new Qutions("How adisk cache works is different from the CPU. ",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q127);
        Qutions q128 = new Qutions("Artificial Intelligence is commonly used in data mining.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q128);
        Qutions q129 = new Qutions("Data mining is used to detect false insurance claims.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q129);
        Qutions q130 = new Qutions("Consumer demand is lagging behind what technology can deliver.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q130);
        Qutions q131 = new Qutions("When a computer comes off the production line , it can do everything.",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q131);
        Qutions q132 = new Qutions("Speech recognition is likely to completely replace other input devices.",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q132);
        Qutions q133 = new Qutions("The definition of Information Superhighway is",
                "Software that enables computers to think like experts",
                "internet system that provides free interactive access to fast recourses to people all over the world ",
                "the process of writing changes only to the cache and not the main memory",
                "the process of writing directly to both the cache and main memory at the same time ",
                2);
        addQustion(q133);
        Qutions q134 = new Qutions("The meaning of FSB  is",
                "Finite State Buffer",
                "Free Software Business",
                "Front Side Bus",
                "File Selection Box",
                3);
        addQustion(q134);
        Qutions q135 = new Qutions("The meaning of LCD  is ",
                "Local Change Directory ",
                "Liqiud Crystal Display ",
                "Low Cost Display",
                "Least Common Denominator",
                2);
        addQustion(q135);
        Qutions q136 = new Qutions("The meaning of ROM is ",
                "Read Only Memory ",
                "Range Of Motion ",
                "Read Only Meaning ",
                "Random Order Memory",
                1);
        addQustion(q136);
        Qutions q137 = new Qutions("The meaning of PDA  is  ",
                "Personal Digital Assistant  ",
                "Primery Digital Asset ",
                "Public display of Accessories",
                "Protocol Decode Architecture",
                1);
        addQustion(q137);
        Qutions q138 = new Qutions("The meaning of EAN  is ",
                "Export Authorization Number ",
                "European Article Number ",
                "Expedia Affiliate Network",
                "Europe Access Network ",
                2);
        addQustion(q138);
        Qutions q139 = new Qutions("The definition of Information Superhighway is",
                "Software that enables computers to think like experts",
                "internet system that provides free interactive access to fast recourses to people all over the world ",
                "the process of writing changes only to the cache and not the main memory",
                "the process of writing directly to both the cache and main memory at the same time ",
                2);
        addQustion(q139);
        Qutions q140 = new Qutions("The meaning of FSB  is",
                "Finite State Buffer",
                "Free Software Business",
                "Front Side Bus",
                "File Selection Box",
                3);
        addQustion(q140);
        Qutions q141 = new Qutions("The meaning of LCD  is ",
                "Local Change Directory ",
                "Liqiud Crystal Display ",
                "Low Cost Display",
                "Least Common Denominator",
                2);
        addQustion(q141);
        Qutions q142 = new Qutions("The meaning of ROM is ",
                "Read Only Memory ",
                "Range Of Motion ",
                "Read Only Meaning ",
                "Random Order Memory",
                1);
        addQustion(q142);
        Qutions q143 = new Qutions("The meaning of PDA  is  ",
                "Personal Digital Assistant  ",
                "Primery Digital Asset ",
                "Public display of Accessories",
                "Protocol Decode Architecture",
                1);
        addQustion(q143);
        Qutions q144 = new Qutions("The meaning of EAN  is ",
                "Export Authorization Number ",
                "European Article Number ",
                "Expedia Affiliate Network",
                "Europe Access Network ",
                2);
        addQustion(q144);
        Qutions q145 = new Qutions("The meaning of  AI is  ",
                "Action Instructions ",
                "Artificial Intelligence",
                "Abstucting and Indexing ",
                "Assistant Instructor",
                2);
        addQustion(q145);
        Qutions q146 = new Qutions("The meaning of  AFM is  ",
                "Adobe font Metrics ",
                "Assistant Floor Manager ",
                "Audio Frequency |Modulation  ",
                "Automatic Force Microscopy ",
                4);
        addQustion(q146);
        Qutions q147 = new Qutions("The definition of Folder is",
                "an icon like a 3D view of a suspension file",
                "the original system program from which compiled programs are generated ",
                "a prediction that the number of transistors in a processor chip will double every 18 month",
                "a storage method for archiving large amount of data",
                1);
        addQustion(q147);
        Qutions q148 = new Qutions("The definition of The definition of Source code is",
                "a storage method for archiving large amount of data",
                "the original system program from which compiled programs are generated",
                "a prediction that the number of transistors in a processor chip will double every 18 month",
                "an icon like a 3D view of a suspension file",
                2);
        addQustion(q148);
        Qutions q149 = new Qutions("The definition of Moore's  law is",
                "a storage method for archiving large amount of data",
                "the original system program from which compiled programs are generated",
                "a prediction that the number of transistors in a processor chip will double every 18 month",
                "an icon like a 3D view of a suspension file",
                3);
        addQustion(q149);
        Qutions q150 = new Qutions("The definition of Moore's  law is",
                "a storage method for archiving large amount of data",
                "the original system program from which compiled programs are generated",
                "a prediction that the number of transistors in a processor chip will double every 18 month",
                "an icon like a 3D view of a suspension file",
                3);
        addQustion(q150);
        Qutions q151 = new Qutions("Fewer people are using computers because their functions are integrated in electronic devices.",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q151);
        Qutions q152 = new Qutions("There have been no improvements' in interface design.",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q152);
        Qutions q153 = new Qutions("Keyboards and mice will soon not be required for using PCs..",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q153);
        Qutions q154 = new Qutions("The status/time box displays the current time and other information.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q154);
        Qutions q155 = new Qutions("The taskbar shows you the programs that you are currently running.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q155);
        Qutions q156 = new Qutions("Minix is based on Unix.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q156);
        Qutions q157 = new Qutions("Linux runs on all types of machines more than any other operation system.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q157);
        Qutions q158 = new Qutions("The predicted maximum storage density of AFM is 100 gigabits per square inch..",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q158);
        Qutions q159 = new Qutions("The predicted maximum storage density of AFM is 100 gigabits per square inch..",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q159);
        Qutions q160 = new Qutions("Digital cameras store images on hard disks..",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q160);
        Qutions q161 = new Qutions("Artificial intelligence is commonly used in data mining.",
                "TRUE",
                "FALSE ",
                "",
                "",
                1);
        addQustion(q161);
        Qutions q162 = new Qutions("Data mining is only used for a limited range of problems.",
                "TRUE",
                "FALSE ",
                "",
                "",
                2);
        addQustion(q162);
        Qutions q163 = new Qutions("The definition of Write- back cache is",
                "internet system that provides free interactive access to fast recourses to people all over the world",
                "the process of writing changes only to the cache and not the main memory ",
                "the process of writing directly to both the cache and main memory at the same time ",
                "Software that enables computers to think like experts",
                2);
        addQustion(q163);
        Qutions q164 = new Qutions("The definition of Write- through cache is",
                "internet system that provides free interactive access to fast recourses to people all over the world",
                "the process of writing changes only to the cache and not the main memory ",
                "the process of writing directly to both the cache and main memory at the same time ",
                "Software that enables computers to think like experts",
                3);
        addQustion(q164);
        Qutions q165 = new Qutions("The definition of Data warehouse is",
                "an icon like a 3D view of a suspension file",
                "the original system program from which compiled programs are generated ",
                "a storage method for archiving large amount of data ",
                "a prediction that the number of transistors in a processor chip will double every 18 month",
                3);
        addQustion(q165);


    }


    private void addQustion(Qutions qutions) {
        ContentValues cv = new ContentValues();
        cv.put(QuaizContract_Eng3.QuestionTabel.COLMUN_QUESTION, qutions.getQution());
        cv.put(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION1, qutions.getOption1());
        cv.put(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION2, qutions.getOption2());
        cv.put(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION3, qutions.getOption3());
        cv.put(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION4, qutions.getOption4());
        cv.put(QuaizContract_Eng3.QuestionTabel.COLMUN_ANSWER_NR, qutions.getAnswer());
        db.insert(QuaizContract_Eng3.QuestionTabel.Tabel_Name, null, cv);

    }

    public ArrayList<Qutions> getAllQustion() {
        ArrayList<Qutions> qutionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuaizContract_Eng3.QuestionTabel.Tabel_Name, null);
        if (c.moveToFirst()) {
            do {
                Qutions qutions = new Qutions();
                qutions.setQution(c.getString(c.getColumnIndex(QuaizContract_Eng3.QuestionTabel.COLMUN_QUESTION)));
                qutions.setOption1(c.getString(c.getColumnIndex(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION1)));
                qutions.setOption2(c.getString(c.getColumnIndex(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION2)));
                qutions.setOption3(c.getString(c.getColumnIndex(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION3)));
                qutions.setOption4(c.getString(c.getColumnIndex(QuaizContract_Eng3.QuestionTabel.COLMUN_OPTION4)));
                qutions.setAnswer(c.getInt(c.getColumnIndex(QuaizContract_Eng3.QuestionTabel.COLMUN_ANSWER_NR)));


                qutionsList.add(qutions);
            } while (c.moveToNext());

        }

        c.close();
        return qutionsList;
    }

}
