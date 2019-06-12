package com.Elkood.ling_en4.Repeatrer_Quiz;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class R2016_3 extends AppCompatActivity {
    private LinearLayout progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2016_3);
        recyclerView = findViewById(R.id.recycle_view2016_3);
        progressBar = findViewById(R.id.pro2016_3);
        ArrayList<item_quiz> list = new ArrayList<>();
        list.add(new item_quiz("1.The main idea of the text is-------.…….",
                0, 0, 0, 0,
                "A. development of modern music",
                "B. musical instruments",
                "C.development of MID interface",
                "D. new storage technologies"));

        list.add(new item_quiz("2. In line 1, the abbreviation “MID” (1) stands for.…. ",
                0, 1, 0, 0,
                "A.Musial Input Device Interface",
                "B. Musical Instrument Digital Interface",
                "C. Media Instrument Digital Interface ",
                "D. Measurement of Information and Data Interface"));


        list.add(new item_quiz("3.In line 2 , the word “linking” (2) can be replaced by …. ",
                1, 0, 0, 0,
                "A. connecting ",
                "B. correcting ",
                "C. conducting  ",
                "D. contracting"));


        list.add(new item_quiz("4. .In line 2 , the word “fitted” (3) can be replaced by ….",
                1, 0, 0, 0,
                "A. equipped ",
                "B.rebuilt   ",
                "C.mixed ",
                "D.edited"));

        list.add(new item_quiz(" 5.we can infer from the text that …. ",
                0, 0, 0, 1,
                "A. . computers and musical instruments cannot be connected",
                "B. computers cannot store music",
                "C. desk works in a different way ",
                "D. computers can store and edit music like mixing desk"));
        list.add(new item_quiz("6. According to the text, MID message contain .",
                0, 0, 1, 0,
                "A. sound   ",
                "B. text ",
                "C. data  ",
                "D. diagrams"));
        list.add(new item_quiz("7. In line 5 , the pronoun “it” (4) refers to-----------. ",
                1, 0, 0, 0,
                "A.music",
                "B.a computer ",
                " C.an interface",
                "D.an instrument"));
        list.add(new item_quiz("8. In line 5 , the word ”binary” (5) means-- …. .",
                1, 0, 0, 0,
                "A. tow numbers",
                "B.  tow elements",
                "C. tow parts",
                "D.tow items"));
        list.add(new item_quiz("9.we can understand from the text that MIDI messages are-- …. ",
                0, 0, 0, 1,
                "A.various  ",
                "B.similar   ",
                "C. less common ",
                "D. complex"));
        list.add(new item_quiz("10.According to the text , MIDI message can tell the receiving instrument----- ",
                0, 0, 0, 1,
                "A.why to play the note ",
                "B.who played the music",
                "C.the kind of music to play ",
                "D.how long to play the note"));

//arrived
        list.add(new item_quiz("11. laser : ",
                0, 0, 0, 1,
                "  A.pane ",
                "B. Bar ",
                "C. Tray",
                " D.printer "));
        list.add(new item_quiz("12) system: ",
                0, 0, 1, 0,
                "A.pane",
                "B. Bar",
                "C. Tray",
                "D.printer "));
        list.add(new item_quiz("13) Task :  ",
                0, 1, 0, 0,
                "A.pane",
                "B. Bar",
                "C. Tray",
                "D. printer "));
        list.add(new item_quiz("14) Explorer :  ",
                1, 0, 0, 0,
                "A. program",
                "B.  Bar",
                "C. Tray",
                "D. printer "));
        list.add(new item_quiz("15) Office suite ",
                0, 0, 0, 1,
                "A.a common type of compression used for video data ",
                "B.mathematical calculation based on the content of data",
                " C. capacity of a network connection",
                "D.set of standard  programs used in an office"));
        list.add(new item_quiz("16) Bandwidth",
                0, 0, 1, 0,
                "A.a common type of compression used for video data ",
                "B.mathematical calculation based on the content of data",
                "C. capacity of a network connection ",
                "D.set of standard  programs used in an office"));
        list.add(new item_quiz("17)MPEG",
                1, 0, 0, 0,
                "A.a common type of compression used for video data  ",
                "B.mathematical calculation based on the content of data",
                "C. capacity of a network connection ",
                "D.set of standard  programs used in an office"));
        list.add(new item_quiz("18) checksum",
                0, 1, 0, 0,
                "A.a common type of compression used for video data ",
                "B.mathematical calculation based on the content of data",
                "C. capacity of a network connection ",
                "D.set of standard  programs used in an office"));
        list.add(new item_quiz("19)Internet address",
                0, 0, 0, 1,
                "A.a advice for converting digital signals and vice-versa",
                "B.a hardware and software combination used to connect the same type of networks",
                "C.an email transfer process in which the receiving computer initiates the connection",
                "D.a 32 bit number identifying a node on an IP network"));
        list.add(new item_quiz("20) “pull” operation",
                0, 0, 1, 0,
                " A.a advice for converting digital signals and vice-versa ",
                "B.a hardware and software combination used to connect the same type of networks",
                "C.an email transfer process in which the receiving computer initiates the connection",
                "D.a 32 bit number identifying a node on an IP network"));
        list.add(new item_quiz("21) A modem",
                1, 0, 0, 0,
                "A. a advice for converting digital signals and vice-versa ",
                "B.a hardware and software combination used to connect the same type of networks",
                "C.an email transfer process in which the receiving computer initiates the connection",
                "D.a 32 bit number identifying a node on an IP network"));
        list.add(new item_quiz("22) A bridge",
                0, 1, 0, 0,
                "A. a advice for converting digital signals and vice-versa ",
                "B.a hardware and software combination used to connect the same type of networks",
                "C.an email transfer process in which the receiving computer initiates the connection",
                "D.a 32 bit number identifying a node on an IP network"));
        list.add(new item_quiz("23) PIM ",
                1, 0, 0, 0,
                "A. Personal Information Manger ",
                "B. Processor In Memory ",
                "C. Print Image Matching",
                "D. Parallel Inference Machine"));
        list.add(new item_quiz("24)LAN ",
                0, 0, 1, 0,
                "A. Live Action Network ",
                "B. Local Available Names  ",
                "C. Local Access Network",
                "D. Learners Advisory Network"));
        list.add(new item_quiz("25) FTP  ",
                0, 0, 0, 1,
                "A. Files To Peers",
                "B. File Transfer Process",
                "C. Fuel Transfer Point",
                "D. File Transfer Protocol"));
        list.add(new item_quiz("26) TCP  ",
                0, 0, 1, 0,
                "A. Trusted Computer Platform",
                "B. Traffic Control",
                "C. Transmission Control Protocol",
                "D. Tool Center Point."));
        list.add(new item_quiz("27) POP   ",
                0, 1, 0, 0,
                "A. Point Of Presence",
                "B. Post Office Protocol",
                "C. Protocol Of Post Office",
                "D. Program Operation Plan"));
        list.add(new item_quiz("28. biz  ",
                0, 0, 0, 1,
                "A.Busy",
                "B. Business Site",
                "C. Business introduction /one ",
                "D. Business internet/one."));
        list.add(new item_quiz("29. A software from an ASP must be installed  locally on a user’ s   computer.",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("30. The practice Manager uses a payroll package based on database to calculate the salaries.",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("31. you need a high bandwidth connection to use an ASP service.",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("32. MP3 significantly reduces the information stored”.",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("33. JPEG is the most common compression system used for video",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("34. Different mail systems transfer emails in different ways",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("35. Wi-Fi networks are like fixed networks , but instead of cables use radio wave",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("36. Most the work done to prepare a message to be sent over a network is not seen by the user",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("37. A……….is an interface that enables dissimilar networks to communicate. ",
                1, 0, 0, 0,
                "A. gateway",
                "B. modem",
                "C. router",
                "D. bridge"));
        list.add(new item_quiz("38. Doctors access a…………..that holds the records of all the patients in the practice",
                1, 0, 0, 0,
                "A. database",
                "B. LAN  ",
                "C. CD  ",
                "D. Router "));
        list.add(new item_quiz("39. Logging on to your computer from a distance is an example of………….",
                0, 0, 1, 0,
                "A. UDP",
                "B. IRC",
                "C. Telnet  ",
                "D. FTP  "));
        list.add(new item_quiz("40. The ………. encodes and sends the packets. ",
                1, 0, 0, 0,
                "A. physical layer",
                "B. transport layer",
                "C. session layer",
                "D. presentation layer"));
        list.add(new item_quiz("41. …………… is a simple message transfer protocol used to send messages between servers. ",
                0, 0, 1, 0,
                "A. ISP  ",
                "B. IMAP",
                "C. SMTP  ",
                "D. POP"));
        list.add(new item_quiz("42. The checksum is calculated by the …………….. layer which also reassembles the message segments.",
                0, 1, 0, 0,
                "A. physical ",
                "B. transport",
                "C. application",
                "D. session"));
        list.add(new item_quiz("43. A/AN ………….. is a set of related webpages .",
                0, 1, 0, 0,
                "A. data center",
                "B. website ",
                "C. office suite",
                "D. both A and C"));
        list.add(new item_quiz("44. The standard developed  for audio and video compression is called………… .",
                1, 0, 0, 0,
                "A. MP3 ",
                "B.. Audio CD",
                "C. WAN",
                "D. MIDI"));
        list.add(new item_quiz("45. The program that converts songs from a CD to W A V files is called ………….",
                1, 0, 0, 0,
                "A. ripper",
                "B. decoder ",
                "C. recorder  ",
                "D. both B and C"));
        list.add(new item_quiz("46. You can tell which folder is currently being displayed by the ………… icon ",
                0, 0, 0, 1,
                "A. dragged  ",
                "B. deleted ",
                "C. displayed",
                "D. highlighted"));
        list.add(new item_quiz("47. Junk mail are good sources for ….",
                0, 0, 0, 1,
                "A. viruses",
                "B. attacks ",
                "C. services   ",
                "D.both A and B "));
        list.add(new item_quiz("48. To narrow down your search on Google search using .",
                0, 0, 1, 0,
                "A. define  ",
                "B. language ",
                "C. categories ",
                "D. brackets"));
        list.add(new item_quiz("49. ……….. is a way of dealing with bandwidth problem when you download a video from the net.",
                0, 0, 0, 1,
                "A. Ripping  ",
                "B. playback ",
                "C. Decompression",
                "D. Streaming"));
        list.add(new item_quiz("50. ……….. you listen to the first part , the next part is downloading .   ",
                1, 0, 0, 0,
                "A. AS",
                "B. After ",
                "C. Until ",
                "D. Once"));
        list.add(new item_quiz("51. you cannot save the file ……… you name it . ",
                0, 0, 0, 1,
                "A. while",
                "B. after",
                "C. when",
                "D. until"));
        list.add(new item_quiz("52. Once you ………. On the hyperlink , you ……… for the webpage to be copied on your computer. ",
                1, 0, 0, 0,
                "A. have clicked / wait",
                "B. clicked / waited",
                "C. will click / will wait",
                "D. are clicking / are waiting"));
        list.add(new item_quiz("53. If you spilled coffee on the keyboard , you ………….. it",
                0, 1, 0, 0,
                "A. will damage",
                "B.would damage   ",
                "C. would have damaged ",
                "D.damaged"));
        list.add(new item_quiz("54. If you ……….print Screen , you can make a copy of the screen .",
                1, 0, 0, 0,
                "A. press ",
                " B. pressed   ",
                "C. will press ",
                "D. to press"));
        list.add(new item_quiz("55. First ,  enter  the search criteria by ……… on the personal tab.",
                0, 0, 1, 0,
                "A.click",
                "B. to click ",
                "C. clicking ",
                "D. clicks"));
        list.add(new item_quiz("56. Exercise in the morning rather than ….",
                0, 1, 0, 0,
                "A. smoke",
                "B. smoking  ",
                "C. to smoke ",
                " D. smokes"));
        list.add(new item_quiz("57. Each MP3 file has a tag ………. extra information to be stored on other track details.",
                0, 0, 1, 0,
                "A. allows",
                " B. to allow  ",
                "C. allowing    ",
                " D. allow "));
        list.add(new item_quiz("58. Finally , click on find to ……………. The search ",
                0, 0, 0, 1,
                "A. starts",
                "B. to start   ",
                "C. starting ",
                "D. start"));
        list.add(new item_quiz("59. A network is a number of computers ………. together.",
                0, 0, 0, 1,
                "A. links",
                "B. to link",
                "C. linking",
                "D. linked"));
        list.add(new item_quiz("60. Avoid …………….. financial information in a chat room.",
                0, 0, 1, 0,
                "A. gives  ",
                " B. to give ",
                "C. giving",
                "D. gave"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, R2016_3.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}

