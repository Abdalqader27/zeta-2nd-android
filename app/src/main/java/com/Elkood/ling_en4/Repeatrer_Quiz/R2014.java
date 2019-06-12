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

public class R2014 extends AppCompatActivity {
    private LinearLayout progressBar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2014);
         recyclerView = findViewById(R.id.recycle_view2014);
        progressBar = findViewById(R.id.pro2014);
        ArrayList<item_quiz> list = new ArrayList<>();
        list.add(new item_quiz("1.The main idea in the paragraph is …….",
                1, 0, 0, 0,
                "A. GPS satellites ",
                "B. GPS for Military Uses",
                "C. GPS history	",
                "D. GPS development "));

        list.add(new item_quiz("2. In line 1, the word “GPS” stands for …..",
                1, 0, 0, 0,
                "A.Global positioning System",
                "B. Geographical Placement System",
                "C. Gravity Plating System",
                "D. Go Perfectly Straight"));


        list.add(new item_quiz("3.In Line 1 , the word “pinpoint” is close in meaning to …… ",
                0, 0, 1, 0,
                "A.calculate",
                "B. broadcast",
                "C. locate ",
                "D. design"));


        list.add(new item_quiz("4. The pronoun  ”it” refers to……",
                0, 0, 1, 0,
                "A.earth",
                "B. a satellites",
                "C. GPS",
                " D. a signal"));

        list.add(new item_quiz(" 5. In line 2, the word “broadcast” is close in meaning to…..",
                1, 0, 0, 0,
                "A. transmit",
                "B. receive",
                "C. locate",
                "D.calculate "));
        list.add(new item_quiz("6. In line 4, the word “altitude” is close in meaning to …",
                0, 0, 0, 1,
                "A. dimension	",
                "B. width",
                "C.length",
                "D. height"));
        list.add(new item_quiz("7. The purpose of the text is…",
                1, 0, 0, 0,
                "A.explanation",
                "B.comparison	 ",
                "C. contrast",
                "D. B and C"));
        list.add(new item_quiz(" 8. We can conclude from the text that GPS can be used for all the following EXCEPT …..",
                0, 0, 1, 0,
                "A. tracking buses ",
                "B. locating cars",
                "C. finding a hotel",
                "D. navigating ships"));
        list.add(new item_quiz("9.The microprocessor compares signals from …",
                1, 0, 0, 0,
                "A. Satellites",
                "B. The receiver ",
                "C.The truck",
                "D. a satellite "));
        list.add(new item_quiz("10. According to the text , we can conclude that to use GPS service, we need…",
                0, 0, 1, 0,
                "A. a location ",
                "B.a receiver ",
                "C.microprocessors  ",
                "D.a satellite"));

        //arrived

        list.add(new item_quiz("11.According to the text, we can conclude that GPS…",
                0, 0, 0, 1,
                "A.is not popular",
                "B.is not used anymore",
                "C.is only used by military",
                "D. has many  user "));



        list.add(new item_quiz("12) A …… is a hardware and software combination used to connect the same type of networks.",
                0, 0, 0, 1,
                "A. gatway",
                "B. modem",
                "C.router",
                "D. bridge"));
        list.add(new item_quiz("13) A …… is a powerful computer storing data shared by all the clients in the network. ",
                1, 0, 0, 0,
                "A. server ",
                "B. LAN",
                "C.bridge",
                "D.Hub"));
        list.add(new item_quiz("14)An email transfer process in which the connection is initiated by the sending computer is called …",
                0, 1, 0, 0,
                "A.messaging protocol ",
                "B.push operation ",
                "C. message retrieval ",
                "D. email "));
        list.add(new item_quiz("15) A … is a simple message transfer protocol used to send messages between servers.",
                0, 0, 1, 0,
                "A. ISP",
                "B. IMAP",
                "C.SMTP",
                "D.POP"));
        list.add(new item_quiz("16)stored information that is used to route data through a gateway is .....",
                0, 0, 0, 1,
                "A.internet address",
                "B. transmission protocol.",
                "C. resolution protocol ",
                "D. look-up table"));
        list.add(new item_quiz("17) A 32-bit number identifying a node on an IP network is called a/an ……",
                0, 1, 0, 0,
                "A. allocation ",
                "B.. Internet address ",
                "C. memory address",
                "D.bus address"));
        list.add(new item_quiz("18) Downloading a file from a server is an example of ……",
                0, 0, 1, 0,
                "A.. User Datagram protocol",
                "B.Internet protocol",
                "C.File Transfer Protocol",
                "D.Transmission Control Protocol"));
        list.add(new item_quiz("19). The ..... is the only part of a communication process that the user sees.",
                0, 1, 0, 0,
                "A.network layer",
                "B. application layer ",
                "C.physical layer ",
                "D. data-link layer"));
        list.add(new item_quiz("20) The …… encodes and sends the packets.",
                1, 0, 0, 0,
                "A.physical layer",
                "B. transport layer ",
                "C.session layer ",
                "D.presentation layer "));
        list.add(new item_quiz("21) …… is a compressed video frame known as a predicated frame.",
                0, 0, 0, 1,
                "A. 0-frame ",
                "B. I-frame ",
                "C. SGML",
                "D. P-frame"));
        list.add(new item_quiz("22) search engines do a better job because of the intelligent …… content.",
                0, 1, 0, 0,
                "A. HTML ",
                "B. XML ",
                "C. SGML ",
                "D. A and C"));
        //  not yet
        list.add(new item_quiz("23) Satellite communication is an example of …… ",
                1, 0, 0, 0,
                "A. Integrated networks",
                "B. cable networks ",
                "C. wireless connections ",
                "D. splitter"));
        //
        list.add(new item_quiz("24) WAP phones will ………. revolutionize the way we communicate.",
                0, 0, 0, 1,
                "A. Provable ",
                "B. importable ",
                "C. probable ",
                "D. probably"));
        list.add(new item_quiz("25) It is ….. that taxis will be robot- controlled. ",
                0, 0, 0, 1,
                "A. luckily ",
                "B. unlike",
                "C. liked",
                "D. likely "));
        list.add(new item_quiz("26) you cannot save the file …… You save it.",
                0, 0, 0, 1,
                "A. while ",
                "B. after ",
                "C. when",
                "D.until"));
        list.add(new item_quiz("27)If you spilled coffee on the keyboard, you …… it. ",
                0, 1, 0, 0,
                "A. Will damage ",
                "B. would damage",
                "C. would damaged ",
                "D. damaged "));
        list.add(new item_quiz("28. If you …….. print screen, you ….. a copy of the screen.",
                1, 0, 0, 0,
                "A. press / can make ",
                "B.pressed / could make ",
                "C. will press / will make  ",
                "D. press / would made "));

        list.add(new item_quiz("29. If there ….. a power cut while you were using the computer, you might lose unsaved data.",
                0, 0, 1, 0,
                "A. is ",
                "B. was  ",
                "C. were ",
                "D. has been"));
        list.add(new item_quiz("30. It is a good idea ……. A few large websites …… some ideas on designing an effective site map.",
                0, 0, 0, 1,
                "A. visit / gets  ",
                "B. will visit / will get 	",
                "C. visit / get ",
                "D. to visit / to get "));
        list.add(new item_quiz("31. Avoid …… A search function in your website as much as possible.",
                1, 0, 0, 0,
                "A.using ",
                "B. use 	",
                "C. used ",
                "D. to use "));
        list.add(new item_quiz("32. Once the DNS server …… the IP address, it sends it back to the browser.",
                0, 0, 1, 0,
                "A. to find  ",
                "B. will find ",
                "C. has found ",
                "D. found "));
        list.add(new item_quiz("33. Streaming is a way of dealing with bandwidth problems ……… you download video from the internet.",
                1, 0, 0, 0,
                "A. when",
                "B. after ",
                "C. until ",
                "D. once "));
        list.add(new item_quiz("34- Exercise in the morning rather than ……",
                0, 1, 0, 0,
                "A. smoke ",
                "B. smoking ",
                "C. to smoke ",
                "D. smokes "));
        list.add(new item_quiz("35.A olient is a network computer …… for accessing a service on a server.",
                0, 0, 0, 1,
                "A. use ",
                "B. to use ",
                "C. using ",
                "D. used "));
        list.add(new item_quiz("36.A router is a special computer ….. messages when several networks are linked.",
                0, 1, 0, 0,
                "A. to direct ",
                "B. directing ",
                "C. directed ",
                "D. directs "));
        list.add(new item_quiz("37. Each MP3 file has a tag ……… extra information to be stored on other track details.",
                0, 0, 1, 0,
                "A. allows 	",
                "B. to allow ",
                "C. allowing ",
                "D. allow "));
        list.add(new item_quiz("IV-Choose the letter underlined word that is NOT correct. \n" +
                "38.Regular programming (A) was interrupted (B) to  broadcast  (C) a special news bulletins (D).",
                0, 0, 0, 1,
                "A",
                "B",
                "C",
                "D"));
        list.add(new item_quiz("IV-Choose the letter underlined word that is NOT correct. \n" +
                "39.Plans (A) for both (B) the International Monetary Fund or (C) the World Bank were drawn up (D) at the Bretton Woods Conference.",
                0, 0, 1, 0,
                "A",
                "B",
                "C",
                "D"));
        list.add(new item_quiz("IV-Choose the letter underlined word that is NOT correct. \n" +
                "40.The Colorado River reaches (A) their (B) maximum height (C) during (D) April and May.",
                0, 1, 0, 0,
                "A",
                "B",
                "C",
                "D"));
        list.add(new item_quiz("IV-Choose the letter underlined word that is NOT correct. \n" +
                "41.Modern (A) art is on display (B) at the Guggenhein museum, a building (C) with an unusually (D) design.",
                0, 0, 0, 1,
                "A",
                "B",
                "C",
                "D"));
        list.add(new item_quiz("IV-Choose the letter underlined word that is NOT correct. \n" +
                "42. Although (A) networks allow (B) data to be shared they (C) permit viruses to spread (D) quickly.",
                0, 0, 1, 0,
                "A",
                "B",
                "C",
                "D"));
        list.add(new item_quiz("43.Metadata",
                0, 0, 0, 1,
                "A. Set boundaries for the beginning and end-of message.",
                "B. Mathematical calculations based on the content of data.",
                "C. A coding system used for structuring and formatting documents.",
                "D. Data about data."));



        list.add(new item_quiz("44. Pull operation ",
                0, 0, 0, 1,
                "A. stored information used to route data through a gateway.",
                "B. A formula used for decompressing components of a data stream.",
                "C. The main transmission path handling major data traffic connecting LANs together.",
                "D. A mail transfer process in which  the receiving computer initiates the  connection."));

        list.add(new item_quiz("45.Look-up table ",
                1, 0, 0, 1,
                "A. stored information used to route data through a gateway.",
                "B. A formula used for decompressing components of a data stream.",
                "C. The main transmission path handling major data traffic connecting LANs together.",
                "D. A mail transfer process in which  the receiving computer initiales the  connection."));
        list.add(new item_quiz("46.Bracketing ",
                1, 0, 0, 0,
                "A. Set boundaries for the beginning and end-of message.",
                "B. Mathematical calculations based on the content of data.",
                "C. A coding system used for structuring and formatting documents.",
                "D. Data about data."));

        list.add(new item_quiz("47.Algorithm",
                0, 1, 0, 0,
                "A. stored information used to route data through a gateway.",
                "B. A formula used for decompressing components of a data stream.",
                "C. The main transmission path handling major data traffic connecting LANs together.",
                "D. A mail transfer process in which  the receiving computer initiales the  connection."));

        list.add(new item_quiz("48. A backbone ",
                0, 0, 0, 1,
                "A. Set boundaries for the beginning and end-of message.",
                "B. Mathematical calculations based on the content of data.",
                "C. A coding system used for structuring and formatting documents.",
                "D. Main transmission path handling major data traffic."));
        list.add(new item_quiz("49.Checksum",
                0, 1, 0, 0,
                "A. Set boundaries for the beginning and end-of message.",
                "B. Mathematical calculations based on the content of data.",
                "C. A coding system used for structuring and formatting documents.",
                "D. Data about data."));
        list.add(new item_quiz("50.Markup Language ",
                0, 0, 1, 0,
                "A. Set boundaries for the beginning and end-of message.",
                "B. Mathematical calculations based on the content of data.",
                "C. A coding system used for structuring and formatting documents.",
                "D. Data about data."));
        list.add(new item_quiz("51.TA ",
                0, 0, 0, 1,
                "A.Target Area",
                "B.Technology Assessment",
                "C.Technical Assistance",
                "D.Terminal Adaptor "));
        list.add(new item_quiz("52. DSL ",
                1, 0, 0, 0,
                "A. Digital Subscriber Line ",
                "B. Document Summary List ",
                "C.Direct Swift Link ",
                "D.Direct Service Line "));
        list.add(new item_quiz("53.SMS",
                0, 1, 0, 0,
                "A.System Management ",
                "B.Short Message Service ",
                "C.School Management System ",
                "D.Stock market Simulation "));
        list.add(new item_quiz("54.POP ",
                0, 0, 1, 0,
                "A.point Of Purchase",
                " B.Plenty Of Parking ",
                "C. Post Office Protocol",
                "D. Point Of Presence"));
        list.add(new item_quiz("55.IP ",
                1, 0, 0, 0,
                "A. Internet protocol",
                " B.Intellectual Property",
                "C. Image Processing ",
                "D. Interaction Point "));
        list.add(new item_quiz("56. LAN",
                0, 0, 1, 0,
                "A. Live Action Network",
                "B. Local Available Names ",
                "C.Local Access Network ",
                " D. Learn Advisory Network"));
        list.add(new item_quiz("57. Web",
                0, 0, 0, 1,
                "A. Name",
                " B. Board  ",
                "C. Program  ",
                " D. Page "));
        list.add(new item_quiz("58.File ",
                1, 0, 0, 0,
                "A. Name",
                " B. Board  ",
                "C. Program  ",
                " D. Page "));
        list.add(new item_quiz("59.Bulletin",
                0, 1, 0, 0,
                "A. Name",
                " B. Board  ",
                "C. Program  ",
                " D. Page "));
        list.add(new item_quiz("60.Supervisor ",
                0, 0, 1, 0,
                "A. Name",
                " B. Board  ",
                "C. Program  ",
                " D. Page "));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, R2014.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}
