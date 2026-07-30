package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.model.ReferenceItem
import com.Elkood.ling_en4.data.model.ReferenceTopic

object ReferenceContent {

    private fun item(title: String, english: String, arabic: String, colorRes: Int) =
        ReferenceItem(title, english, arabic, colorRes)

    // Ported verbatim from Vocabulary.java addItem(...) calls (45 items).
    private val vocabulary: List<ReferenceItem> = listOf(
        item("Website", "Collection of related web pages \n ", "مجموعة من صفحات الويب ذات الصلة ", R.color.colorPrimary),
        item("Virus", "Self-replicating program.\n", "برنامج التكرار الذاتي.", R.color.colorPrimary),
        item("Office Suite", "Set of Standard Programs used in an Office.\n", "مجموعة من البرامج القياسية المستخدمة في مكتب.", R.color.colorPrimary),
        item("Bandwidth", "Capacity of a network connection.\n", "قدرة اتصال الشبكة.", R.color.colorPrimary),
        item("Broadband", "High Capacity of internet connection\n", "قدرة عالية على الاتصال بالإنترنت", R.color.colorPrimary),
        item("Data Center", "Facility for storing Large Amount of information\n", "مرفق لتخزين كمية كبيرة من المعلومات", R.color.colorPrimary),
        item("SAP", "Common Enterprise resource Planning tool \n ", "أداة تخطيط موارد المؤسسة الشائعة", R.color.colorPrimary),
        item("MIDI", "( Using reference works like encyclopedias.)  \n (  standard for interconnecting electronic musical instruments and computers.)  \n", "( باستخدام مرجع يعمل مثل الموسوعات )\n( .معيار لربط الآلات الموسيقية الإلكترونية وأجهزة الكمبيوتر) .\n", R.color.colorPrimary),
        item("Mp3", "Downloading Music from the internet. \n ", "تحميل الموسيقى من الإنترنت", R.color.color4),
        item("DVD", "Watching Movie \n ", "مشاهدة فيلم", R.color.color4),
        item("Algorithm", "Formula used for decompressing component of data stream. \n ", "الصيغة المستخدمة لإلغاء ضغط مكون دفق البيانات", R.color.color4),
        item("I-Frame", "Compressed video frame that contains the complete Image Information \n ", "إطار فيديو مضغوط يحتوي على معلومات الصورة الكاملة", R.color.color4),
        item("JPEG", "Type of compression used for Bitmap image. \n ", "نوع الضغط المستخدم لصورة نقطية", R.color.color4),
        item("P-Frame", "Compressed video frame known as Predicted Frame. \n ", "إطار فيديو مضغوط يعرف باسم الإطار المتوقع", R.color.color4),
        item("B-Frame", "Compressed video frame that stores Changes between the frame before it and the frame after it. \n ", "إطار فيديو مضغوط يخزن التغيرات بين الإطار الذي قبله و الإطار الذي بعده", R.color.color4),
        item("MPEG", "( Common type of Compression used for video data ) \n ( Composing music from the internet )\n ", "( نوع شائع من الضغط المستخدم لبيانات الفيديو ) \n ( تأليف الموسيقى من الإنترنت ) \n ", R.color.color4),
        item("Bracketing", "Set boundaries for the beginning and end of message \n ", "تعيين حدود لبداية و نهاية الرسالة", R.color.color4),
        item("Checksum", "Mathematical calculation based on the content of data \n ", "حساب رياضي يعتمد على محتوي البيانات", R.color.color4),
        item("Half-Duplex", "Transmission mode in which each computer takes turn sending and receiving \n ", "وضع النقال الذي يتحول فيه كل كمبيوتر إلى إرسال و استقبال", R.color.color4),
        item("Full-Duplex", "Transmission mode in which both computers send and receive at the same time \n ", "وضع النقل الذي يقوم كلا الجهازين بإرسال و استقباله في نفس الوقت", R.color.color4),
        item("IRC", "Chatting to other users in real-time. \n ", "الدردشة مع المستخدمين الآخرين في الوقت الحقيقي", R.color.color4),
        item("Moos", "Taking part in simulation in shared environment \n ", "المشاركة في المحاكاة في البيئة المشتركة", R.color.color4),
        item("E-Mail", "Sending and receiving message \n ", "إرسال و استقبال الرسائل", R.color.colorPrimary),
        item("FTP", "Downloading file from server. \n ", "تنزيل الملف من الخادم", R.color.colorPrimary),
        item("WWW", "Browsing web page \n ", "تصفح صفحة ويب", R.color.colorPrimary),
        item("Telnet", "Logging on to your computer at a distance \n ", "تسجيل الدخول إلى جهاز الكمبيوتر الخاص بك عن بعد", R.color.colorPrimary),
        item("Usenet", "Accessing web pages \n ", "الوصول إلى صفحة الويب", R.color.colorPrimary),
        item("Router", "Special computer that’s directs communications. \n ", "كومبيوتر خاص يوجه الاتصالات", R.color.colorPrimary),
        item("backbone", "Main transmission path handling major data traffic \n ", "مسار النقل الرئيسي التعامل مع حركة البيانات الرئيسية", R.color.colorPrimary),
        item("Internet Address", "A 32-bit number identifying anode on an IP network  \n ", "رقم 32 بت يحدد الأنود على شبكةIP", R.color.colorPrimary),
        item("Resolution Protocol", "Standard used for software that routes data through get way \n ", "المعيار المستخدم للبرامج التي تقوم بتوجيه البيانات من خلال الحصول على الطريق", R.color.colorPrimary),
        item("Look-up Table", "Stored information used to route data through get way. \n ", "المعلومات المخزنة المستخدمة لتوجيه البيانات من خلال الحصول على الطريق", R.color.colorPrimary),
        item("Get Way", "Device for connecting dissimilar networks. \n ", "جهاز لتوصيل شبكات متباينة", R.color.colorPrimary),
        item("User Datagram Protocol(UDB)", "Standard used by software that moves information to the correct application on the receiving system of a network. \n ", "معيار يستخدمه البرنامج الذي ينقل المعلومات إلى التطبيق الصحيح على نظام الاستقبال الخاص بالشبكة", R.color.colorPrimary),
        item("Transmission Control Protocol(TCP)", "Standard used by software that manage communication exchanges between computers on the internet \n ", "المعيار المستخدم بواسطة البرامج التي تدير تبادل الاتصالات بين أجهزة الكومبيوتر على الإنترنت", R.color.colorPrimary),
        item("ISMTP", "Simple Mail Transfer Protocol that is used to send message between server. \n ", "بروتوكول نقل بريد بسيط يستخدم لإرسال رسالة بين الخادم", R.color.colorPrimary),
        item("Push' Operation", "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer \n ", "عملية نقل بريد إلكتروني يتم فيها بدء الاتصال بواسطة جهاز الكومبيوتر المرسل بدلاً من جهاز الاستقبال", R.color.colorPrimary),
        item("Pull' Operation", "An E-Mail Transfer Process in which the receiving computer initiates the connection \n ", " عملية نقل بريد الكتروني يقوم فيها الكبيوتر المتلقي ببدء الاتصال", R.color.colorPrimary),
        item("POP", "A Message-Retrieval protocol that download all E-Mail messages at the same time \n ", "بروتوكول لاإستعادةالرسائل يقوم بتنويل جميع رسائل البريد الالكتروني في نفس الوقت", R.color.colorPrimary),
        item("IMAP", "Mail transfer protocol that initially only retrieves the message handers \n ", "بروتوكول نقل البريد الذي يسترد مبدئياً معالجات الرسائل فقط", R.color.colorPrimary),
        item("Metadata", "Data about Data \n ", "بيانات حول البيانات", R.color.colorPrimary),
        item("GMetalanguage", "Language from which you can create other Language. \n ", "اللغة التي يمكنك من خلالها إنشاء لغة أخرى", R.color.colorPrimary),
        item("HTML", "example of page presentation Language. \n ", "مثال على لغة عرض الصفحة", R.color.colorPrimary),
        item("XML", "extensible markup Language \n ", "لغة التوصيف الموسعة", R.color.colorPrimary),
        item("Markup Language", "coding system used for structuring and formatting documents. \n ", "نظام الترميز المستخدم لهيكلة وتنسيق الوثائق", R.color.colorPrimary),
    )

    // Ported verbatim from Abbreviations.java (25 items).
    private val abbreviations: List<ReferenceItem> = listOf(
        item("GPS", "Global Positioning System\n", "نظام تحديد المواقع العالمي", R.color.colorPrimary),
        item("XML", "eXtensible Markup Language\n", "لغة التوصيف الموسعة", R.color.colorPrimary),
        item("IMAP", "Internet Mail Access Protocol\n", "بروتوكول الوصول إلى بريد الإنترنت", R.color.colorPrimary),
        item("LAN", "Local Access Network.\n", "شبكة المنطقة المحلية.", R.color.colorPrimary),
        item("FTP", "File Transfer Protocol\n", "بروتكول نقل الملفات", R.color.colorPrimary),
        item("MP3", "MPEG Audio Layer 3\n", "MPEG أغنية طبقة 3", R.color.colorPrimary),
        item("TA", "Terminal Adapter\n", "محول المحطة", R.color.colorPrimary),
        item("DSL", "Digital Subscriber Line\n", "خط المشترك الرقمي", R.color.color4),
        item("SMS", "Short Message Server\n", "خادم الرسائل القصيرة", R.color.color4),
        item("POP", "Post Office Protocol\n", "بروتوكول مكتب البريد", R.color.color4),
        item("IP", "Internet Protocol\n", "بروتوكول إنترنت", R.color.color4),
        item("ASP ", "Application Server Providers\n", "مزودو خادم التطبيقات", R.color.color4),
        item("NIC", "Network Internet Card\n", "بطاقة شبكة الإنترنت", R.color.color4),
        item("WWW", "World Web\n", "الشبكة العالمية", R.color.color4),
        item("TCP", "Transmission Control Protocol\n", "بروتوكول التحكم بالإرسال", R.color.color4),
        item("SMTP", "Simple Mail Transfer Protocol\n", "بروتوكول نقل الإيميل البسيط", R.color.color4),
        item("UDP", "User Datagram Protocol\n", "بروتوكول مخطط المستخدم", R.color.color4),
        item("DNS", "Domain Name System\n", "نظام اسم المجال", R.color.color4),
        item("HTML", "Hyper Text Markup Language\n", "لغة ترميز النصوص التشعبية", R.color.color4),
        item("SGML", "Standard Generalized Markup Language\n", "لغة الترميز المعممة القياسية", R.color.color4),
        item("DTP", "Desktop Publisher\n", "الناشر المكتبي", R.color.color4),
        item("ISP", "Internet Server Provider.\n", "مزود خدمة الانترنت", R.color.color4),
        item("IRC", "Internet Relay Chat\n", "دردشة ترحيل الانترنت", R.color.color4),
        item("PIM", "Personal Information Manager\n", "مدير المعلومات الشخصية", R.color.colorPrimary),
        item("DVD", "Digital Video Disk\n", "قرص فيديو رقمي", R.color.colorPrimary),
    )

    // Ported verbatim from Compound_Nouns.java (20 items).
    private val compoundNouns: List<ReferenceItem> = listOf(
        item("Barcode", "Reader \n", "قارئ", R.color.colorPrimary),
        item("Mainframe", "Computer\n", "حاسوب", R.color.colorPrimary),
        item("Laser", "Printer\n", "طابعة", R.color.colorPrimary),
        item("Expansion", "Card\n", "بطاقة", R.color.colorPrimary),
        item("Search", "Engine\n", "محرّك", R.color.colorPrimary),
        item("Control", "Bus\n", "نقل", R.color.colorPrimary),
        item("Supervisor", "Program\n", "برنامج", R.color.colorPrimary),
        item("Task", "Bar\n", "شريط", R.color.color4),
        item("System", "Tray\n", "علبة", R.color.color4),
        item("Explorer", "Program\n", "برنامج", R.color.color4),
        item("Bulletin", "Board\n", "لوح", R.color.color4),
        item("Domain ", "Name\n", "اسم ", R.color.color4),
        item("File", "Name\n", "اسم", R.color.color4),
        item("Graphical", "Button\n", "زر", R.color.color4),
        item("Mobile", "Phone\n", "هاتف", R.color.color4),
        item("Search", "Engine\n", "محرّك", R.color.color4),
        item("Site", "Map\n", "خريطة", R.color.color4),
        item("Synchronous", "Transmissionn\n", "انتقال", R.color.color4),
        item("Text", "Message\n", "رسالة", R.color.color4),
        item("Web", "Page\n", "صفحة", R.color.color4),
    )

    // Ported verbatim from Extinsions.java (20 items).
    private val extensions: List<ReferenceItem> = listOf(
        item(".aero", "aviation industry \n", "صناعة الطيران", R.color.colorPrimary),
        item(".biz", "businesses \n", "الأعمال", R.color.colorPrimary),
        item(".com (.co in UK)", "commercial \n", "تجاري", R.color.colorPrimary),
        item(".coop", "cooperatives \n", "التعاونيات", R.color.colorPrimary),
        item(".edu (.ac in UK)", "educational and research \n", "التعليمية والبحثية", R.color.colorPrimary),
        item(".gov", "government\n", "الحكومي", R.color.colorPrimary),
        item(".info", "general use\n", "استخدام عام", R.color.colorPrimary),
        item(".int", "international organization \n", "منظمة عالمية", R.color.color4),
        item(".mil", "military agency \n", "وكالة عسكرية", R.color.color4),
        item(".museums", "museums\n", "المتاحف", R.color.color4),
        item(".name", "individuals\n", "الأفراد", R.color.color4),
        item(".net ", "gateway or host\n", "بوابة أو المضيف", R.color.color4),
        item(".org", "non-profit organization\n", "منظمة غير ربحية", R.color.color4),
        item(".pro", "Professionals\n", "المهنيين", R.color.color4),
        item(".firm", "Informative\n", "غنيا بالمعلومات", R.color.color4),
        item(".store", "Cultural or entertainment\n", "ثقافي أو ترفيهي", R.color.color4),
        item(".web", "Personal\n", "الشخصية", R.color.color4),
        item(".arts", "Film or agency\n", "فيلم أو وكالة", R.color.color4),
        item(".rec", "Online retail shop\n", "متجر التجزئة على الإنترنت", R.color.color4),
        item(".nom", "recreational\n", "ترفيهية", R.color.color4),
    )

    fun itemsFor(topic: ReferenceTopic): List<ReferenceItem> = when (topic) {
        ReferenceTopic.VOCABULARY -> vocabulary
        ReferenceTopic.ABBREVIATIONS -> abbreviations
        ReferenceTopic.COMPOUND_NOUNS -> compoundNouns
        ReferenceTopic.EXTENSIONS -> extensions
    }

    fun referenceTopicFromExtra(name: String?): ReferenceTopic =
        ReferenceTopic.entries.firstOrNull { it.name == name } ?: ReferenceTopic.VOCABULARY
}
