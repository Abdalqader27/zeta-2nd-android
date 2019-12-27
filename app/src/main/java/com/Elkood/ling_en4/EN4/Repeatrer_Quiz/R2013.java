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

public class R2013 extends AppCompatActivity {
    private ViewGroup progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2013);
        recyclerView = findViewById(R.id.recycle_view2013);
        progressBar = findViewById(R.id.pro2013);
        ArrayList<item_quiz> list = new ArrayList<>();
        list.add(new item_quiz("1.the main idea of this passage is…….",
                0, 0, 0, 1,
                "A. mail format",
                "B. Mail messages",
                "C. Mail servers ",
                "D. Mail protocols "));

        list.add(new item_quiz("2. “STMP” stands for …. ",
                1, 0, 0, 0,
                "A. simple mail transfer protocol",
                "B. parallel ocean program ",
                "C. simple management transfer protocol ",
                "D. simple mail title protocols"));


        list.add(new item_quiz("3. . “POP” stands for…. ",
                0, 0, 0, 1,
                "A. Integrated mission analysis planning ",
                "B.  internet mail access protocol ",
                "C. post operation protocol  ",
                "D. post office protocol "));


        list.add(new item_quiz("4. “IMAP” stands for  ….",
                0, 1, 0, 0,
                "A. Integrated mission analysis planning",
                "B. internet mail access protocol ",
                "C. internet message application  ",
                " D. internet module allocation protocol."));

        list.add(new item_quiz(" 5.one of the following as an SMTP function  …. ",
                0, 0, 0, 1,
                "A. Retrieve mail   ",
                "B.  Delete mail messages",
                "C. Retrieve headers ",
                "D.  Sent Mail to servers  "));
        list.add(new item_quiz("6. the word “rigidly is close in meaning to .",
                1, 0, 0, 0,
                "A. Strictly ",
                "B. Softly",
                "C.Patiently",
                "D. Likely"));
        list.add(new item_quiz("7. the word “retrieval” is close in meaning to  ",
                0, 0, 0, 1,
                "A.relocation",
                "B.re-allocation ",
                "C.restructure",
                "D.recovery"));
        list.add(new item_quiz("     8. ……….is a set of code used for web pages …. .",
                0, 0, 1, 0,
                "A. XML ",
                "B. GUI ",
                "C. HTML ",
                "D. ATM"));
        list.add(new item_quiz("9. the part of address “www” refers to  …. ",
                0, 0, 0, 1,
                "A. domain",
                "B. directory path     ",
                "C. protocol prefix",
                "D. web service"));
        list.add(new item_quiz("10.the extension firm  stands for ",
                0, 0, 0, 1,
                "A.informative   ",
                "B.non-profit agency        ",
                "C. personal   ",
                "D.company"));

//arrived
        list.add(new item_quiz("11.……...is a network computer used for accessing a service on a server  ",
                0, 1, 0, 0,
                "A. Thin Hub",
                "B. Client",
                "C. Server",
                "D. Router "));
        list.add(new item_quiz("12) The Number of Channels used by an ISDN system is ................... ",
                0, 1, 0, 0,
                "A.two",
                "B. three",
                "C. four",
                "D. five"));
        list.add(new item_quiz("13) based services are very low-cost option when compared to other solution offering similar bandwidth.  ",
                0, 0, 1, 0,
                "A. ADSI",
                "B. ISDN",
                "C. DSI",
                "D. 3G"));
        list.add(new item_quiz("14)…………IS A 32-hit number identifying a node on an IP network ",
                1, 0, 0, 0,
                "A. Internet address ",
                "B. Gateway address  ",
                "C. Transmission protocol   ",
                "D.mail address "));
        list.add(new item_quiz("15) ………………….is a network contained within a small area ",
                0, 0, 0, 1,
                "A. LNB ",
                "B.  WAN",
                "C. WAP ",
                "D. LAN"));
        list.add(new item_quiz("16) ……………allows users to send emails and access information on the internet on a mobile",
                0, 0, 1, 0,
                "A. WAN ",
                "B. LAP",
                "C. WAP ",
                "D. LAN "));
        list.add(new item_quiz("17) a….. is an electronic device connecting all data cabling in a network",
                0, 1, 0, 0,
                "A. backbone    ",
                "B. hub       ",
                "C. bridge   ",
                "D. cable"));
        list.add(new item_quiz("18) …………is a data about data",
                1, 0, 0, 0,
                "A. Metadata   ",
                "B. beta data ",
                "C. Backup data  ",
                "D. Meta language "));
        list.add(new item_quiz("19)  ………...is a way of dealing with bandwidth problems when downloading Video from the internet ",
                0, 0, 0, 1,
                "A.  Buffering ",
                "B. connecting",
                "C. steaming   ",
                "D. streaming"));
        list.add(new item_quiz("20) ……... opens communication and keeps it straight among all nodes on the network",
                0, 0, 1, 0,
                "A. Transport layer    ",
                "B. data link layer",
                "C. the session layer",
                "D. Physical layer"));
        list.add(new item_quiz("21) ………. shows only drives and folders",
                0, 0, 0, 1,
                "A. Navigation paths ",
                "B. toggle bus",
                "C.  icon",
                "D. windows explorer"));
        list.add(new item_quiz("22) ………….is a common type of compressions used for video data",
                0, 0, 1, 0,
                "A. BMP ",
                "B. MID",
                "C. MPEG ",
                "D. MP3"));
        list.add(new item_quiz("23) ………………is a formation used for decompressing components of data stream ",
                1, 0, 0, 0,
                "A. algorithm ",
                "B.  Frame ",
                "C.  encryption",
                "D. decompression"));
        // yet
        list.add(new item_quiz("24)MP3 can provide information about itself in a code  black called ......... ",
                1, 0, 0, 0,
                "A.  tag  ",
                "B.  label                           ",
                "C.  frame",
                "D. layer"));
        list.add(new item_quiz("25)a facility for storing a large amount of information is  ",
                1, 0, 0, 0,
                "A. a data center ",
                "B. a call center",
                "C.data",
                "D. metadata"));
        list.add(new item_quiz("26) . a self-replicating program is ……..",
                0, 0, 1, 0,
                "A.an application                            ",
                "B. an OS",
                "C. a virus                                        ",
                "D. a website"));
        list.add(new item_quiz("27) a program which   $ dras songs from a CD to  a WAV file is a ………….  ",
                0, 1, 0, 0,
                "A. recorder ",
                "B. player ",
                "C. decoder",
                "D. Sipper "));
        list.add(new item_quiz("28. a………. adjust the space allocated for each pane   ",
                1, 0, 0, 0,
                "A. divider ",
                "B. splitter",
                "C.  scroller     ",
                "D. guideline"));
        list.add(new item_quiz("29.a device connected on the computer to receive ISDN signal called",
                0, 0, 0, 1,
                "A.  ADSI   ",
                "B.   DSI   ",
                "C.  ATM ",
                "D. TA"));
        /// not solved
        list.add(new item_quiz("30.The qualifications needed for being an operating system expert are called ",
                1, 0, 0, 0,
                "A. MCSA      ",
                "B.  MCSI   ",
                "C. MCT   ",
                "D. MCSD"));
        /// not solved
        list.add(new item_quiz("31. No one of the following companies developed Bluetooth exept",
                0, 1, 0, 0,
                "A. Sony Ericson  ",
                "B.  IBM  ",
                "C. Motorola",
                "D. L-G"));
        list.add(new item_quiz("32. A rectangle with equal sides is called…….",
                1, 0, 0, 0,
                "A. square  ",
                "B. menu   ",
                "C. library ",
                "D.circle"));
        list.add(new item_quiz("33. ……...means convert to meaningful data",
                0, 0, 0, 1,
                "A. Tenet  ",
                "B. tamper  ",
                "C. Imposter ",
                "D.Decipher"));
        list.add(new item_quiz("34. It is not a good idea to keep looking at strangers. You….stare at people",
                0, 0, 1, 0,
                "A. shall   ",
                "B. must   ",
                "C. shouldn’t",
                "D. should"));
        list.add(new item_quiz("35. If you spilled coffee on the keyboard you…...it ",
                0, 1, 0, 0,
                "A. will damage",
                "B. would damage",
                "C. would have damage ",
                "D. will have damage"));
        list.add(new item_quiz("36. When you ………… to a network, you have provided an ID",
                0, 1, 0, 0,
                "A. log on  ",
                "B. log in   ",
                "C. log for         ",
                "D. log out"));
        list.add(new item_quiz("37. If you are committed to …… frames on your site. you would better commit yourself to some extra work ",
                0, 0, 0, 1,
                "A.  use   ",
                "B. be using  ",
                "C. to be used    ",
                "D. using "));
        list.add(new item_quiz("38. A………. …………………the green family enter green in the surname box",
                0, 0, 0, 1,
                "A. Find",
                "B. Be found ",
                "C. To be found        ",
                "D. to find"));
        list.add(new item_quiz("39. Once the selection crltorig$, click on the find button ",
                1, 0, 0, 0,
                "A. has been found   ",
                "B. have been found     ",
                "C.  found         ",
                "D. was found"));
        list.add(new item_quiz("40. Each side of DVD can have two layers …an enormous capacity  ",
                0, 1, 0, 0,
                "A.  give       ",
                "B. giving",
                "C. to be given   ",
                "D. be giving     "));
        list.add(new item_quiz("41. If you double click the icon, it………the program ",
                1, 0, 0, 0,
                "A. will start  ",
                "B. would start   ",
                "C. must start     ",
                "D. to start  "));
        list.add(new item_quiz("42. A gateway is a device ………………dissimilar   networks communicate    ",
                0, 1, 0, 0,
                "A. to enable    ",
                "B.  enabling",
                "C. enabled  ",
                "D. was enabled"));
        list.add(new item_quiz("43. Avoid ……………access to PCs",
                0, 1, 0, 0,
                "A. give ",
                "B. giving ",
                "C. to be given     ",
                "D. be giving"));
        list.add(new item_quiz("44. NO, coffee in this lab .We……………drink coffee here.",
                0, 0, 0, 1,
                "A.can’t      ",
                "B. couldn’t",
                "C. may not    ",
                "D. mustn’t"));
        list.add(new item_quiz("45. …………...You listen to part one. part 2 is downloading ………….",
                1, 0, 0, 0,
                "A. as",
                "B. when ",
                "C. before  ",
                "D. until  "));
        list.add(new item_quiz("46.I……………like to make a course in multimedia ",
                0, 0, 1, 0,
                "A. will  ",
                "B. must",
                "C. would   ",
                "D. could"));
        list.add(new item_quiz("47. ATMs……………….Iris certainly……………...this recognition in the future  ",
                0, 1, 0, 0,
                "A. will use    ",
                "B. would use ",
                "C. must use ",
                "D. can use   "));
        list.add(new item_quiz("48. I recommend ………………Pc diagnlidon  tool to make it secure.",
                0, 0, 0, 1,
                "A. use     ",
                "B. to use",
                "C.uses   ",
                "D. using"));
        list.add(new item_quiz("49.  you can ………to secret code to make it secure",
                0, 1, 0, 0,
                "A. encrypt",
                "B. encode",
                "C. enable",
                "D. enhance"));
        list.add(new item_quiz("50. smart cards prevent unauthorized users……………systems  ",
                0, 0, 1, 0,
                "A. access ",
                "B. to access",
                "C. accessing ",
                "D. accessed"));
        list.add(new item_quiz("51. Hackers try to…………...passwords to penertrate systems ",
                0, 1, 0, 0,
                "A. break down  ",
                "B. break into",
                "C. log on  ",
                "D. find out"));
        list.add(new item_quiz("52. How do you…………. running this machine. ",
                0, 1, 0, 0,
                "A. go about ",
                "B. go around ",
                "C. go down   ",
                "D. go out"));
        list.add(new item_quiz("53. Calculate all sales ……….…there are no more ",
                0, 0, 0, 1,
                "A. while ",
                "B. until  ",
                "C. as",
                "D. when"));
        list.add(new item_quiz("54. You know main frames well. You …………for IBM before",
                0, 0, 1, 0,
                "A. must work ",
                " B.would have worked ",
                "C. must have worked ",
                "D. should have worked"));
        list.add(new item_quiz("55. You…………a degree in computer science. it is not important ",
                1, 0, 0, 0,
                "A. have not need ",
                "B. will be having",
                "C. do need   ",
                "D.must need"));
        list.add(new item_quiz("56. I would   like    taking a course in multimedia ",
                0, 0, 1, 0,
                "A. power",
                "B. like  ",
                "C. taking",
                " D. in"));
        list.add(new item_quiz("57. power consumption and cost    were very significance factors in Bluetooth design ",
                1, 0, 0, 0,
                "A.power ",
                " B. cost",
                "C. were",
                " D. significance"));
        list.add(new item_quiz("58. you   can   wide     the   picture in your monitor ……….",
                0, 1, 0, 0,
                "A. can",
                " B. wide  ",
                "C. the ",
                " D. picture"));
        list.add(new item_quiz("59.the Trainer  gives the trainee some  advices",
                1, 0, 0, 0,
                "A. Trainer",
                "B. gives",
                "C. trainee",
                "D. advices"));
        list.add(new item_quiz("60.the better thing to   do is to reinstall the sound drivers ",
                1, 0, 0, 0,
                "A. better",
                " B. to",
                "C. is",
                "D. to"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        Adapter_Quiz adapter_home = new Adapter_Quiz(list, R2013.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter_home);
        }, 4000L);
    }
}
