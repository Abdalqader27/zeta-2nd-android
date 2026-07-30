package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.MolakhsCard
import com.Elkood.ling_en4.data.model.MolakhsSummary

object MolakhsContent {

    private fun card(title: String?, english: String, arabic: String) =
        MolakhsCard(title, english, arabic)

    // Each summary's cards ported verbatim from fragment_munitN.xml android:text values, in layout order.
    val summaries: List<MolakhsSummary> = listOf(
        MolakhsSummary(
            "Unit 8 ",
            listOf(
                card(
                    "Application Service Providers",
                    "If your hard disk is packed to bursting point, the IT department is far too busy to fix your email problems, and your business can't afford to buy the tool that you'd like to develop the company website, then it's time to think about using an application service provider (ASP). Rather than installing software on each machine so server within your organization, you rent applications from the ASP which provides remote access to the software and manages the hardware required to run the applications.",
                    "إذا كان قرصك الصلب ممتلئ إلى حد الانفجار وقسم المعلومات أبعد بكثير من أن ينشغل بإصلاح مشاكل بريدك الإلكتروني. ولايمكن لقطاع عملك أن يتحمل نفقات شراء الأداة التي تريدها لتطوير موقع الويب للشركة عندما يكون قد حان الوقت للتفكير باستخدام موفر خدمة الإنترنت (ASP)\nبدلا من تثبيت البرنامج على كل جهاز أو خادم داخل مؤسستك. يمكنك استئجار التطبيقات من ASP ، التي توفر الوصول عن\nبعد إلى البرنامج وتدير الأجهزة المطلوبة لتشغيل التطبيقات.\n",
                ),
                card(
                    null,
                    "There are a lot of advantages to this approach. The havoc caused by viruses makes the idea of outsourcing your email and office suite services an attractive option. It also gives you more flexibility – you pay for applications as and when you need them, rather than investing in a lot of costly software which you'r then tied to for years.",
                    "هناك الكثير من المحاسن لهذه الطريقة، الفوضى التي تسببها تجعل من فكرة الاستعانة ببريدك الالكتروني وخدمات جناح ال office  كمصادر خارجية خياراً جذاباً.\nكما أنه يتيح لك المزيد من المرونة، كأن تدفع ثمن التطبيقات عندما تحتاجها بدلاً من أن تُحاط بالكثير من البرامج المكلفة، والتي تبقى مقيداً بها لسنوات.\n",
                ),
                card(
                    null,
                    "Not having to worry about upgrading to the latest version of the office suite about battling with the complexities of managing an email system, leaves business with more time. Time to focus on what they do best. However, there are some potential pitfalls. To use applications remotely requires a lot of bandwidth, which is only really available from a broadband connection or a leased line to the ASP itself. It is also important to ensure that the ASP will be able to provide a secure, reliable service which will be available whenever you need it.",
                    "لا داعي للقلق حول ترقية جناح الoffice  إلى إصدار أو حول المواجهة مع تعقيدات نظام إدارة البريد الإلكتروني، اترك العمل لكسب المزيد من الوقت. الوقت لتركيز على أفضل ما يمكنهم فعله. ومع ذلك هناك بعض المخاطر المحتملة. استخدام التطبيقات عن بعد يتطلب الكثير من عرض الحزمة. والذي لا يتوفر فعلاً إلا من اصال واسع النطاق أو خط مؤجر للASP نفسه. ومن المهم أيضا التأكد من أن ASP سوف يكون قادراً على توفير بيئة آمنة. وخدمة موثوق بها بحيث تكون متاحة كلما احتجت إليها. ",
                ),
                card(
                    null,
                    "Providing applications and storage space for vast numbers of users requires some powerful technology on the part of the ASP. This includes security controls and data storage as well as providing the physical links to customers. For the most part, ASPs don't own the data centers that store the information. Instead, they lease space from data storage specialists. In this way, they can be confident of meeting customers' increasing storage requirements by buying more space as it's needed.",
                    "التزويد بالتطبيقات وبمساحة تخزين الأعداد الكبيرة من المستخدمين يحتاج إلى بعض التقنية القوية من الناحية الASP. وهذا يشمل الرقابة الأمنية وتخزين البيانات. فضلاً عن توفير الوصلات الفيزيائية للمستخدمين. بالنسبة للجزء الأكبر. مزودات خدمات الانترنت لا تملك مراكز البيانات التي تخزن المعلومات . بدلاً من ذلك إنهم يستأجرون المساحة من المتخصصين في تخزين البيانات. وبهذه الطريقة، يمكن أن يكونوا واثقين من تحقيق متطلبات المستخدمين للتخزين التي تزداد باستمرار عن طريق شراء المزيد من المساحة عند الحاجة اليها.",
                ),
                card(
                    null,
                    "There's wide variety of applications available for use via ASPs. Office suite applications and email services are two of the most generic applications available through ASPs.",
                    "هناك طائفة واسعة من التطبيقات المتوفرة للاستخدام عبر مزودات خدمات الانترنت.\nتطبيقات جناح الoffice  وخدمات البريد الإلكتروني اثنان من أكثر التطبيقات العامة المتاحة من خلال مزودات خدمات الانترنت.",
                ),
                card(
                    null,
                    "Large, complex business applications such as enterprise resource planning tools like SAP are another popular candidate for delivery through an ASP. Other business services, such as payroll and accounting systems are also available. This is particularly beneficial to small businesses which are likely to grow quickly and don't want to deal with the problems caused by outgrowing their existing system and having to move to a high-end package.",
                    "تطبيقات الأعمال الكبيرة و المعقدة مثل أدوات التخطيط لموارد مؤسسة ك ASP  الخدمات التجارية الأخرى، مثل الرواتب ونظم المحاسبة متوفرة أيضاً. هذا مفيد بشكل خاص للشركات الصغيرة التي من المرجح أن تنمو بسرعة ولا ترغب في التعامل مع المشاكل التي يسببها النمو الفائق لنظامهم الحالي والحاجة إلى الانتقال إلى رزمة راقية.",
                ),
                card(
                    null,
                    "ASPs also offer a means of using specialist tools that would otherwise prove prohibitively expensive. Small businesses have the opportunity to use such tools for short periods of time as and when they need them, rather than having to buy the software as a permanent investment.",
                    "مزودات خدمة الانترنت تقدم أيضاً وسائل لاستخدام الادوات المتخصصة التي من شأنها أن تثبت أنها ليست باهظة الثمن الشركات الصغيرة لديها الفرصة لاستخدام هذه الأدوات لفترات قصيرة من الوقت عندما تجد أنها في حاجة إليها، بدلاً من الاضطرار إلى شراء البرمجيات كاستثمار دائم",
                ),
                card(
                    null,
                    "ASPs also offer a means of using specialist tools that would otherwise prove prohibitively expensive. Small businesses have the opportunity to use such tools for short periods of time as and when they need them, rather than having to buy the software as a permanent investment.",
                    "\nأحد العوائق الرئيسية للشركات الصغيرة التي تريد البدء في مجال التجارة الإلكترونية هو أن تَضْمنُ بأنّ لديهم مصادر كافية لتحمل الزيادات الكبيرة المفاجئة في عدد الزبائن.",
                ),
                card(
                    null,
                    "This means not only having adequate storage for all your customers' details, but ensuring that you have the technology in place to handle stock levels, efficient delivery and large volumes or traffic.",
                    "هذا لا يعني فقط أن يكون لديهم تخزين كافي لتفاصيل كافة الزبائن، ولكن أن تضمن بأن يكون لديك التقنية للتعامل مع مستويات المخزون، والتوزيع الكفوء، والكميات ضخمة أو المرور.(الاتصالات).",
                ),
                card(
                    null,
                    "It's very rare for an e-commerce business to handle all of these elements by itself, making this one of the best-established areas of ASP use",
                    "من النادر جداً لعمل التجارة الإلكترونية التعامل مع جميع هذه العناصر بنفسه، مما يجعل هذا واحدة من أفضل المجالات لتأسيس استخدام ASP",
                ),
                card(
                    null,
                    "Being able to respond rapidly to changes in the size of your customer base and the type of product that they want to order from your business, demands more flexibility than traditional software can provide.",
                    "أن تكون قادر على الاستجابة بسرعة للتغيرات في حجم قاعدة العملاء الخاصة بك ونوع المنتج الذي يريدونه هو أمر من أمور عملك. ويتطلب مزيد من المرونة التي يمكن للبرمجيات التقليدية أن تقدمها.",
                ),
            ),
        ),
        MolakhsSummary(
            "Unit 9 ",
            listOf(
                card(
                    "Understanding MP3",
                    "MP3  تتنافس مع تنسيق ملف سمعي آخر WAV\nالفرق الأساسي هو أن ملفات  MP3 أصغر بكثير من ملفات WAV\nيمكن لملفMP3  تخزين دقيقة من الصوت لكل ميغابايت في حين يحتاج ملف ال WAV  إلى 11  أو 12ميغابايت لحمل نفس الكمية.\nكيف الMP3  ينجز هذا الضغط؟ الأقراص المدمجة و الملفات الصوتية لا يعيدان إنتاج كل صوت الأداء.\nبدل من ذلك يختبرون الأداء ويخزنوا رمز منفصل لكل ملاحظة مختبرة، القرص المدمج أو الملف WAV  يختبران أغنية 44000  مرة بالثانية، خلق كتلة ضخمة من المعلومات.\nبالتجريد من الأصوات معظم الناس لا تسمع؟ MP3  يقلل بشكل ملحوظ من المعلومات المخزنة، على سبيل المثال،  فإن معظم الناس لا يستطيعون سماع الملاحظات فوق تردد 16 KHz  لذا يزيلهم من المزيج. وبالمثل، يزيل الأصوات الهادئة التي أخفت بواسطة الضجيج في نفس التردد. النتيجة هي أن الأصوات ملف مشابه جداً لقرص مضغوط CD ، ولكن الذي هو أصغر من ذلك بكثير . يمكن أن يحتوي ملف ال MP3  على عروض الكلمة المنطوقة مثل البرامج الإذاعية أو الكتب الصوتية وبالإضافة إلى الموسيقى، يمكن أن تزود معلومات عن نفسها في كتلة ترميز تسمى العلامة (tag), قد تتضمن اسم المؤدي ، الرسم مثل غلاف الألبوم ، كلمات الأغنية،  نوع الموسيقى، وعنوان  URL للمزيد من التفاصيل.",
                    "",
                ),
                card(
                    null,
                    "تشغيل ملفات MP3 ",
                    "معظم الاجهزة اليوم لديهم ما يكفي من قوة المعالجة والذاكرة لتشغيل ملفات MP3 على الفور. ببساطة تحميل ملف MP3 مثل أي ملف آخر واضغط عليه في مستكشف النوافذ (Windows explorer). Windows media player يفك الملف ويوجه الإشارات إلى كرت الصوت ومن ثم إلى السماعات",
                ),
                card(
                    null,
                    "Players",
                    "معظم المشغلات (players)المستقلين لهم ميزات كثيرة تتجاوز الافتراضي ويندوز ميديا بلاير. للسيطرة على الموسيقى التي تشغلها، يسمحوا لك المشغلات ((playersبجمع أغاني داخل قوائم التشغيل (playlist)وعشوائية الاختيارات. للسيطرة على هذه الأصوات الموسيقية، أنها توفر أجهزة تحليل الطيف، ورسم المعدلات وعروض التردد.",
                ),
                card(
                    null,
                    "Track info  تتبع المعلومات",
                    "زر معلومات المسار تعطيك معلومات عن العلامة (tag) في ملف MP3 . أزرار أخرى قد يأخذك إلى مكتبة الموسيقى حيث يمكنك تنظيم ملفات MP3 الخاص بك عن كريق الأداء أو النوع.",
                ),
                card(
                    null,
                    "Skins or themes",
                    "هذه البرامج مصممة لتغيير مظهر من اكثر المشغلات (players) شعبية.\nإنهم قريبين إلى ورق الجدران الذي يعدل نظرة مكتب النوافذ (Windows desktop) . مع الskin المشغل يستطيع أن يكون صندوق الموسيقى (jukebox) . لوحة قيادة (عدادات) السيارة (car dashboard) أو رحلة عبر النجوم (star trek tricorder) . اعتبرهم انهم قابلين للتبادل بسهولة think of them as easily interchangeable faceplates.",
                ),
                card(
                    null,
                    "Rippers and encoders:",
                    "Ripper هو برنامج يقطع الأغاني من القرص المدمج دخل السواقة ال (CD_ROM) ويحولهم إلى ملفات WAV . المرمز يحول ملفات ال WAV إلى ملفات MP3 والعكس بالعكس. يدمج المثير من مشغلات (players) الRippers و encoders ويمكن أن يعمل كلتا الخطوتين بخطوة واحدة.",
                ),
                card(
                    null,
                    "Recorders:",
                    "مع محرك CD_ROM للكتابة، المسجل هو برنامج يتيح لم بخلق الأقراص المضغوطة السمعية الخاصة بك",
                ),
                card(
                    "THE TRICKS TO MPEG'S SUCCESS",
                    "The most common system for the compression of video is MPEC. I works like this The single data stream off the CD-ROM is split into video and audio components, which are then decompressed using separate algorithms. The video is processed to produce individual frames as follows Imagine a sequence of frames depicting a bouncing ball on a plain background. The very first is called an Intra Frame (I- frame) L-frames are compressed using only information in the picture itself just like conventional bitmap compression techniques like JPEG..\nFollowing I-frames will be one or more predicted frames (P-frames). The difference between the P-frame and the I-frame it is based on is the only data that is stored for this P-frame\n",
                    "النظام الأكثر شيوعا لضغط الفيديو هو  MPEG .وهو يعمل هكذا. وخدة البيانات المتدفقة من قرص الذاكرة المدمج تقسم إلى مكونات الفيديو والصوت، والتي يتم بعد ذلك فك ضغطها باستخدام خوارزميات منفصلة.\nتتم معالجة الفيديو لإنتاج إطارات على النحو التالي، تخيل سلسلة من الإطارات التي تصور كره وثابة على خلفية بسيطة. ويطلق على أول إطار الإطار الداخل I-Frame.\nI-frame يضغط فقط باستخدام المعلومات الموجودة في الصورة نفسها مثل تقنيات ضغط خريطة البتات التقليدية ك JPEG . بالتالي الإطارات I-FRAME سيكون واحد أو أكثر من الإطارات المتنبأ بها والتي يرمز لها P-frame.\n",
                ),
                card(
                    null,
                    "\nFor example, in the case of a bouncing ball, the P picture is stored simply as a description of how the position of the ball has changed from the previous I-frame. This takes up a fraction of the space that would be used if you stored the P-frame as a picture in its own right. Shape or color changes are also stored in the P- frame. The next P-frame may also be based on this P-frame and so on. Only a few P-frames are allowed before a new I-frame is introduced into the sequence as a new reference point, since a small margin of error  creeps in with each P-frame.",
                    "على سبيل المثال،  في حالة وجود الكرة الوثّابة، يتم تخزين P picture  ببساطة كوصف لكيفية تغير موقع الكرة من\nI-frame  السابق . هذا يستغرق جزء من المساحة التي سيتم استخدامها إذا خزنت p-frame كصورة في حد ذاتها. تغيرات الشكل واللون يتم أيضاً  تخزينها في الإطار p-frame . P-frame القادم قد يكون أيضاً يعتمد على P-frame\nالحالي وهكذا. اختلافات التخزين بين الإطارات يعطي تخفيضاً هائلاً في كمية المعلومات الضرورية لإعادة إنتاج السلسلة فقط عدد قليل من إطارات  P-frame  مسموح بها قبل إدخال إطار I-frame  جديد في السلسلة كنقطة مرجع جديدة، لأن فرق صغير من الخطأ يتسرب مع كل إطار P-frame.\n",
                ),
                card(
                    null,
                    "الفرق بين P-frame  و I-frame  يقوم على أساس البيانات الوحيدة التي يتم تخزينها في هذا ال P-frame .",
                    "",
                ),
                card(
                    null,
                    "Between I and P-frames are bi-directional frames (B-frames), based on the nearest I or P-frames both before and after them. In our bouncing ball example. in a B-frame the picture is stored as the difference between the previous I or P- frame and the B-frame and as the difference between the B-frame and the following I or P-frame.",
                    "بين إطارات P-frame  و إطارات I-frame يوجد إطارات ثنائية الاتجاه B-frame ،تعتمد على I-frame أو\nP-frameالاقرب إليها سواء كانت قبلها وبعدها. في مثالنا الكرة الوثّابة، في الإطار ثنائي الاتجاه B-frame الصورة تخزن كالفرق بين إطارات P-frameأو إطارات I-frame السابقة وبين إطار B-frameوبين إطارات P-frame أو إطارات I-frame اللاحقة.\n",
                ),
                card(
                    null,
                    "To recreate the B-frame when playing back the sequence, the MPEG algorithm uses a combination of two references. There may be a number of B-frames between I or P-frames. No other frame is ever based on a B-frame so they don't propagate errors like P-frames Typically you will have two or three Bs between Is or Ps, and perhaps three to five P-frames between Is",
                    "\nلإعادة خلق الإطار ثنائي الاتجاه B-frame عند إعادة تشغيل السلسلة. خوارزمية MPEG تستخدم مزيج من (مرجعين/إشارتين). قد يكون هناك عدد من الإطارات ثنائية الاتجاه بين إطارات P-frame أو إطارات I-frame.ليس هناك أية إطارات أخرى تعتمد على الإطار ثنائي الاتجاه B-frame لذا فهي لا تنشر الأخطاء مثل إطارات P-frame . نموذجياً، سيكون لديك اثنين أو ثلاثة من الإطارات ثنائية الاتجاه بين إطارات P-frame أو إطارات I-frame ،ربما من ٣ إلى 5 من إطارات P-frame بين إطارات I-frame .",
                ),
            ),
        ),
        MolakhsSummary(
            "Unit 11 ",
            listOf(
                card(
                    "Wireless Networking",
                    "إن شركات الاتصال اللاسلكية WIFI ليست إلا شبكات اتصال محلية مثبّتة ولكن عوضا عن استخدام الأسلاك، ترتبط بواسطة الأمواج الراديويّة.\nكل حاسب في شبكة الاتصال اللاسلكي يتطلّب بطاقة واجهة الشبكة (كرت الشبكة).\nيمكن لهذه البطاقات ان تكون موجودة مسبقاً او يمكنك ان تربطها عبر مداخل خاصة. تسمح هذه البطاقات لكل مكوّن في الشبكة بأن يتصل مع نقطة الوصول اللاسلكي لينشئ شبكة اتصال محلية لاسلكية.\nإن نقطة الوصول تعمل كموجه في شبكة اتصال محلية مثبتة.\nانها تؤمن أيضاً جسر يمكن أن يوصل إلى (هاب) في شبكة اتصال محلية مثبتة ممكنة كلا المستخدمين (مستخدمي الشبكة المثبتة ومستخدمي الشبكة اللاسلكية) بالتحدّث مع بعضهم البعض.\nإذا كانت شبكة الاتصال المحلية الخاصة بك متصلة الى الانترنت، فإنّ شبكة الاتصال اللاسلكية يمكنها استخدامه أيضاً. وإلاّ (إن لم تكن متصلة) يمكنك أن توصل الشبكة اللاسلكية المحلية WLAN الى الانترنت عن طريق ال ADSL أو cable modem .\nماهي مزايا شلكة الاتصال اللاسلكية؟\nلا تحتاج الى اسلاك و كبلات. في المباني القديمة، كان من المكلف أن تركّب الأسلاك ونقطة الوصول. مع شبكات الاتصال اللاسلكية Wifi ، نقطة وصول واحدة يمكن ان تغطي كامل الأرضية وحتى المبنى. يمكنك أن تعمل في أي مكان ضمن مجال نقطة الوصول. في يوم مشمس، تستطيع العمل في الخارج. يمكنك صنع أي غرفة في المنزل من أجل الدراسة. يوجد الآن Wifi hotspots في الفنادق والمكتبات والمطارات وبذلك يمكنك الوصول للشبكة وأنت بعيد عن منزلك أو مكتبك.\nهناك مساوئ أيضاً:\nشبكات الاتصال اللاسلكية المثبتة يمكن أن تصل لسرعة 1000Mbps . شبكات الاتصال اللاسلكية أبطأ، والابتعاد عن نقطة الوصول يزيد البطأ أكثر. على الرغم من وجود حفاظ على الموارد (الاسلاك)، فإنّ بطاقات واجهة الشبكة أغلى من النسخة السلكية. وبذلك توجد مشكلة في الواجهة، إذا استعمل جار نفس القناة والحماية. ويمكن لمستخدمين أن يفسروا بيناتك. برامج التشفير مثل WEP يمكن ان تساعد في مثل ذلك.\n",
                    "",
                ),
                card(
                    "Network Communication",
                    "The application layer is the only part of a communications process that a user sees, and even then, the user doesn't see most of the work that the application does to prepare a message for sending over a network The layer converts a message's data from human-readable form into bits and attaches a header identifying the sending and receiving computers. ",
                    "(١) طبقة التطبيق هي الجزء الوحيد من عملية الاتصالات التي يراها المستخدم، وحتى ذلك الحين، المستخدم لا يرى معظم العمل الذي يقوم به التطبيق لإعداد رسالة لإرسالها عبر الشبكة.\nالطبقة تحول بيانات الرسالة من الشكل المقروء الإنسان إلى البتات وترفقها بترويسة للتعرف على أجهزة الارسال والاستقبال.\n",
                ),
                card(
                    null,
                    "(2) The presentation layer ensures that the message is transmitted in a language that the receiving computer can interpret (often ASCII) This layer translates the language, if necessary, and then compresses and perhaps encrypts the data. It adds another header specifying the language as well as the compression and encryption schemes. ",
                    "(٢) تضمن طبقة التقديم بأنّ تترجم الرسالة إلى لغة يمكن للحاسوب المستلم أن يترجمها (في أغل الأحيان اللاسلكي).\nهذه الطبقة تترجم اللغة إذا لزم الأمر، وبعد ذلك تضغط وربما تشفر البيانات. تضيف ترويسة أخرى تحدد اللغة، فضلاً عن مخططات الضغط والتشفير.",
                ),
                card(
                    null,
                    "(3) The session layer opens communications and has the job of keeping straight the communications among all nodes on the network. It sets boundaries (called bracketing) for the beginning and end of the message, and establishes whether the messages will be sent half-duplex, with each computer taking turns sending and receiving or full-duplex, with both computers sending and receiving at the same time. The details of these decisions are placed into a session header.",
                    "(3) طبقة الجلسة تفتح الإتصالات و تتولى مهمة الحفاظ على استمرار الاتصالات بين جميع العقد على الشبكة. تقوم بتعيين الحدود (وتسمى الأقواس) لبداية ونهاية الرسالة، وتحدد فيها إذا كان سيتم إرسال رسائل أحادية الاتجاه، بحيث أن كل حاسوب يتناوب بالإرسال والاستقبال، أو رسالة مزدوجة الاتجاه، بحيث كلا الحاسوبين يرسل ويستقبل في نفس الوقت. يتم وضع تفاصيل هذه القرارات في رأس الجلسة.",
                ),
                card(
                    null,
                    " (4) The transport layer protects the data being sent . It subdivides the data into segments, creates checksum tests mathematical sums based on the contents of data - that can be used later to determine if the data was scrambled It can also make backup copies of the data. The transport header identifies each segment's checksum and its position in the message.",
                    "(٤) تحمي طبقة النقل البيانات المرسلة. فهي تقسم البيانات إلى أجزاء، تقوم بإجراء اختبارات المجموع - المجاميع الرياضية تعتمد على محتويات الرسالة – التي يمكن أن تستخدم لاحقاً لتحديد فيها إذا كانت البيانات ممزوجة (تذكرة: المزج يوفر التزامن دون زيادة عدد البتات).\nيمكن أيضاً إجراء نسخ احتياطية للبيانات. ترويسة النقل تحدد اختبار المجموع لكل جزء و موقعه في الرسالة.\n",
                ),
                card(
                    null,
                    "(5) The network layer selects a route for the message. It forms data into packets, counts them, and adds a header containing the sequence of packets and the address of the receiving computer",
                    "\n(٥) طبقة الشبكة تختار طريقاً للرسالة، وهي تحول البيانات إلى حزم، تحسبها، وتضيف ترويسة تحتوي على سلسلة من الحزم على عنوان الحاسوب المستقبل.",
                ),
                card(
                    null,
                    " (6)The data-link layer supervises the transmission. It confirms the checksum, then addresses and duplicates the packets. This layer keeps a copy of each packet until it receives confirmation from the next point along the route that the packet has arrived undamaged.",
                    "(٦) طبقة وصلة البيانات تشرف على الإرسال. وهي تؤكد اختبار المجموع، ثم تعنون وتنسخ الجزء. هذه الطبقة تحتفظ بنسخة من كل حزمة إلى أن تستلم التأكيد من النقطة التالية عبر الطريق بأنه قد تم وصول الحزمة بدون أي أضرار.",
                ),
                card(
                    null,
                    "(7) The physical layer encodes the packets into the medium that will carry them - such as an analogue signal, if the message is going across a telephone line - and sends the packets along that medium.",
                    "(٧) الطبقة الفيزيائية ترمّز الحزم في الوسط الذي ستُحمل إليه_ مثل الإشارة التماثلية، إذا كانت الرسالة تذهب عبر خط الهاتف _وترسل الحزم على هذه الوسط.",
                ),
                card(
                    null,
                    "(8) An intermediate node calculates and verifies the checksum for each packet. It may also reroute the message to avoid congestion on the network.",
                    "(٨) عقدة وسيطة تقوم بحساب والتحقق من اختبار المجموع من أجل كل حزمة. وقد تعيد توجيه الرسالة لتجنب الازدحام على الشبكة.",
                ),
                card(
                    null,
                    "(9) At the receiving node, the layered process that sent the message on its way is reversed. The physical layer reconverts the message into bits. The data-link layer recalculates the checksum, confirms arrival, and logs in the packets. The network layer recounts incoming packets for security and billing purposes The transport. layer recalculates the checksum and reassembles the message segments. The session layer holds the parts of the message until the message is complete and sends it to the next layer. The presentation layer expands and decrypts the message. The application layer converts the bits into readable  characters, and directs the data to the correct application.",
                    "(٩) عند عقدة الاستلام، العملية ذات الطبقات التي أرسلت الرسالة على طريقها تصبح معكوسة.\nالطبقة الفيزيائية تعيد تحويل الرسالة إلى بتات. طبقة وصلة البيانات تعيد حساب اختبار المجموع، تؤكد وصوله، وتسجلها في الحزم. طبقة الشبكة تعيد حساب الحزم القادمة لأغراض الأمنية والمحاسبة. طبقة النقل تعيد حساب اختبار المجموع وتعيد تجميع أجزاء الرسالة. طبقة الجلسة تحمل أجزاء من الرسالة حتى الانتهاء من كتابة الرسالة، وترسلها إلى الطبقة التالية. طبقة التقديم توسع وتفك شيفرة الرسالة. طبقة التطبيق تحول البتات إلى محارف مقروءة، وتوجه البيانات إلى التطبيق الصحيح.\n",
                ),
            ),
        ),
        MolakhsSummary(
            "Unit 12 ",
            listOf(
                card(
                    "How TCP/IP Links Dissimilar Machines",
                    "At the heart of the Internet  protocol(IP) portion of TCP/IP is a concept called the Internet address. This 32 bit coding system assigns a number   to every node on the network  . There are various types of addresses  designed for networks of different sizes, but you can write every address with a series of numbers that identify the major network and the sub – networks to which a node is attached. Besides identifying a node, the             address provides a path that gateway can use to route information from one machine to another,",
                    "في قلب جزء من معاهدة الانترنت )الملكية الفكرية   ( من بروتوكول برنامج التعاون الفني  يوجد مفهوم يدعى عنوان الانترنت وهو نظام ترميز ب 32 عقدة يشير الى عدد كل   عقدة من شبكة الانترنت . وهناك نماذج متعددة من العناوين المصممة للشبكات وبحجوم مختلفة ، ولكن يمكنك ان تكتب كل عنوان مع سلسلة من الأرقام التي تعرف الشبكة الرئيسية والشبكات  الفرعية التي ترتبط الى عقدة . بالإضافة الى تعريف للعقدة فإن العنوان يزود بمسار بحيث يمكن ان تستخدم البوابات لتسيير المعلومات من الة الى أخرى ",
                ),
                card(
                    null,
                    ".   Although data-delivery systems like Ethernet or X.25 bring their packets\nTo any machine electrically attached to the cable, the IP modules must\nKnow each other’s Internet addresses if they are to communicate. A\nmachine acting as a gateway connecting different TCP/IP networks will\nhave a different Internet address on each network. Internet look _up\ntables and software based on another standard-called Resolution protocol- are used to route the data through a gateway between networks.\n",
                    "ورغم ان أنظمة تسليم البيانات كالايثرنت او     تحضر حزمها كهربائيا اللا أي جهاز مرتبط الى الكابل ، فإنه يجب على معايير   ان تعرف عناوين الانترنت بعضها ببعض اذا كانت على  اتصال. ان الجهاز الذي يعمل كبوابة ربط لشبكات المختلفة سيكون له عنوان انترنت",
                ),
                card(
                    null,
                    "9-use an IC extraction tool; don’t use a screwdriver. ",
                    "استخدم أداة استخراج استخراج    ولا تستخدم مفك براغي",
                ),
                card(
                    null,
                    "10-Always ensure the power is switched off when working on a\nComputer.\n",
                    "تأكد دائما من فصل الطاقة عند العمل على الكمبيوتر  ",
                ),
                card(
                    null,
                    "إن نظام تسليم البيانات كالايثرنت لا يبشر بتسليم حزم المعلومات بنجاح.\nلا IP ولا حتى UDP يعرفان أي شيء عن استعادة الحزم التي لا تسلم بشكل ناجح. لكن بنىTCPتنظم وتحجز تدفقات البيانات وتبحث عن استجابات وتتخذ الإجراءات لتحل محل كتل البيانات و مفهوم إدارة البيانات هذا يدعى خدمة الجريان الاستمرار الموثوق بها.\n",
                    "",
                ),
                card(
                    null,
                    "After TCP brings the data packet into a computer, other high-level programs handle it . some are enshrined in official US government standards, like the file transfer protocol (FTP) and the Simple Mail Transfer Protocol (STMP) . if you use these standard protocols on different kinds of computers, you will at least have ways of easily transferring files and other kinds of data.",
                    "بعد أن يأتي TCP بحزم البيانات إلى الكمبيوتر تقوم البرامج الأخرى ذات المستوى العالي بمعالجتها وبعضها يكون منصوصا عليها في المعايير الحكومية الرسمية في الولايات المتحدة مثل بروتوكول تحويل الملف FTP و بروتوكول تحويل الميل البسيط STMP .إذا استخدمت هذه البروتوكولات القياسية بأنواع مختلفة من أجهزة الحاسب , فإنك على الأقل ستملك طرق تحويل الملفات و الأنواع الأخرى من البيانات بسهولة.",
                ),
                card(
                    null,
                    "Conceptually, software that supports the TCP protocol stands alone. it can work with data received through a serial port, over a packet-switched network, or from a network system like Ethernet. TCP software doesn’t\nNeed to use IP or UDP, it doesn’t even have to know they exist. but in practice TCP is an integral part of the TCP\\IP picture, and it is most frequently used with those two protocols.\n",
                    "وبشكل توضيحي وإن البرامج التي تدعم TCP تكون مستقلة، ويمكنها أن تعمل مع البيانات المستلمة خلال المنفذ التسلسلي، عبر شبكة اتصال حزمة التشغيل أو من نظام الشبكة كالإيثرنت. إن برامج TCP لا يحتاج لاستخدام IPأو UDP , عليه حتى أن لا يعرف أنها موجودة. ولكن عمليان يكون تطبيق TCP  هو جزء متمم لشكل TCP\\IP , وهو غالبا ما يستخدم مع هذان البروتوكولانز",
                ),
            ),
        ),
        MolakhsSummary(
            "Unit 13 ",
            listOf(
                card(
                    "Email protocols ",
                    "Although the format of a mail message, as transmitted from one machine to another, is rigidly define, different mail protocols transfer and store messages in slightly different ways. The mail system you’re probably used to employs combination of STMP and POP3 to send or receive mail respectively. Others may use IMP4 to receive mail, especially where bandwidth is limited or expensive.",
                    "رغم أن صياغة شكل رسالة البريد الالكتروني عندما ترسب من جهاز إلى آخر معروفة تماما فإن اتفاقيات الايميل المختلفة وتخزن الرسائل بطرق مختلفة تماما. إن نظام البريد الذي من الممكن أن يستخدم لتوظيف.      لتلقي البريد، I MP4 لإرسال وتلقي البريد على الترتيب. الآخرون قد يستخدمون  POP3 وSTMPمجموعة من\nوبخاصة عندما تكون كمية البيانات محدودة أو غالية الثمن.\n",
                ),
                card(
                    "Simple Mail Transfer Protocol",
                    "STMP is used to transfer messages between one mail server and another. It’s also used by mail programs on PCs to send mail to the\nServer. STMP is very straightforward, providing only facilities to deliver messages to one or more recipients in batch mode. Once a message has been delivered, it can’t be recalled or cancelled. It’s also deleted from the sending server once it’s been delivered. SMPT uses ‘push’ operation, meaning that the connection is initiated by sending server rather than the receiver. This makes it unsuitable for delivering messages to desktop PCs, which aren’t guaranteed to be switched at all times.",
                    "اتفاقية تحويلالبريد العادي \n\n لتحويل الرسائل بين خادم بريد وآخر. وهو يستخدم أيضا عن طريق برامج الايميل على STMPيستعمل\n هي صريحة جدا، وهي تزود فقط بالتسهيلات لاستلام الرسائل إلى STMP الحواسيب الشخصية لإرسال البريد إلى الخادم.\nواحدة أو أكثر من المستلمين بالنموذج التسلسلي. حالما تصل الرسالة، فإنه لا يمكن أن يذكر أو يلغى. وهي أيضا تحذف من\n يستخدم عملية الدفع، ويعني ذلك أن الاتصال يبدأ عن طرق ملقم الإرسال بدلا من STMPالملقم المرسل حالما يتم استلامها.\nالمتلقي، وهذا يجعله غير مناسب لتسليم الرسائل إلى سطح المكتب في الحواسيب الشخصية والتي تكون مضمونة لكي تنقل في كل الأوقات.\n",
                ),
                card(
                    null,
                    "In host-based mail system, such as Unix and Web mail, SMPT is the only protected the server uses. Received messages are stored locally and retrieved from the local file system by mail program. In the case of Web mail, the message is then translated into HTML and transmitted to your browser. SMPT is the only protocol for transferring messages between server. How they’re then stored varies from system to system.",
                    "\n   هي الاتفاقية الوحيدة التي يستخدمهاSMPT، إنWebوالويب Unix في المضيف –وبالاعتماد على أنظمة البريد، مثل بريد\nالملقم. تخزن الرسائل المستلمة محليا ويسترجعها من نظام الملف المحلي عن طريق برنامج البريد. في حالة شبكة  الويب,\n هي الاتفاقية الوحيدة لتحويل الرسائل بين الملقمات. كيف SMPT  وترسل إلى المتصفح لديك.HTML فإن الرسالة تتحول إلى\nتخزن بعدها التغيرات من نظام إلى آخر.",
                ),
                card(
                    "Post Office Protocol",
                    "POP is a message-retrieval protocol used by many PC mail clients to get message from a server, typically your ISP’s mail server. it only allows you to download all message in your mailbox at once. It works in ‘pull’ mode, the receiving PC initiating the connection. PC-based POP3 mail clients can do this automatically at a present interval. When you use your Web mail account to access a POP3 mailbox, the mail server opens a connection to the POP3 server just as a PC-based application would. The messages are then copied into your Web mailbox and read via a browser.",
                    " اتفاقية استعادة الرسائل المستخدمة عن طريق الكثير من مسجلي البريد في الحاسب الشخصي للحصول على pop\nرسائلهم من الملقم، نموذجيا ملقم البريد لمزود خدمات الانترنت: أنه يسمح فقط بتحميل كل الرسائل في صندوق البريد في الحال. وهو يعمل بنظام سحب البيانات، الحاسب الشخصي المرسل البادئ بالاتصال. ان الحاسب الشخصي المعتمد على مسجلي\n يمكن أن يقوموا بذلك أوتوماتيكيا في اللحظة نفسها. عندما تستخدم حساب البريد الالكتروني للوصول إلىPop3البريد\n فقط كما لو كان حاسبا شخصيا معتمدا على التطبيق. ينسخ بعدها POP3فإن ملقم البريد يفتح الاتصال لملقم POP3 البريد\nالرسائل في صندوق البريد الالكتروني وتقرأ بواسطة المتصفح\n",
                ),
                card(
                    null,
                    "Since POP3 download all the messages in your mailbox there’s an option to leave messages on the server, so that they can be picked up from different machines without losing any. This does mean that you’ll get every message downloaded every time you connect to the server. If you don’t clean up your mailbox regularly, this could mean long downloads. When using a Web mail account to receive POP3 mail, be careful about leaving message on the server – if too many build up, each download will take a long time and fill up your inbox. Many Web mail systems won’t recognize messages you’ve already downloaded, so you’ll get duplicates of ones you haven’t deleted.",
                    "منذ تنزيل POP3 لجميع الرسائل الموجودة في صندوق البريد الخاص بك ، هناك خيار لترك الرسائل على الخادم ، بحيث يمكن التقاطها من أجهزة مختلفة دون فقد أي. وهذا يعني أنك ستحصل على كل رسالة يتم تنزيلها كل إذا كنت لا تقوم بتنظيف صندوق البريد الخاص بك بانتظام ، فقد يعني ذلك تنزيلات طويلة.عند استخدام حساب بريد ويب لتلقي بريد POP3 ، احرص على ترك رسالة على الخادم - إذا كان هناك عدد كبير جدًا من الإنشاءات ، سيستغرق كل تنزيل وقتًا طويلاً ويملأ البريد الوارد الخاص بك. لن تتعرف العديد من أنظمة بريد الويب على الرسائل التي قمت بتنزيلها بالفعل ، وبالتالي ستحصل على نسخ مكررة من تلك التي لم تقم بحذفها. ",
                ),
            ),
        ),
        MolakhsSummary(
            "Unit 14 ",
            listOf(
                card(
                    "XML Takes on HTML",
                    "Standard Generalized Markup Language (SGML) is the language that spawned both HTML(Hyper Text Markup Language ) and XML(Extensible Markup Language ).SGML is not a true language , it is a metalanguage, which is a language from which you can create other language . In this case, it is the creation of a markup language (a system of encoded instructions for structuring and formatting electronic document elements ).",
                    "إن اللغة المعممة القياسية الخاصة بتوصيف النص (SMGL ) هي تلك اللغة التي تنتج كلا من HTML (لغة الانترنت التوصيفية ) و XML (اللغة التوصيفية القابلة للتبسيط للامتداد).\nإن SGML ليست لغة حقيقية وهي لغة مركبة حيث يمكن ابتكار لغات اخرى منها .في هذه الحالة هي عبارة عن خلق للغة توصيف النص (نظام من التعليمات المشفرة لبناء وتنسيق عناصر المستند الالكترونية )\n",
                ),
                card(
                    null,
                    "HTML is an application-specific derivation of SGML . it is a set of codes , generally used for webpages, that creates electronic documents according to rules established by SGML. HTML is a\n language that is all about the presentation of your information , not what the actual data is. You can ,therefore, say  that HTML is a presentation language .",
                    "\nHTML هي اشتقاق تطبيق محدد عن  SGML فهي مجموعة من الرموز التي تستخدم بشكل عام لصفحات الويب التي تنشئ مستندات الكترونية بحسب القواعد المبنية باستخدام SGML . إن HTML هي اللغة التي كلها تدور حول عرض معلوماتك وليست عن البيانات الفعلية .لذا فإنه بإمكانك أن تقول أن HTML  هي لغة عرض .",
                ),
                card(
                    null,
                    "XML is a subset of SGML , but it is also , like SGML , a metalanguage .\nXML defines a specific method for creating text formats for data so that files are program independent platform independent , and support internationalization (able to read different languages , etc. ). In fact , because XML is an extensible language , you don't even have to have a browser to interpret the page . Application can parse the XML document and read the information without any human intervention .",
                    "XML هي مجموعة فرعية من SGML ولكنها تشبه SGML أيضاً، اللغة المركبة. تعرف XML طريقة محددة لإيجاد ولأن XML هي لغة قابلة للامتداد فلا يتحتم عليك أن يكون لديك متصفحاً لترجمة الصفحة . يمكن للتطبيقات أن تحلل وثيقة XML وتقرأ المعلومات بدون أي تدخل بشري.",
                ),
                card(
                    null,
                    "XML, unlike HTML , is concerned with the identity , meaning and structure of data . XML is extensible because it lets website developers create their own set of customized tags for documents . This ability to define your own tags is the main feature of XML , and it is what gives developers more flexibility .",
                    "XML على نقيض HTML تهتم تتعلق بتطابق ، ومعنى وبنية البيانات .XML قابلة للامتداد لأنها تفسح المجال لمطوري المواقع الالكترونية بخلق مجموعة من الاقتباسات البيانية حسب الطلب من أجل المستندات الوثائق . وهذه القدرة على تعريف الاقتباسات الخاصة بك هي الميزة الرئيسية ل XML ، وهي ما يمنح المطورون مرونة أكثر",
                ),
                card(
                    null,
                    " By defining your own markup tags ,you can explicitly define the content in the document . This makes XML a more intelligent markup language than HTML . For example, in HTML , you could have a paragraph tag P preceding a paragraph about baseball. Your web browser sees this tag and know to present the following text as a paragraph . All your browser knows about the text , however, is that it is text , it doesn't know that it is specifically about baseball.",
                    "وبتعريف اقتباسات توصيف النص ، بإمكانك أن تعرف بشكل واضح المحتوى في الوثيقة المستند. وهذا يجعل من XML لغة توصيفية أكثر ذكاء من HTML ،يمكن أن يكون لديك اقتباس خاص بفقرة P تسبق فقرة عن البيسبول . إن كتصفح الويب لديك يرى هذا الاقتباس ويعرفها ليحضر النص التالي كفقرة . إن كل متصفحك يعرف حول النص ، ولكن هل هو نفسه ذاك النص ، فهو لا يعرف بأنه عن البيسبول بالتحديد.",
                ),
                card(
                    null,
                    "In an XML document , you could define a BASEBALL tag to refer specifically to the text in the paragraph in your document . This way , when your XML browser examines the document, the document knows what data it contains, and that makes the content more intelligent . Search engines that make use of XML data can do a better job of finding the pages you are looking for because of the intelligent nature of XML content.",
                    "في مستند XML ، يمكنك أن تعرف البيسبول لتشير بشكل واضح الى نص في الفقرة في مستندك. وهذه الطريقة عندما يفحص متصفحك XML المستند، فالمستند يعرف البيانات التي بداخله وهذا يجعل من المحتوى أكثر ذكاء. أن المحركات التي تستخدم بيانات XML يمكن أن تقوم بعمل أفضل بإيجاد الصفحات التي تبحث عنها بسبب الطبيعة الذكية لمحتوى XML.",
                ),
                card(
                    null,
                    "XML, by design , does not deal with how the data is displayed to the end user . Because HTML is a presentation language ,XML documents use HTML tags to help handle the visual formatting of the document. Also, you can use XML in your HTML documents to provide metadata ,which is data about data in the document.",
                    "XML،بالتصميم لا تتعامل مع كيفية عرض البيانات للمستخدم النهائي. لأن HTML هي لغة عرض، ان مستندات XML تستخدم اقتباسات HTML لتساعد في التعامل مع التنسيق البصري للمستند. وكذلك بإمكانك أن تستخدم XML في مستندات HTML للتزويد ببيانات مركبة والتي هي بيانات عن البيانات التي في المستند.",
                ),
                card(
                    null,
                    "XML will do the Web and e-commerce what HTML originally did to the internet . XML and its associated applications have the potential to blow the roof off the internet and we do business.",
                    "XML ستعمل للويب وللتجارة الالكترونية ما فعلته HTML بالأساس للإنترنت.\nXML وتطبيقاتها المرتبطة تمتلك القدرة على اختراق سقف الانترنت وما نقوم به من أعمال.\n",
                ),
                card(
                    null,
                    "XML will do the Web and e-commerce what HTML originally did to the internet . XML and its associated applications have the potential to blow the roof off the internet and we do business.",
                    "XML ستعمل للويب وللتجارة الالكترونية ما فعلته HTML بالأساس للإنترنت.\nXML وتطبيقاتها المرتبطة تمتلك القدرة على اختراق سقف الانترنت وما نقوم به من أعمال.\n",
                ),
            ),
        ),
    )
}
