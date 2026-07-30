package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.TrueFalseItem
import com.Elkood.ling_en4.data.model.TrueFalseQuestion

object TrueFalseContent {

    private fun li(english: String, arabic: String, isTrue: Boolean) =
        TrueFalseItem(english, arabic, isTrue)

    private fun q(statement: String, correctAnswer: Boolean) =
        TrueFalseQuestion(statement, correctAnswer)

    // Ported verbatim from En4TrueFalseConstants.java (36 items; isTrue = image was ic_check_green).
    val listItems: List<TrueFalseItem> = listOf(
        li("Software from an ASP must be installed locally on a user's computer ", "يجب تثبيت البرنامج من ASP محليًا على كمبيوتر المستخدم", false),
        li("You need a high bandwidth connection to use an ASP service ", "تحتاج إلى اتصال نطاق ترددي عالي لاستخدام خدمة ASP", true),
        li("ASPs usually use their own storage space for customers ", "عادةً ما تستخدم ASPs مساحة التخزين الخاصة بها للعملاء", false),
        li("Using an ASP gives you more flexibility", "يمنحك استخدام ASP مرونة أكبر", true),
        li("An e-commerce business usually provides all of the required technology itself ", "عادة ما توفر شركة التجارة الإلكترونية كل التكنولوجيا المطلوبة نفسها", false),
        li("JPEG the most common compression system used for video ", "JPEG أكثر أنظمة الضغط شيوعًا المستخدمة للفيديو", false),
        li("P-frame only store the changes in the image", "P- الإطار فقط تخزين التغييرات في الصورة", true),
        li("here is always at least one P-frame between tow I-frame", "هنا دائما ما لا يقل عن واحد P- الإطار بين اثنين Iframe", true),
        li("B-frame store the complete picture information", "B- الإطار تخزين معلومات الصورة كاملة", false),
        li("There can only be one B-frame between each I and P-frame", "يمكن أن يكون هناك إطار B واحد فقط بين كل إطار I و P", false),
        li("There are typically about four P-frames between each I-frame", "يوجد عادة حوالي أربعة إطارات P بين كل إطار ", true),
        li("Most the work that an application does to prepare a message for sending over a network is not seen by the user", "معظم الأعمال التي يقوم بها التطبيق لإعداد رسالة لإرسالها عبر شبكة لا يراها المستخدم", true),
        li("ASCII Is always used to transmit a data", "يستخدم ASCII دائمًا لنقل البيانات", false),
        li("The encryption layer compressed the message", "ضغط طبقة التشفير الرسالة", true),
        li("The network layer keeps a copy of each packet until it arrives at the next node undamaged", "تحتفظ طبقة الشبكة بنسخة من كل حزمة حتى تصل إلى العقدة التالية غير تالفة", false),
        li("Analogue signal are used on ordinary telephone lines", "يتم استخدام إشارة تناظرية على خطوط الهاتف العادية", true),
        li("When a message arrives at its destination, it passes through the same seven network communication layers as when it was sent, but in reverse order", "عندما تصل الرسالة إلى وجهتها ، فإنها تمر عبر طبقات الاتصال الشبكي السبع نفسها كما كانت عند إرسالها ، ولكن بترتيب عكسي", true),
        li("Internet addresses are an integral part of the IP protocol.", "تعتبر عناوين الإنترنت جزءًا لا يتجزأ من بروتوكول IP.", true),
        li("Internet addresses can be written as a series of numbers.", "يمكن كتابة عناوين الإنترنت كسلسلة من الأرقام.", true),
        li("UDP software provides the final routing for data within the receiving system.", "يوفر برنامج UDP التوجيه النهائي للبيانات داخل نظام الاستقبال.", true),
        li("UDP recovers packets that aren't successfully delivered.", "يستعيد UDP الحزم التي لم يتم تسليمها بنجاح", false),
        li("TCP only works with packet-switched networks.", "يعمل TCP فقط مع شبكات تبديل الحزمة", false),
        li("TCP only works when it is combined with IP.", "يعمل TCP فقط عندما يتم دمجه مع IP.", false),
        li("Different mail systems transfer emails in different ways.", "تنقل أنظمة البريد المختلفة رسائل البريد الإلكتروني بطرق مختلفة.", true),
        li("IMAP4 requires more bandwidth than the other email protocols.", "يتطلب IMAP4 نطاقًا تردديًا أكبر من بروتوكولات البريد الإلكتروني الأخرى.", false),
        li("SMTP is used for sending emails from a PC to a server", "يستخدم SMTP لإرسال رسائل البريد الإلكتروني من جهاز كمبيوتر إلى خادم", true),
        li("SMTP delivers messages one at a time.", "يسلم SMTP رسائل واحدة في كل مرة.", false),
        li("SMTP does not allow a delivered message to be cancelled.", "لا يسمح SMTP بإلغاء الرسالة التي يتم تسليمها.", true),
        li("SMTP is only one of many protocols used to send mail between servers.", "SMTP هو واحد فقط من العديد من البروتوكولات المستخدمة لإرسال البريد بين الخوادم.", false),
        li("POP protocol allows the user to download one message at a time.", "يسمح بروتوكول POP للمستخدم بتنزيل رسالة واحدة في كل مرة.", false),
        li("HTML is no longer useful for creating webpages.", "لم يعد HTML مفيدًا لإنشاء صفحات الويب", false),
        li("SGML is more complex than XML.", "SGML أكثر تعقيدًا من XML.", true),
        li("XML files can only be used on Unix systems.", "لا يمكن استخدام ملفات XML إلا على أنظمة Unix.", false),
        li("XML files can only be read by browser programs.", "لا يمكن قراءة ملفات XML إلا عن طريق برامج المتصفح", false),
        li("HTML is a markup language", "HTML هي لغة الترميز", true),
        li("Internet searches will be better with XML files.", "ستكون عمليات البحث على الإنترنت أفضل مع ملفات XML", true),
    )

    // Ported verbatim from QuizActivity_True_false.java quizData (35 rows; col1 "true"/"false").
    val quizPool: List<TrueFalseQuestion> = listOf(
        q("Software from an ASP must be installed locally on a user's computer", false),
        q("You need a high bandwidth connection to use an ASP service ", true),
        q("ASPs usually use their own storage space for customers", false),
        q("Using an ASP gives you more flexibility", true),
        q("An e-commerce business usually provides all of the required technology itself", false),
        q("JPEG the most common compression system used for video ", false),
        q("P-frame only store the changes in the image", true),
        q("here is always at least one P-frame between tow I-frame", true),
        q("B-frame store the complete picture information", false),
        q("P-frame only store the changes in the image", true),
        q("There can only be one B-frame between each I and P-frame", false),
        q("There are typically about four P-frames between each I-frame", true),
        q("Most the work that an application does to prepare a message for sending over a network is not seen by the user", true),
        q("ASCII Is always used to transmit a data", false),
        q("The encryption layer compressed the message", true),
        q("The network layer keeps a copy of each packet until it arrives at the next node undamaged", false),
        q("Analogue signal are used on ordinary telephone lines", true),
        q("When a message arrives at its destination, it passes through the same seven network communication layers as when it was sent, but in reverse order", true),
        q("Internet addresses are an integral part of the IP protocol.", true),
        q("Internet addresses can be written as a series of numbers", true),
        q("UDP software provides the final routing for data within the receiving system.", true),
        q("Different mail systems transfer emails in different ways.", true),
        q("SMTP is used for sending emails from a PC to a server", true),
        q("SMTP does not allow a delivered message to be cancelled", true),
        q("HTML is a markup language", true),
        q("Internet searches will be better with XML files", true),
        q("UDP recovers packets that aren't successfully delivered.", false),
        q("TCP only works with packet-switched networks.", false),
        q("TCP only works when it is combined with IP", false),
        q("IMAP4 requires more bandwidth than the other email protocols.", false),
        q("SMTP delivers messages one at a time.", false),
        q("SMTP is only one of many protocols used to send mail between servers.", false),
        q("POP protocol allows the user to download one message at a time", false),
        q("XML files can only be used on Unix systems.", false),
        q("XML files can only be read by browser programs.", false),
    )
}
