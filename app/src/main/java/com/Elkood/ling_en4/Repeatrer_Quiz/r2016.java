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

public class r2016 extends AppCompatActivity {
    private LinearLayout progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2016);
        recyclerView = findViewById(R.id.recycle_view2016);
        progressBar = findViewById(R.id.pro2016);
        ArrayList<item_quiz> list = new ArrayList<>();
        list.add(new item_quiz("1.the main idea of this passage is…….",
                0, 0, 1, 0,
                "A. Wireless Networks Vs Wired Networks",
                "B. Advantages of Wireless Network",
                "C. Advantages and Disadvantages of Wireless Networks",
                "D. Disadvantages of Wireless Networks"));

        list.add(new item_quiz("2. In line 1, the pronoun “It” stands for…. ",
                0, 0, 0, 1,
                "A. a network",
                "B. a building",
                "C. an access point",
                "D. cabling"));


        list.add(new item_quiz("3. in line 2, the abbreviation “WiFi” refers to …. ",
                0, 0, 1, 0,
                "A. Wireless Internet Free Internet",
                "B.  Wireless File",
                "C. Wireless Fidelity ",
                "D.  Wire Free Internet"));


        list.add(new item_quiz("4. We can understand from the passage that the function of access points is to connect ….",
                1, 0, 0, 0,
                "A. a Wireless Networks to a wired on",
                "B. libraries and airports",
                "C. a similar network",
                " D. similar networks"));

        list.add(new item_quiz(" 5.In line4, the word “hotspots” means …. ",
                1, 0, 0, 0,
                "A. a location of an access point",
                "B.  a kind of network",
                "C. an office",
                "D.  a home network"));
        list.add(new item_quiz("6. It could be inferred from the passage that wireless networks are ……… than fixed ones.",
                0, 1, 0, 0,
                "A. faster",
                "B. slower",
                " C. cheaper",
                "D. both A and C"));
        list.add(new item_quiz("7. According to the passage, there is/are …….. advantage(s) to wireless networks ",
                0, 0, 1, 0,
                "A.1",
                "B.2 ",
                "C.3",
                "D.4"));
        list.add(new item_quiz(" 8. According to the passage, installing cables in old buildings is …. .",
                1, 0, 0, 0,
                "A. unpractical",
                "B. cheap",
                "C. practical",
                "D. easy"));
        list.add(new item_quiz("9. According to the passage, WiFi users can access the net in all of the following EXCEPT in …. ",
                1, 0, 0, 0,
                "A. taxes",
                "B. balconies ",
                "C. bedrooms",
                "D. office"));
        list.add(new item_quiz("10.Mainframe ",
                0, 0, 0, 1,
                "A.Card ",
                "B.Reader",
                "C.Engine ",
                "D.Computer"));

//arrived
        list.add(new item_quiz("11.Expansion : ",
                1, 0, 0, 0,
                "A.Card ",
                "B.Reader",
                "C.Engine ",
                "D.Computer"));
        list.add(new item_quiz("12) Barcode : ",
                0, 0, 0, 1,
                "A. Engine",
                "B. Tray",
                "C. Computer",
                "D. Reader "));
        list.add(new item_quiz("13) Search :  ",
                1, 0, 0, 0,
                "A. Engine",
                "B. Tray",
                "C. Computer",
                "D. Reader "));
        list.add(new item_quiz("14) Half duplex  ",
                0, 0, 0, 1,
                "A. a special computer that directs communicating messages when several nets are connected",
                "B.a self-duplicating program",
                "C. connection initiated by the sending computer rather than the receiving computer",
                "D. Each computer take turns sending and receiving"));

        list.add(new item_quiz("15) Push” operation ",
                0, 0, 1, 0,
                "A. a special computer that directs communicating messages when several nets are connected",
                "B.a self-duplicating program",
                "C. connection initiated by the sending computer rather than the receiving computer",
                "D. Each computer take turns sending and receiving"));
        list.add(new item_quiz("16) Router :",
                1, 0, 0, 0,
                "A. a special computer that directs communicating messages when several nets are connected",
                "B.a self-duplicating program",
                "C. connection initiated by the sending computer rather than the receiving computer",
                "D. Each computer take turns sending and receiving"));
        list.add(new item_quiz("17) Virus :",
                0, 1, 0, 0,
                "A. a special computer that directs communicating messages when several nets are connected",
                "B.a self-duplicating program",
                "C. connection initiated by the sending computer rather than the receiving computer",
                "D. Each computer take turns sending and receiving"));
        list.add(new item_quiz("18) Algorithm : ",
                0, 0, 0, 1,
                "A. stored information used to route data through a gateway ",
                "B. set boundaries for the beginning and end of a message",
                "C. the main transmission path handling major data traffic",
                "D.a formula used for decompressing components of a data stream"));
        list.add(new item_quiz("19) Backbone",
                0, 0, 1, 0,
                "A. stored information used to route data through a gateway ",
                "B. set boundaries for the beginning and end of a message",
                "C. the main transmission path handling major data traffic",
                "D.a formula used for decompressing components of a data stream"));
        list.add(new item_quiz("20) Bracketing : ",
                0, 1, 0, 0,
                "A. stored information used to route data through a gateway ",
                "B. set boundaries for the beginning and end of a message",
                "C. the main transmission path handling major data traffic",
                "D.a formula used for decompressing components of a data stream"));
        list.add(new item_quiz("21) Look-up table : ",
                1, 0, 0, 0,
                "A. stored information used to route data through a gateway ",
                "B. set boundaries for the beginning and end of a message",
                "C. the main transmission path handling major data traffic",
                "D.a formula used for decompressing components of a data stream"));
        list.add(new item_quiz("22) PIM : ",
                1, 0, 0, 0,
                "A. Personal Information Manager",
                "B. Processor in Memory",
                "C. Print Image Matching",
                "D. Parallel Inference Machine"));
        list.add(new item_quiz("23) ASP :  ",
                0, 1, 0, 0,
                "A. Advanced Simple Profile ",
                "B. Application Service Provider",
                "C. Advanced Study Program",
                "D. Analog Signal Processing"));
        list.add(new item_quiz("24) DVD :  ",
                0, 0, 1, 0,
                "A.Digital Virus Disease  ",
                "B. Disk Video Digital  ",
                "C.Digital Video Disc ",
                "D. Dual View Display"));
        list.add(new item_quiz("25) LAN :   ",
                0, 0, 1, 0,
                "A. Live Action Network",
                "B. Local Available Names",
                "C. Local Access Network",
                "D. Learners Advisory Network"));
        list.add(new item_quiz("26) FTP  ",
                0, 0, 1, 0,
                "A. Failure To Progress",
                "B. File Transfer Process",
                "C. File Transfer Protocol",
                "D. Fuel Transfer Point"));
        list.add(new item_quiz("27) TCP :   ",
                0, 0, 1, 0,
                "A.Trusted Computer Platform",
                "B. Traffic Control Point",
                "C. Transmission Control Protocol",
                "D. Tool Center Point"));
        list.add(new item_quiz("28) POP :   ",
                0, 1, 0, 0,
                "A.Point Of Presence",
                "B.Post Office Protocol",
                "C.Protocol Of Post Office ",
                "D.Program Operating Plan "));
        list.add(new item_quiz("29) ORG :   ",
                1, 0, 0, 0,
                "A.Organization",
                "B.Origin ",
                "C.Open Rights Group  ",
                "D.Operations Research Group"));
        list.add(new item_quiz("30. Different mail systems transfer emails in different ways",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("31. Streaming is a way of dealing with bandwidth problems when you download from the net   ",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));
        list.add(new item_quiz("32. Internet addresses can be written as a series of number",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));

        list.add(new item_quiz("33 .Connection to the net cannot be provided by a standard dial up.",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));

        list.add(new item_quiz("34.  IRC is not chatting to others in real-time",
                0, 1, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));

        list.add(new item_quiz("35. Digital signals are used on ordinary telephone lines",
                1, 0, -1, -1,
                "A. True",
                "B. false",
                "",
                ""));

        list.add(new item_quiz("36. . A software from an ASP must be installed locally on a user’ s   computer",
                0, 1, -1, -1,
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
        list.add(new item_quiz("38.Doctors access a…………..that holds the records of all the patients in the practice.",
                1, 0, 0, 0,
                "A. database",
                "B. LAN",
                "C. CD",
                "D. Router"));
        list.add(new item_quiz("39. ..........Logging on to your computer from a distance is an example of ",
                0, 0, 1, 0,
                "A. UDP",
                "B. IRC",
                "C. Talent ",
                "D. FTP"));
        list.add(new item_quiz("40.The checksum is calculated by the ……… layer which also reassembles the message segments. ",
                0, 1, 0, 0,
                "A. physical",
                "B. transport",
                "C. application ",
                "D. Session"));
        list.add(new item_quiz("41. The…………. encodes and sends the packets. ",
                1, 0, 0, 0,
                "A. physical layer",
                "B. transport layer",
                "C. session layer",
                "D. presentation layer"));
        list.add(new item_quiz("42.. ……. is a simple message transfer protocol used to send messages between servers ",
                0, 0, 1, 0,
                "A. ISP",
                "B. IMAP",
                "C. SMTP",
                "D. POP"));
        list.add(new item_quiz("43. The practice manager uses a payroll package based on …… to calculate salaries.",
                0, 1, 0, 0,
                "A. word processor",
                "B. Spreadsheet ",
                "C. database",
                "D. both A and C"));

        list.add(new item_quiz("44. A/AN ………….. is a set of related webpages ..",
                0, 1, 0, 0,
                "A. data center",
                "B. Website ",
                "C. office suite",
                "D. both A and C"));
        list.add(new item_quiz("45. The standard developed  for audio and video compression is called…………. .",
                1, 0, 0, 0,
                "A. Mp3 ",
                "B. Audio CD",
                "C. WAN",
                "D. MIDI"));
        list.add(new item_quiz("46. The program that converts songs from a CD to W A V files is called ……………. .",
                1, 0, 0, 0,
                "A. ripper",
                "B. decoder",
                "C. recorder",
                "D. both b and c"));
        list.add(new item_quiz("47.You can tell which folder is currently being displayed by the ………… icon ..",
                0, 0, 0, 1,
                "A. dragged",
                "B. deleted ",
                "C. displayed",
                "D. highlighted"));
        list.add(new item_quiz("48. Junk mail are good sources for …….. ",
                0, 0, 0, 1,
                "A. viruses",
                "B. attacks ",
                "C. services",
                "D. both A and B"));
        list.add(new item_quiz("49. You can refine your search on Google by adding …. .",
                0, 0, 0, 1,
                "A.define",
                "B.Language",
                "C.more words",
                "D. brackets"));
        list.add(new item_quiz("50. Once you ………on the hyperlink, you ………. for the webpage to be copied on your computer.",
                1, 0, 0, 0,
                "A. have clicked / wait",
                "B. clicked / waited ",
                "C. will click / will wait ",
                "D. are clicking / are waiting"));
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
                "C. will press / will make",
                "D. press / would made"));
        list.add(new item_quiz("54. A MIDI message ……. Sound into 8 bite of digital information",
                0, 0, 0, 1,
                "A. encode ",
                "B. encoding ",
                "C. encoded ",
                "D. encodes"));
        list.add(new item_quiz("55. Exercise in the morning rather than ……...",
                0, 1, 0, 0,
                "A. smoke",
                " B. smoking ",
                "C. to smoke ",
                "D. smokes"));
        list.add(new item_quiz("56.First ,  enter  the search criteria by ……… on the personal tab.",
                0, 0, 1, 0,
                "A. click",
                "B. to click  ",
                "C. clicking ",
                " D. clicks"));
        list.add(new item_quiz("57. Each MP3 file has a tag ………... extra information to be stored on other track details.",
                0, 0, 1, 0,
                "A. allows",
                "B. to allow ",
                "C. allowing ",
                "D. allow "));
        list.add(new item_quiz("58. A network is a number of computers ………. together.",
                0, 0, 0, 1,
                "A. links",
                "B. to link  ",
                "C. linking ",
                "D. linked"));
        list.add(new item_quiz("59. Avoid …………….. financial information in a chat room.",
                0, 0, 1, 0,
                "A. gives",
                "B. to give",
                "C. giving",
                "D.gave"));
        list.add(new item_quiz("60.……….. you listen to the first part , the next part is downloading .",
                1, 0, 0, 0,
                "A. As",
                "B. After",
                "C. Until",
                "D. Once"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, r2016.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}
