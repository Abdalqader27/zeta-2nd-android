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

public class R2015 extends AppCompatActivity {
    private ViewGroup progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2015);
        recyclerView = findViewById(R.id.recycle_view2015);
        progressBar = findViewById(R.id.pro2015);

        ArrayList<item_quiz> list = new ArrayList<>();
        list.add(new item_quiz("1.the main idea of this passage is…….",
                0, 0, 1, 0,
                "A. Internet  ",
                "B.TCP/IP       ",
                " C. Internet Address   ",
                "D. Ethernet"));

        list.add(new item_quiz("  2.In line 1, the abbreviation “TCP” stands for ………….…. ",
                0, 0, 0, 1,
                "A.T-Commerce Protocol   ",
                "B. Transmission Control panel",
                "C. Test and Counting Protocol    ",
                "D. Transmission Control Protocol"));


        list.add(new item_quiz(" …. 3.In line 1, the pronoun “This” refers to ….……",
                0, 0, 1, 0,
                "A. a concept  ",
                "B.TCP portion  ",
                "C.   IP address   ",
                "D. a coding system "));


        list.add(new item_quiz("4.In line 2, the word “node” is close in meaning to………. ….",
                1, 0, 0, 0,
                "A.Point",
                "B. period   ",
                "C. network   ",
                " D. path"));

        list.add(new item_quiz(" 5.In line 3, the pronoun “it” refers to…………. …. ",
                0, 1, 0, 0,
                "A. node     ",
                "B. address  ",
                "C. path    ",
                "D. gateway"));
        list.add(new item_quiz("6.In line 4, the word “to route” is close in meaning to ………………..",
                0, 0, 1, 0,
                "A. transcend    ",
                "B. copy         ",
                " C. move",
                "D. direct"));
        list.add(new item_quiz("7.According to the passage, we can understand that networks are …………… ",
                0, 0, 1, 0,
                "A. similar",
                "B. equal",
                "C. different",
                "D. both A and B4"));
        list.add(new item_quiz(" 8.The purpose of the passage is…………. …. .",
                0, 1, 0, 0,
                "A. explanation    ",
                "B. comparison    ",
                "C. contrast      ",
                "D. both B and C"));


        list.add(new item_quiz("9. We can conclude from the text that IP is used for all the following EXCEPT…………. …. ",
                0, 0, 0, 0,
                "A. identifying nodes",
                "B. routing information         ",
                "C. providing paths",
                "D. delivering packets "));

        list.add(new item_quiz("10.In line 6, the pronoun “they” refers to………. ",
                1, 0, 0, 0,
                "A. machines ",
                "B. cables ",
                "C. IPs  ",
                "D.packets"));

        list.add(new item_quiz("11. We can conclude from the passage that …………. : ",
                0, 0, 0, 1,
                "A. computers can communicate directly",
                "B. computers need TCP/IP to communicate",
                "C. computers use Ethernet to communicate ",
                "D. Cables allow computers to communicate "));
        list.add(new item_quiz("12. We can conclude from the passage that an address is………. : ",
                1, 0, 0, 0,
                "A. a series of digits",
                "B. a series of networks",
                "C. a series of sub-networks",
                "D. a series of nodes"));
        list.add(new item_quiz("13.	Web :  ",
                0, 0, 0, 1,
                "A.Name",
                "B. Board",
                "C. Program",
                "D. page "));
        list.add(new item_quiz("14) File  :  ",
                1, 0, 0, 0,
                "A.Name",
                "B. Board",
                "C. Program",
                "D. page "));
        list.add(new item_quiz("15) Bulletin ",
                0, 1, 0, 0,
                "A.Name",
                "B. Board",
                "C. Program",
                "D. page "));
        list.add(new item_quiz("16) Supervisor",
                0, 0, 1, 0,
                "A.Name",
                "B. Board",
                "C. Program",
                "D. page "));
        list.add(new item_quiz("17) Metadata",
                0, 0, 0, 1,
                "	A.stored information used to route data through a gateway	 ",
                "	B.set boundaries for the beginning and end of a message",
                "	C.a mail transfer process in which the receiving computer initiates the connection  ",
                "	D.data about data "));
        list.add(new item_quiz("18) Pull operation",
                0, 0, 1, 0,
                "	A.stored information used to route data through a gateway	 ",
                "	B.set boundaries for the beginning and end of a message",
                "	C.a mail transfer process in which the receiving computer initiates the connection  ",
                "	D.data about data "));
        list.add(new item_quiz("19) Look-up table",
                1, 0, 0, 0,
                "	A.stored information used to route data through a gateway	 ",
                "	B.set boundaries for the beginning and end of a message",
                "	C.a mail transfer process in which the receiving computer initiates the connection  ",
                "	D.data about data "));
        list.add(new item_quiz("20) Bracketing",
                0, 1, 0, 0,
                "	A.stored information used to route data through a gateway	 ",
                "	B.set boundaries for the beginning and end of a message",
                "	C.a mail transfer process in which the receiving computer initiates the connection  ",
                "	D.data about data "));
        list.add(new item_quiz("21) Algorithm",
                1, 0, 0, 0,
                "	A.formula used for decompressing components of  a data  stream ",
                "	B.set boundaries for the beginning and end of a message",
                "	C.a mail transfer process in which the receiving computer initiates the connection  ",
                "	D.data about data "));


        list.add(new item_quiz("22) A backbone",
                0, 1, 0, 0,
                "	A.stored information used to route data through a gateway	 ",
                "	B.Main transmission path handling major data traffic",
                "	C.a mail transfer process in which the receiving computer initiates the connection  ",
                "	D.data about data "));
        list.add(new item_quiz("23) Checksum ",
                0, 0, 1, 0,
                "	A.stored information used to route data through a gateway	 ",
                "	B.set boundaries for the beginning and end of a message",
                "	C.Mathematical calculation based on the content of data",
                "	D.data about data "));
        list.add(new item_quiz("24) Markup Language ",
                0, 0, 0, 1,
                "	A.stored information used to route data through a gateway	 ",
                "	B.set boundaries for the beginning and end of a message",
                "	C.a mail transfer process in which the receiving computer initiates the connection  ",
                "	D.coding system used for structuring and formatting documents"));
        list.add(new item_quiz("25)  TA  ",
                0, 0, 0, 1,
                "A. Target Area",
                "B. Technology Assessment",
                "C. Technical Assistance",
                "D. Terminal Adapter"));
        list.add(new item_quiz("26) DSL  ",
                1, 0, 0, 0,
                "A. Digital subscriber Line ",
                "B. Document Summary List ",
                "C. Direct Swift Link",
                "D. Direct Service Line"));
        list.add(new item_quiz("27) SMS   ",
                0, 1, 0, 0,
                "A. Systems Management  Server",
                "B. Short Message Service",
                "C. School Management System",
                "D. Stock Market Simulation"));
        list.add(new item_quiz("28. POP.  ",
                0, 0, 1, 0,
                "A. Point of Purchase ",
                "B. Plenty Of Parking",
                "C. Post Office Protocol",
                "D. Point Of Presence"));
        list.add(new item_quiz("29. LAN .",
                0, 0, 1, 0,
                "A. Live Action Network",
                "B. Local Available Names",
                "C. Local Access Network",
                "D. Learners Advisory Network"));
        list.add(new item_quiz("30. A ……. Is an interface that enables dissimilar networks to communicate..",
                1, 0, 0, 0,
                "A.gateway ",
                "B. modem",
                "C. router",
                "D. bridge"));
        list.add(new item_quiz("31. A……. is a simple computer comprising a processor and memory, display, keyboard, mouse and hard drives only..",
                1, 0, 0, 0,
                "A.client",
                "B.LAN",
                "C. bridge ",
                "D. Hub"));
        list.add(new item_quiz("32.An email transfer process in which the connection is initialed by the sending computer is called…….”.",
                0, 1, 0, 0,
                "A. messaging protocol",
                "B. push operation",
                "C. message retrieval  ",
                "D. email transfer"));


        list.add(new item_quiz("33. Downloading a file from a server is an example of ……….",
                0, 0, 1, 0,
                "	A.User Datagram Protocol ",
                "B. Internet Protocol",
                "C. File Transfer Protocol ",
                " D. Transmission Control Protocol"));

        list.add(new item_quiz("34.The checksum is calculated by the ……… layer which also reassembles the message segments.",
                0, 1, 0, 0,
                "A. physical   ",
                "B. transport",
                "C. application",
                "D. Session"));
        list.add(new item_quiz("35. The ……. encodes and sends the packets.",
                0, 0, 0, 1,
                "A. physical layer     ",
                "B. transport layer      ",
                "C. session layer     ",
                "D. presentation layer"));

        list.add(new item_quiz("36. ……. Is a simple message transfer protocol used to send messages between servers.",
                0, 0, 1, 0,
                "A. ISP",
                "B. IMAP   ",
                "C. SMTP     ",
                "D.POP"));

        list.add(new item_quiz("37. Search engines do a better job because of the intelligent …. content.. ",
                0, 1, 0, 0,
                "A.HTML       ",
                "B.XML  ",
                "C. SGML    ",
                "D.A and C"));
        list.add(new item_quiz("38.GPS was developed by the US military to……….",
                0, 1, 0, 0,
                "A. design machine ",
                "B. pinpoint locations ",
                "C. store data      ",
                "D.A and C"));
        //TODO not yet

        list.add(new item_quiz("39. A/an ……. In a GPS receiver campares signals from at least three satellites..",
                0, 0, 0, 0,
                "A. microprocessor    ",
                "B. atomic clock    ",
                "C. navigation system  ",
                "D.B and C"));
        //TODO not yet

        list.add(new item_quiz("40.Wap phones will …. revolutionize the way we communicate.. ",
                0, 0, 0, 0,
                "A. provable        ",
                "B. improbable      ",
                "C. probable ",
                "D. probably"));
        list.add(new item_quiz(" 41.Once you ………on the hyperlink, you ………. for the webpage to be copied on your computer.. ",
                1, 0, 0, 0,
                "A. have clicked/wait   ",
                "B. clicked /waited",
                "C. will click/will wait      ",
                "D. are clicking/are waiting"));
        list.add(new item_quiz("42.The house has an electronic door-keeper ……… to recognize you …. access to family only.………...",
                0, 1, 0, 0,
                "A. program/give         ",
                "B. programmed/gave     ",
                "C. programmed/give      ",
                "D. programmed/giving"));
        //TODO no yet
        list.add(new item_quiz("43.It is…… that taxis will be robot-controlled..",
                0, 0, 0, 0,
                "A. luckily   ",
                "B. unlike       ",
                "C. liked       ",
                "D. likely"));
        list.add(new item_quiz("44.You cannot save the file ……. you name it ",
                0, 0, 0, 1,
                "A. while        ",
                "B. after        ",
                "C. when       ",
                "D. until"));
        list.add(new item_quiz("45.If you spilled coffee on the keyboard, you ……. It",
                0, 1, 0, 0,
                "A. will damage       ",
                "B. would damage",
                "C. would damaged",
                "D. damaged "));
        list.add(new item_quiz("46.If you…… Print screen, you ………. a copy of the screen",
                1, 0, 0, 0,
                "A. press/can make        ",
                "B. pressed/could make        ",
                "C. will press/will make   ",
                "press/would made"));
        list.add(new item_quiz("47.If there ……. A power cut while you were using the computer, you might lose unsaved data..",
                0, 1, 0, 0,
                "A. is       ",
                " B. was        ",
                "C. were",
                "D. has been"));
        list.add(new item_quiz("48.It is a good idea ……… a few large websites……... some ideas on designing an effective site map..",
                0, 0, 0, 1,
                "A. visits/gets        ",
                "B. will visit/will get ",
                "C. visit/get    ",
                "D. to visit/to get "));
        list.add(new item_quiz("49.Avoid ……. a search function in your website as much as possible..",
                1, 0, 0, 0,
                "A. using   ",
                "B. use     ",
                "C. used         ",
                "D. to use "));
        list.add(new item_quiz("50.Once the DNS server ……… the IP address, it sends it back to the browser..   ",
                0, 0, 1, 0,
                "A.to find   ",
                "B. will find  ",
                "C. has found ",
                "D. found"));
        list.add(new item_quiz("51.Streaming is a way of dealing with bandwidth problems ………... you download video from the Internet. ",
                1, 0, 0, 0,
                "A. when       ",
                "B. after",
                "C. until  ",
                "D. once"));
        list.add(new item_quiz("52.Exercise in the morning rather than …….... ",
                0, 1, 0, 0,
                "A. smoke",
                "B. smoking ",
                "C.to smoke  ",
                "D. smokes"));
        list.add(new item_quiz("53.A client is a network computer ……. For accessing a service on a server.",
                0, 0, 0, 1,
                "A. use   ",
                "B. to use    ",
                "C. using   ",
                "D. used "));
        list.add(new item_quiz("54.A router is a special computer ………. messages when several networks are linked..",
                1, 0, 0, 0,
                "A. to direct     ",
                " B. directing   ",
                "C. directed   ",
                "D. directs"));
        list.add(new item_quiz("55. Each MP3 file has a tag ………... extra information to be stored on other track details.",
                1, 0, 0, 0,
                "A. allows  ",
                " B. to allow    ",
                "C. allowing  ",
                "D. allow"));
        list.add(new item_quiz("56. Although (A) networks allow (B) data to be shared; they (C)permit viruses to spread (D) quickly.",
                0, 1, 0, 0,
                "A. to encode",
                "B. encoding  ",
                "C. encoded ",
                " D. encodes"));
        list.add(new item_quiz("57.He(A) was quite @@@@ (B) when he heard (C) what had happened (D)..",
                0, 1, 0, 0,
                "A. when",
                " B. after    ",
                "C. until    ",
                " D. once "));
        list.add(new item_quiz("58.The robber gave (A) the victim with (B) a (C) hard blow (D).……….",
                0, 1, 0, 0,
                "A. smoke",
                " B. smoking  ",
                "C. to smoke ",
                " D. smokes"));
        list.add(new item_quiz("60.The contestant hitted @@@ (A) the target(B) with (C) great accuracy (D). ..",
                0, 1, 0, 0,
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

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, R2015.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}
