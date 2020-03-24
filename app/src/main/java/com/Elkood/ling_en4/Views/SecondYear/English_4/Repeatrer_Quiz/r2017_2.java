package com.Elkood.ling_en4.Views.TwoYear.English_3.English_4.Repeatrer_Quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Elkood.ling_en4.Adapter.Adapter_Quiz;
import com.Elkood.ling_en4.Models.Quiz;
import com.Elkood.ling_en4.R;

import java.util.ArrayList;

public class r2017_2 extends AppCompatActivity {
    private ViewGroup progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2017_2);
        recyclerView = findViewById(R.id.recycle_view2017_2);
        progressBar = findViewById(R.id.pro2017_2);

        ArrayList<Quiz> list = new ArrayList<>();
        list.add(new Quiz("1) The best title of this paragraph is  : ",
                0, 0, 1, 0,
                "A. The application layer",
                "B .Encoding and Encrypting Data",
                "C.Two Layers of Network Communication ",
                "D. Data conversion"));

        list.add(new Quiz("2 ) In line 6, the pronoun it refers to ………….  :  ",
                0, 0, 0, 1,
                "A. a network",
                "B. the application",
                "C. a message",
                "D. the application layer"));


        list.add(new Quiz("3 ) In line 11, the word interpret is similar in meaning to a/an …… ",
                0, 0, 1, 0,
                "A. identify",
                "B.transmit",
                "C. translate ",
                "D. receive"));

        list.add(new Quiz("4 ) In line 13, the word  encrypts means ……",
                0, 1, 0, 0,
                "A. decompress",
                "B. encode",
                "C. decode",
                "D.compress"));

        list.add(new Quiz("5 ) It could be inferred from the text that the application layer is all of the following EXCEPT",
                0, 0, 0, 1,
                "A. the user sees it",
                "B. it prepares the message",
                "C. It attaches a header",
                "D. It opens communication"));
        list.add(new Quiz("6 ) According to the passage, in network communication,)",
                0, 0, 1, 1,
                "A. different languages are used",
                "B. human-readable languages are used in communication",
                "C. data is converted into bits",
                "D. nothing changes in the message"));
        list.add(new Quiz("7 ) According to the passage, the receiving computer should receive the following information EXCEPT…………. : ",
        0, 0, 0, 1,
                "A. The address",
                "B. compression method",
                "C. encryption way",
                "D. the translated data"));
        list.add(new Quiz("8 ) synchronous?",
                0, 0, 0, 1,
                "A. board  ",
                "B.  name ",
                "C.button",
                "D. transmission"));
        list.add(new Quiz("9 ) graphical ……",
                0, 0, 1, 0,
                "A.board  ",
                "B. name ",
                "C. button",
                "D.transmission "));
        list.add(new Quiz("10 ) domain…………. : .",
                0, 1, 0, 0,
                "A.board ",
                "B.name ",
                "C.button",
                "D. transmission "));
        list.add(new Quiz("11) bulletin : ",
                1, 0, 0, 0,
                "A. board",
                "B. name",
                "C. button",
                "D.transmission "));
        list.add(new Quiz("12) Push operation : ",
                0, 1, 0, 0,
                "A. set boundaries for beginning and end of a message",
                "B. an email process in which the connection is initialized by the sending computer",
                "C. a 32-bit number identifying a node on an IP network",
                "D. a transmission mode in which each computer takes turns sending and receiving "));
        list.add(new Quiz("13) Internet address :  ",
                0, 0, 1, 0,
                "A. set boundaries for beginning and end of a message",
                "B. an email process in which the connection is initialized by the sending computer",
                "C.  a 32-bit number identifying a node on an IP network",
                "D. a transmission mode in which each computer takes turns sending and receiving "));
        list.add(new Quiz("14) Full-duplex :  ",
                0, 0, 0, 1,
                "A. set boundaries for beginning and end of a message",
                "B. an email process in which the connection is initialized by the sending computer",
                "C. a 32-bit number identifying a node on an IP network",
                "D. a transmission mode in which each computer takes turns sending and receiving "));
        list.add(new Quiz("15) Bracketing ",
                1, 0, 0, 0,
                "A. set boundaries for beginning and end of a message ",
                "B. an email process in which the connection is initialized by the sending computer",
                "C. a 32-bit number identifying a node on an IP network ",
                "D. a transmission mode in which each computer takes turns sending and receiving"));
        list.add(new Quiz("16) A Gateway",
                0, 0, 0, 1,
                "A. hardware and software combination to connect similar networks ",
                "B. coding system for structuring and formatting documents",
                "C. a formula for decompressing components of a data stream ",
                "D. an interface enabling dissimilar networks to communicate"));
        list.add(new Quiz("17) A bridge",
                1, 0, 0, 0,
                "A. hardware and software combination to connect similar networks ",
                "B. coding system for structuring and formatting documents",
                "C.a formula for decompressing components of a data stream  ",
                "D. an interface enabling dissimilar networks to communicate"));
        list.add(new Quiz("18) Markup Language",
                0, 1, 0, 0,
                "A. hardware and software combination to connect similar networks ",
                "B.  coding system for structuring and formatting documents",
                "C. a formula for decompressing components of a data stream ",
                "D.an interface enabling dissimilar networks to communicate"));
        list.add(new Quiz("19) Algorithm",
                0, 0, 1, 0,
                "A.hardware and software combination to connect similar networks ",
                "B.coding system for structuring and formatting documents ",
                "C.a formula for decompressing components of a data stream ",
                "D.an interface enabling dissimilar networks to communicate"));
        list.add(new Quiz("20) DVD",
                1, 0, 0, 0,
                "A. Digital Video Disc ",
                "B. Dual View Display",
                "C. Digital Virus Disease",
                "D. Dependable Video Discus"));
        list.add(new Quiz("21) TCP",
                0, 1, 0, 0,
                "A. Traffic Control Point ",
                "B. Transmission Control Protocol",
                "C. Trusted Computer Platform",
                "D. Terminal Control Program"));
        list.add(new Quiz("22) aero",
                0, 0, 1, 0,
                "A. Aerobic Endurance ",
                "B. Animation Editor",
                "C.Aviation Industry ",
                "D.Alternative Education "));
        list.add(new Quiz("23) DNS ",
                0, 0, 1, 0,
                "A. Dow Number Service ",
                "B. Driver Night Sight ",
                "C.Domain Name Server ",
                "D.Damn Network Solutions "));
        list.add(new Quiz("24) ASP ",
                1, 0, 0, 0,
                "A. Application Service Provider ",
                "B. Analog Signal Processing ",
                "C.Advanced Simple Profile ",
                "D.Active Server Page "));
        list.add(new Quiz("25) XML  ",
                0, 0, 0, 1,
                "A.Xtra Microsoft Loopholes ",
                "B.Xperimental Markup Language ",
                "C.XCC Mod Launcher ",
                "D.Extensible Markup Language "));
        list.add(new Quiz("26) HTML is no longer useful for creating webpages  ",
                0, 1, -1, -1,
                "A.True ",
                "B. false",
                " ",
                ""));
        list.add(new Quiz("27) Add many graphic navigation tools in the webpage to help visitors   ",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));


        list.add(new Quiz("28. It is better to avoid frames when designing a website.  ",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("29. The page travels in packets from router to router.",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("30. Different mail systems transfer emails in different ways.",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("31. POP delivers messages one at a time.",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("32. To make your search for Arsenal  more specific on Google, type football .",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("33. Online gaming and video require a high usage allowance",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("34. Spam is very common",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("35. Web-based email is suitable if you work in an office",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("36. Analogue signals are used on ordinary telephone lines",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("37.WiFi network is cheaper than cabling. ",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("38.An e-commerce business usually provides all the required technology itself",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new Quiz("39. A …… is a simple computer comprising a processor and memory, display, keyboard, mouse and drives only.",
                1, 0, 0, 0,
                "A.thin client ",
                "B.LAN ",
                "C. bridge ",
                "D.Hub "));
        list.add(new Quiz("40. The checksum is calculated by the …… layer which also reassembles the message segments. ",
                0, 1, 0, 0,
                "A.physical ",
                "B.transport ",
                "C.application ",
                "D.session "));
        list.add(new Quiz("41. ………….is logging on to your computer at a distance. ",
                0, 0, 1, 0,
                "A. IRC",
                "B. FTP",
                "C. Telnet",
                "D. MOOs"));
        list.add(new Quiz("42. A self-replicating program is a/an………...",
                0, 0, 0, 1,
                "A. accounting system",
                "B. SAP",
                "C. office suite",
                "D. virus"));
        list.add(new Quiz("43. MP3 files are much………. than WAV files. Each minute can be stored in one megabyte.",
                0, 0, 1, 0,
                "A. clearer",
                "B. larger ",
                "C. smaller",
                "D. nosier"));
        list.add(new Quiz("44. MP3 files can provide information about itself in coded block called a ………..",
                1, 0, 0, 0,
                "A. tag ",
                "B. URL",
                "C. layer",
                "D. sector"));
        list.add(new Quiz("45. A program that rips songs from a CD and turns them into WAV file is a………….",
                0, 1, 0, 0,
                "A. recorder",
                "B. ripper",
                "C. encoder ",
                "D. decoder"));
        list.add(new Quiz("46. A………. network is linked by radio waves",
                0, 0, 1, 0,
                "A. NIC",
                "B. LAN",
                "C. Wifi",
                "D. cable"));
        list.add(new Quiz("47. …………is used by servers and PCs to send mails.",
                0, 0, 0, 1,
                "A. TCP/IP",
                "B. MAPI ",
                "C. POP",
                "D. SMTP"));
        list.add(new Quiz("48. ……… allows you to download message headers before downloading the message.",
                0, 1, 0, 0,
                "A. TCP/IP",
                "B. IMAP ",
                "C. POP",
                "D. SMTP"));
        list.add(new Quiz("49. Once you………. on the by perlink. You ………. for the webpage to be copied on your computer.",
                1, 0, 0, 0,
                "A. have clicked / wait",
                "B. clicked / waited",
                "C. will click / will wait",
                "D. are clicking / are waiting"));
        list.add(new Quiz("50. The house has an electronic door-keeper………….to recognize you…………. access to family only.   ",
                0, 0, 0, 1,
                "A. program / give",
                "B. programmed / gave",
                "C. programmed / give",
                "D. programmed / giving"));
        list.add(new Quiz("51. You cannot save the file……. you name it. ",
                0, 0, 0, 1,
                "A. while",
                "B. after",
                "C. when",
                "D. until"));
        list.add(new Quiz("52. If you spilled coffee on the keyboard. you…….it. ",
                0, 1, 0, 0,
                "A. will damage",
                "B. would damage",
                "C. would damaged",
                "D. damaged"));
        list.add(new Quiz("53. If you……. Print Screen. You……. a copy of the screen",
                1, 0, 0, 0,
                "A. press/can make",
                "B. pressed/could make   ",
                "C. will press/will make",
                "D. press/would made"));
        list.add(new Quiz("54. If there………. a power cut while you were using the computer. you might lose unsaved data.",
                0, 0, 1, 0,
                "A. is ",
                " B. was ",
                "C. were ",
                "D. has been"));
        list.add(new Quiz("55. Avoid……… a search function in your website as much as possible.",
                1, 0, 0, 0,
                "A. using",
                " B. use ",
                "C. used ",
                "D. to use"));

        list.add(new Quiz("56. A MIDI message ………sound into 8 bite of digital information.",
                0, 0, 0, 1,
                "A. to encode",
                "B. encoding  ",
                "C. encoded ",
                " D. encodes"));
        list.add(new Quiz("57. Streaming is a way of dealing with bandwidth problems ……… you download video from the Internet.",
                1, 0, 0, 0,
                "A. when",
                " B. after    ",
                "C. until    ",
                " D. once "));
        list.add(new Quiz("58. Exercise in the morning rather than……….",
                0, 1, 0, 0,
                "A. smoke",
                " B. smoking  ",
                "C. to smoke ",
                " D. smokes"));
        list.add(new Quiz("59. A server is a powerful computer …… data shared by all the clients in the network.",
                0, 0, 0, 1,
                "A.store",
                "B.to store ",
                "C.stored ",
                "D. storing"));
        list.add(new Quiz("60. A router is a special computer ……… messages when several networks are linked.",
                0, 1, 0, 0,
                "A. to direct",
                " B. directing",
                "C. directed",
                "D. directs"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, r2017_2.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);


    }

}
