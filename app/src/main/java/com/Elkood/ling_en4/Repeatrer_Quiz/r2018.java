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

public class r2018 extends AppCompatActivity {
    private LinearLayout progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2018);
        recyclerView = findViewById(R.id.recycle_view2018);
        progressBar = findViewById(R.id.pro2018);
        ArrayList<item_quiz> list = new ArrayList<>();
        list.add(new item_quiz("1. The main idea of this paragraph is ………",
                1, 0, 0, 0,
                "A. buffering data   ",
                " B.video playback ",
                "C. streaming videos  ",
                "D. video player"));
        list.add(new item_quiz("2.In line 7, the word  it  refers to the",
                0, 0, 0, 1,
                "A. memory ",
                "B. internet ",
                "C. video player ",
                "D.movie"));
        list.add(new item_quiz("3.In line 5, the word buffering is similar in meaning to a/an ……",
                0, 0, 1, 0,
                "A.file storage ",
                "B. data storage  ",
                "C. temporary storage        ",
                "D. permanent storage  "));
        list.add(new item_quiz("4. In line 15, the word  interruption  is closer in meaning to ……",
                1, 0, 0, 0,
                "A. disconnection  ",
                "B. interference",
                "C. interaction            ",
                "D. intervention "));
        list.add(new item_quiz("5. In line 19, the word steady means ……",
                0, 1, 0, 0,
                "A. intermittent ",
                "B. stable ",
                "C. slow ",
                "D. uninterruptable"));
        list.add(new item_quiz("6.It could be inferred from the text that streaming is a/an ………downloading on the net.",
                0, 0, 0, 1,
                "A. application for",
                "B. line for  ",
                "C. method for  ",
                "D. solution for"));
        list.add(new item_quiz("7.According to the passage, all the following in correct EXCERT ",
                0, 0, 1, 0,
                "A. playback continues even if the data level is low    ",
                "B. the buffer sends data continuously to play the clip",
                "C. playback stops when the connection is slow ",
                "D. the user enjoyes the movie during downloading "));

        list.add(new item_quiz("8.barcode …….",
                0, 0, 0, 1,
                "A. .engine",
                "B. tray",
                "C. computer ",
                "D. reader "));

        list.add(new item_quiz("9. mainframe …. ",
                0, 0, 1, 0,
                "A. .engine",
                "B. tray",
                "C. computer ",
                "D. reader "));


        list.add(new item_quiz("10 .system…. ",
                0, 1, 0, 0,
                "A. .engine",
                "B. tray",
                "C. computer ",
                "D. reader "));


        list.add(new item_quiz("11. search  ….",
                1, 0, 0, 0,
                "A. .engine",
                "B. tray",
                "C. computer ",
                "D. reader "));

        list.add(new item_quiz(" 12. “PUSH” operation  …. ",
                0, 1, 0, 0,
                "A. set boundaries for beginning and end of a message  ",
                "B. an email process in which the connection is initialized by sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving "));
        list.add(new item_quiz("13. Internet address .",
                0, 0, 1, 0,
                "A. set boundaries for beginning and end of a message  ",
                "B. an email process in which the connection is initialized by sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving "));
        list.add(new item_quiz("14. Full-duplex  ",
                0, 0, 0, 1,
                "A. set boundaries for beginning and end of a message  ",
                "B. an email process in which the connection is initialized by sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving "));
        list.add(new item_quiz("15. bracketing .",
                1, 0, 0, 0,
                "A. set boundaries for beginning and end of a message  ",
                "B. an email process in which the connection is initialized by sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving "));
        list.add(new item_quiz("16. backbone  …. ",
                0, 0, 0, 1,
                "A. collection of related webpages",
                "B. high capacity internet connection    ",
                "C. a formal used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));
        list.add(new item_quiz("17. algorithm ",
                0, 0, 1, 0,
                "A. collection of related webpages",
                "B. high capacity internet connection    ",
                "C. a formal used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));
//arrived
        list.add(new item_quiz("18. broadband  ",
                0, 1, 0, 0,
                "A. collection of related webpages",
                "B. high capacity internet connection    ",
                "C. a formal used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));
        list.add(new item_quiz("19. website ",
                1, 0, 0, 0,
                "A. collection of related webpages",
                "B. high capacity internet connection    ",
                "C. a formal used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));

        list.add(new item_quiz("20) IP:  ",
                1, 0, 0, 0,
                "A.  Internet Protocol",
                "B. Image Processing",
                "C. Interaction Point",
                "D. Instrument Panel "));
        list.add(new item_quiz("21) IRC :  ",
                0, 1, 0, 0,
                "A. Information Resource Center",
                "B. Internet Relay Chat",
                "C. Innovation Relay Center",
                "D. International Relations Center"));
        list.add(new item_quiz("22) SMTP” operation ",
                0, 0, 1, 0,
                "A. Small Mail Transfer Protocol ",
                "B. Strategic Medium Term Plan",
                "C. Simple Mail Transfer Protocol ",
                "D. Simple Message Transfer Protocol"));
        list.add(new item_quiz("23) FTP",
                0, 0, 1, 0,
                "A. Failure To Progress",
                "B. File Transfer Process",
                "C. File Transfer Protocol",
                "D. Fuel Transfer Point"));
        list.add(new item_quiz("24) ASP",
                1, 0, 0, 0,
                "A. Application Service Provider ",
                "B. Average Sales Price",
                "C. Advanced Simple Profile ",
                "D. Alternative School Programs"));
        list.add(new item_quiz("25) DVD",
                1, 0, 0, 0,
                "A. Digital Video Disc ",
                "B. Dual View Display",
                "C. Digital Virus Disease ",
                "D. Dependable Video Discus"));
        list.add(new item_quiz("26) TCP",
                0, 1, 0, 0,
                "A. Traffic Control Point",
                "B. Transmission Control Protocol",
                "C. Trusted Computer Platform",
                "D. Terminal Control Program"));
        list.add(new item_quiz("27) .aero",
                0, 0, 1, 0,
                "A. Aerobic Endurance",
                "B. Animation Editor",
                "C. Aviation Industry",
                "D. Alternative Education"));
        list.add(new item_quiz("28. To find a webpage , you have to click on a hyperlink or enter a URL..  ",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("29. The page travels in packets from router to router..",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("30. Different mail systems transfer emails in different ways..",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("31. SMTP delivers messages one at a time.",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("32. To make you search for< Arsenal > more specific on google,type<football>..",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));

        list.add(new item_quiz("33. TCP only works with packet-switched networks.",
                0, 1, -1, -1,
                "A. True",
                "B. False",
                " ",
                " "));
        list.add(new item_quiz("34. Spam is very common.",
                1, 0, -1, -1,
                "A. True",
                "B. False",
                "",
                ""));
        list.add(new item_quiz("35. Web-based email is suitable if you work in office.",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("36. Analogue signals are used on ordinary telephone lines.",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("37. A …… is an interface that enables dissimilar networks to communicate.",
                1, 0, 0, 0,
                "A. gateway",
                "B. modem ",
                "C. router ",
                "D. bridge"));
        list.add(new item_quiz("38. A …… is a simple computer comprising a processor and memory, display, keyboard, mouse and hard drives only.",
                1, 0, 0, 0,
                "A. Thin client ",
                "B. LAN ",
                "C. bridge ",
                "D. hub"));
        list.add(new item_quiz("39. The checksum is calculated by the …… layer which also reassembles the message segments.",
                0, 1, 0, 0,
                "A. physical ",
                "B. transport ",
                "C. application",
                "D. session"));
        list.add(new item_quiz("40. The encodes and sends the packet.",
                1, 0, 0, 0,
                "A. physical layer ",
                "B. transport layer",
                "C. session layer ",
                "D. presentation layer"));
        list.add(new item_quiz("41. ……… is logging on to your computer at a distance.",
                0, 0, 1, 0,
                "A. IRC ",
                "B. FTP  ",
                "C. Telnet",
                "D. MOO"));
        list.add(new item_quiz("42. A self-replicating program is a/an ……… .",
                0, 0, 0, 1,
                "A. accounting system ",
                "B. SAP",
                "C. office suite",
                "D. virus"));
        list.add(new item_quiz("43. MP3 files are much ….. than WAV files. Each minute can be stored in one megabyte.",
                0, 0, 1, 0,
                "A. clearer ",
                "B. larger ",
                "C. smaller ",
                "D. nosier"));

        list.add(new item_quiz("44. Mp3 files can provide information about itself in coded block called a …. ",
                1, 0, 0, 0,
                "A.  tag    ",
                "B. URL  ",
                "C. layer ",
                "D. sector  "));
        list.add(new item_quiz("45. A program that rips songs from a CD and turns them into W A V file is a/an ……. ",
                0, 1, 0, 0,
                "A. recorder",
                "B. ripper  ",
                "C. encoder ",
                "D. decoder"));
        list.add(new item_quiz("46. A….. network is linked by radio waves ",
                0, 0, 1, 0,
                "A. NIC ",
                "B. LAN",
                "C.  Wifi      ",
                "D. cable"));
        list.add(new item_quiz("47. ……. Is used by server and PCs to send mail ",
                0, 0, 0, 1,
                "A.  TCP/IP             ",
                "B. IMAP",
                "C. POP   ",
                "D. SMTP"));
        list.add(new item_quiz("48. ……….. allows you to download  message headers before downloading the message ",
                0, 1, 0, 0,
                "A. TCP/IP      ",
                "B. IMAP  ",
                "C. POP ",
                "D. ISMTP "));
        list.add(new item_quiz("49. Once you… on the hyperlink , you…… for the webpage to be copied on your computer",
                1, 0, 0, 0,
                "A. have clicked / wait  ",
                "B.  clicked / waited   ",
                "C. will click / will wait         ",
                "D. are clicking / are waiting"));
        list.add(new item_quiz("50. The house has an electronic door –keeper …….. to recognize  you …… access to family only. ",
                0, 0, 0, 1,
                "A. program / give    ",
                "B. programmed /gave   ",
                "C. programmed / give  ",
                "D. . programmed /giving"));
        list.add(new item_quiz("51. You cannot save the file …… you name it.",
                0, 0, 0, 1,
                "A.while        ",
                "B. after ",
                "C. when  ",
                "D. until "));
        list.add(new item_quiz("52.If you spilled coffe on the keyboard, you … it.",
                0, 1, 0, 0,
                "A. will damage   ",
                "B. would damage  ",
                "C. would damaged   ",
                "D. damaged "));
        list.add(new item_quiz("53.If you print …. Screen , you ….. a copy of the screen . ",
                1, 0, 0, 0,
                "A. press / can make ",
                "B. pressed/could make",
                "C. will press/will make ",
                "D. press/would made "));

        list.add(new item_quiz("54..If there ……… a power cut while you were using the computer , you might lose unsaved data.  ",
                0, 0, 1, 0,
                "A. is ",
                "B. was ",
                "C. were ",
                "D. has been    "));
        list.add(new item_quiz("55. .Avoid …..  search function in your website as much as possible",
                1, 0, 0, 0,
                "A.using",
                "B.use",
                "C.used      ",
                "D. to use "));
        list.add(new item_quiz("56.A MIDI message ….. sound into 8-bits of digital information ",
                0, 0, 0, 1,
                "A. to encode   ",
                "B. encoding  ",
                "C. encoded   ",
                "D. encodes"));
        list.add(new item_quiz("57......... Ensure the machine is disconnected you repair a PC. ",
                0, 0, 1, 0,
                "A. once     ",
                "B. after  ",
                "C. before   ",
                "D. as "));
        list.add(new item_quiz("58. Exercise in the morning rather than.......... ",
                0, 1, 0, 0,
                "A. smoke    ",
                "B. smoking ",
                "C. to smoke    ",
                "D. smokes "));
        list.add(new item_quiz("59. A client is a network computer  ……. For accessing a service on server. ",
                0, 0, 0, 1,
                "A. use     ",
                "B. to use ",
                "C. using    ",
                "D. used"));
        list.add(new item_quiz("60. A router is a special computer………messages when serval networks are linked ",
                0, 1, 0, 0,
                "A. to direct ",
                "B. directing ",
                "C. directed ",
                "D. directs"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, r2018.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);


    }

}
