package com.Elkood.ling_en4.EN4.Repeatrer_Quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.EN4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

public class r2017 extends AppCompatActivity {
    private ViewGroup progressBar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2017);
        recyclerView = findViewById(R.id.recycle_view2017);
        progressBar = findViewById(R.id.pro2017);

        ArrayList<item_quiz> list = new ArrayList<>();
        list.add(new item_quiz("1 ) The Main Idea in The paragraph is  : ",
                1, 0, 0, 0,
                "A. a doctors’ software",
                "B. patients’ personal details",
                "C. a system’s application programs ",
                "D. mangers’ and receptions"));

        list.add(new item_quiz("2 ) In line 1, the pronoun “Which” refers to ………….  :  ",
                0, 0, 0, 1,
                "A. patients",
                "B. doctors",
                "C. networkers",
                "D. databases"));


        list.add(new item_quiz("3 ) In line 6, the word  practice is similar in meaning to a/an …… ",
                0, 0, 1, 0,
                "A. office",
                "B.net café",
                "C. clinic ",
                "D. reception"));

        list.add(new item_quiz("4 ) In line 9, the verb call up is closer in meaning to ……",
                1, 0, 0, 0,
                "A. display",
                "B. phone",
                "C. send",
                "D. hide"));

        list.add(new item_quiz("5 ) In line 16, the word  tailored software  means a/an …… package",
                0, 1, 0, 0,
                "A. a fully operational",
                "B. specific purpose",
                "C. standard",
                "D. open"));
        list.add(new item_quiz("6 ) It could be inferred from the text that different people in the practice use  …………application(s)",
                0, 0, 0, 1,
                "A. the same",
                "B. different",
                "C. tailored",
                "D. B and C"));
        list.add(new item_quiz("7 ) According to the passage. All the following is correct EXCEPT…………. : ",
                0, 0, 1, 0,
                "A. doctors use patients stored records",
                "B. doctors check their appointments",
                "C. Patients wait in the practice for a long time",
                "D. doctors check stored information about drugs"));
        list.add(new item_quiz("8 ) According to the passage, which program is used to calculate the payroll?",
                1, 0, 0, 0,
                "A. Excel  ",
                "B. word ",
                "C. Access",
                "D. PowerPoint"));
        list.add(new item_quiz("9 ) In line 19, the word payroll'' means ……",
                0, 0, 0, 1,
                "A. drugs ",
                "B. appointments ",
                "C. tables",
                "D. salaries"));
        list.add(new item_quiz("10 ) According to the passage, doctors use databases for all the following EXCEPT…………. : .",
                0, 0, 1, 0,
                "A. patients records",
                "B. appointments",
                "C. employees’ names",
                "D. information about drugs "));
        list.add(new item_quiz("11) Barcode : ",
                0, 0, 0, 1,
                "A. Engine",
                "B. Tray",
                "C. Computer",
                "D. Reader "));
        list.add(new item_quiz("12) mainframe : ",
                0, 0, 1, 0,
                "A. Engine",
                "B. Tray",
                "C. Computer",
                "D. Reader "));
        list.add(new item_quiz("13) System :  ",
                0, 1, 0, 0,
                "A. Engine",
                "B. Tray",
                "C. Computer",
                "D. Reader "));
        list.add(new item_quiz("14) Search :  ",
                1, 0, 0, 0,
                "A. Engine",
                "B. Tray",
                "C. Computer",
                "D. Reader "));
        list.add(new item_quiz("15) Push” operation ",
                0, 1, 0, 0,
                "A. set boundaries for beginning and end of a message ",
                "B. an email process in which the connection is initialized by the sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving"));
        list.add(new item_quiz("16) Internet address",
                0, 0, 1, 0,
                "A. set boundaries for beginning and end of a message ",
                "B. an email process in which the connection is initialized by the sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving"));
        list.add(new item_quiz("17) Full-duplex",
                0, 0, 0, 1,
                "A. set boundaries for beginning and end of a message ",
                "B. an email process in which the connection is initialized by the sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving"));
        list.add(new item_quiz("18) Bracketing",
                1, 0, 0, 0,
                "A. set boundaries for beginning and end of a message ",
                "B. an email process in which the connection is initialized by the sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving"));
        list.add(new item_quiz("19) Backbone",
                0, 0, 0, 1,
                "A. collection of related webpages ",
                "B. high capacity internet connection",
                "C. a formula used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));
        list.add(new item_quiz("20) Algorithm",
                0, 0, 1, 0,
                "A. collection of related webpages ",
                "B. high capacity internet connection",
                "C. a formula used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));
        list.add(new item_quiz("21) Broadband",
                0, 1, 0, 0,
                "A. collection of related webpages ",
                "B. high capacity internet connection",
                "C. a formula used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));
        list.add(new item_quiz("22) Website",
                1, 0, 0, 0,
                "A. collection of related webpages ",
                "B. high capacity internet connection",
                "C. a formula used for compressing component of a data stream",
                "D. the main transmission path handling the major data"));
        list.add(new item_quiz("23) IP ",
                1, 0, 0, 0,
                "A. Internet Protocol ",
                "B. Image Processing ",
                "C. Interaction Point",
                "D. Instrument Panel"));
        list.add(new item_quiz("24) IRC ",
                0, 1, 0, 0,
                "A. Information Resource Center ",
                "B. Internet Relay Chat  ",
                "C. Innovation Relay Centre",
                "D. International Relations Center"));
        list.add(new item_quiz("25) SMTP  ",
                0, 0, 1, 0,
                "A. Small Mail Transfer Protocol",
                "B. Strategic Medium Tenn Plan",
                "C. Simple Mail Transfer Protocol",
                "D. Simple MessageTransfer Protocol"));
        list.add(new item_quiz("26) FTP  ",
                0, 0, 1, 0,
                "A. Failure To Progress",
                "B. File Transfer Process",
                "C. File Transfer Protocol",
                "D. Fuel Transfer Point"));
        list.add(new item_quiz("27) ASP   ",
                1, 0, 0, 0,
                "A. Application Service Provider",
                "B. Average Sales Price",
                "C. Advanced Simple Profile",
                "D. Alternative School Programs"));


        list.add(new item_quiz("28. To find a webpage, you have to click on a hyperlink or enter a URL.  ",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("29. The page travels in packets from router to router.",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("30. Different mail systems transfer emails in different ways.",
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
        list.add(new item_quiz("32. To make you search for “Arsenal” more specific on google, type “football”.",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("33. TCP only works with packet-switched networks",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("34. Spam is very common",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("35. Web-based email is suitable if you work in an office",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("36. Analogue signals are used on ordinary telephone lines",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("37. A………. is an interface that enables dissimilar networks to communicate. ",
                1, 0, 0, 0,
                "A. gatway",
                "B. modem",
                "C. router",
                "D. bridge"));
        list.add(new item_quiz("38. A………. is a simple computer comprising a processor and memory, display, keyboard, mouse and hard drives only",
                1, 0, 0, 0,
                "A. thin client",
                "B. LAN",
                "C. bridge",
                "D. Hub"));
        list.add(new item_quiz("39. The checksum is calculated by the ………...layer which also reassembles the message segments.",
                0, 1, 0, 0,
                "A. physical",
                "B. transport",
                "C. application ",
                "D. session"));
        list.add(new item_quiz("40. The…………. encodes and sends the packets. ",
                1, 0, 0, 0,
                "A. physical layer",
                "B. transport layer",
                "C. session layer",
                "D. presentation layer"));
        list.add(new item_quiz("41. ………….is logging on to your computer at a distance. ",
                0, 0, 1, 0,
                "A. IRC",
                "B. FTP",
                "C. Telnet",
                "D. MOOs"));
        list.add(new item_quiz("42. A self-replicating program is a/an………...",
                0, 0, 0, 1,
                "A. accounting system",
                "B. SAP",
                "C. office suite",
                "D. virus"));
        list.add(new item_quiz("43. MP3 files are much………. than WAV files. Each minute can be stored in one megabyte.",
                0, 0, 1, 0,
                "A. clearer",
                "B. larger ",
                "C. smaller",
                "D. nosier"));
        list.add(new item_quiz("44. MP3 files can provide information about itself in coded block called a ………..",
                1, 0, 0, 0,
                "A. tag ",
                "B. URL",
                "C. layer",
                "D. sector"));
        list.add(new item_quiz("45. A program that rips songs from a CD and turns them into WAV file is a………….",
                0, 1, 0, 0,
                "A. recorder",
                "B. ripper",
                "C. encoder ",
                "D. decoder"));
        list.add(new item_quiz("46. A………. network is linked by radio waves",
                0, 0, 1, 0,
                "A. NIC",
                "B. LAN",
                "C. Wifi",
                "D. cable"));
        list.add(new item_quiz("47. …………is used by servers and PCs to send mails.",
                0, 0, 0, 1,
                "A. TCP/IP",
                "B. IMAP ",
                "C. POP",
                "D. SMTP"));
        list.add(new item_quiz("48. ……… allows you to download message headers before downloading the message.",
                0, 1, 0, 0,
                "A. TCP/IP",
                "B. IMAP ",
                "C. POP",
                "D. SMTP"));
        list.add(new item_quiz("49. Once you………. on the by perlink. You ………. for the webpage to be copied on your computer.",
                1, 0, 0, 0,
                "A. have clicked / wait",
                "B. clicked / waited",
                "C. will click / will wait",
                "D. are clicking / are waiting"));

        list.add(new item_quiz("50. The house has an electronic door-keeper………….to recognize you…………. access to family only.   ",
                0, 0, 0, 1,
                "A. program / give",
                "B. programmed / gave",
                "C. programmed / give",
                "D. programmed / giving"));
        list.add(new item_quiz("51. You cannot save the file……. you name it. ",
                0, 0, 0, 1,
                "A. while",
                "B. after",
                "C. when",
                "D. until"));
        list.add(new item_quiz("52. If you spilled coffee on the keyboard. you…….it. ",
                0, 1, 0, 0,
                "A. will damage",
                "B. would damage",
                "C. would damaged",
                "D. damaged"));
        list.add(new item_quiz("53. If you……. Print Screen. You……. a copy of the screen",
                1, 0, 0, 0,
                "A. press / can make",
                "B. pressed / could make   ",
                "C. will press /will make",
                "D. press / would made"));
        list.add(new item_quiz("54. If there………. a power cut while you were using the computer. you might lose unsaved data.",
                0, 0, 1, 0,
                "A. is ",
                " B. was ",
                "C. were ",
                "D. has been"));
        list.add(new item_quiz("55. Avoid……… a search function in your website as much as possible.",
                1, 0, 0, 0,
                "A. using",
                " B. use ",
                "C. used ",
                "D. to use"));

        list.add(new item_quiz("56. A MIDI message ………sound into 8 bite of digital information.",
                0, 0, 0, 1,
                "A. to encode",
                "B. encoding  ",
                "C. encoded ",
                "D. encodes"));
        list.add(new item_quiz("57. Streaming is a way of dealing with bandwidth problems ……… you download video from the Internet.",
                1, 0, 0, 0,
                "A. when",
                " B. after    ",
                "C. until    ",
                " D. once "));
        list.add(new item_quiz("58. Exercise in the morning rather than……….",
                0, 1, 0, 0,
                "A. smoke",
                "B. smoking  ",
                "C. to smoke ",
                " D. smokes"));
        list.add(new item_quiz("59. A client is a network computer ………… for accessing a service on a server.",
                0, 0, 0, 1,
                "A. use",
                "B. to use",
                "C. using",
                "D. used"));
        list.add(new item_quiz("60. A router is a special computer ……… messages when several networks are linked.",
                0, 1, 0, 0,
                "A. to direct",
                " B. directing",
                "C. directed",
                "D. directs"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, r2017.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);


    }

}
