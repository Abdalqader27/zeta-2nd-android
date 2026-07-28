package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.Question

/** Vocabulary interactive-quiz content, migrated verbatim from QuizDbHelper_Vocabulary. */
object En4Vocabulary {

    private fun q(prompt: String, o1: String, o2: String, o3: String, o4: String, answer: Int) =
        Question(prompt, listOf(o1, o2, o3, o4), correctIndex = answer - 1)

    val questions: List<Question> = listOf(
        q("Website : ", "Using reference work like encyclopedias", "Collection of related webpages.", "Composing Music on PC.", "Type of compression used for Bitmap image", 2),
        q("Virus : ", "Self-replicating program.", "Formula used for decompressing component of data stream.", "the amount of data transferred to the cache at any one time ", "Composing Music on PC.", 1),
        q("Office Suite : ", "Downloading Music from the internet", "Set of Standard Programs used in an Office.", "Collection of related webpages.", "Common type of Compression used for video data.", 2),
        q("Bandwidth : ", "High Capacity of internet connection", "a combination of text with sound, graphic and video", "Composing Music on PC. ", "Capacity of a network connection.", 4),
        q("Broadband : ", "Capacity of a network connection.", "Composing Music on PC", "High Capacity of internet connection  ", "a combination of text with sound, graphic and video ", 3),
        q("Data Center", "a system that allows users to interact with a combination of inputs  ", "Facility for storing Large Amount of information", "Common Enterprise resource Planning tool ", "software assistant that performs tasks such as automatic repetitive tasks   ", 2),
        q("SAP ", "Common Enterprise resource Planning tool", "Facility for storing Large Amount of information", "software assistant that performs tasks such as automatic repetitive tasks  ", "a system that allows users to interact with a combination of inputs    ", 1),
        q("MIDI", "Common Enterprise resource Planning tool ", "Facility for storing Large Amount of information", " standard for interconnecting electronic musical instruments and computers.", "software assistant that performs tasks such as automatic repetitive tasks  ", 3),
        q("Mp3 :", "Common Enterprise resource Planning tool ", "Facility for storing Large Amount of information", "software assistant that performs tasks such as automatic repetitive tasks  ", "Downloading Music from the internet. ", 4),
        q("DVD :", "Composing Music on PC ", "Watching Movie ", "Common Enterprise resource Planning tool", "Downloading Music from the internet. ", 2),
        q("Algorithm : ", "Common Enterprise resource Planning tool ", "Formula used for decompressing component of data stream. ", "Facility for storing Large Amount of information", "Using reference work like encyclopedias. ", 2),
        q("I-Frame ", "Abstucting and Indexing  ", "Common Enterprise resource Planning tool ", "Compressed video frame that contains the complete Image Information", "Using reference work like encyclopedias.   ", 3),
        q("JPEG  ", "Using reference work like encyclopedias. ", "Composing Music on PC ", "Formula used for decompressing component of data stream", "Type of compression used for Bitmap image.", 4),
        q("P-Frame ", " Type of compression used for Bitmap image. ", "Compressed video frame known as Predicted Frame.  ", "Compressed video frame that stores Changes between the frame before it and the frame after it. ", "Using reference work like encyclopedias.", 2),
        q("B-Frame :", " Compressed video frame that stores Changes between the frame before it and the frame after it.  ", "Compressed video frame known as Predicted Frame.  ", "Type of compression used for Bitmap image. ", "Using reference work like encyclopedias.", 1),
        q("MPEG : ", " Using reference work like encyclopedias.  ", "Common type of Compression used for video data  ", "Type of compression used for Bitmap image. ", "Downloading Music from the internet. ", 2),
        q("Bracketing :  ", " Using reference work like encyclopedias.  ", "Watching Movie   ", "Set boundaries for the beginning and end of message  ", "Mathematical calculation based on the content of data", 3),
        q("Checksum : ", "Formula used for decompressing component of data stream", "Common type of Compression used for video data     ", "Set boundaries for the beginning and end of message  ", "Mathematical calculation based on the content of data ", 4),
        q("Half-Duplex :", " Transmission mode in which each computer takes turn sending and receiving  ", "Transmission mode in which both computers send and receive at the same time  ", "Set boundaries for the beginning and end of message     ", "Mathematical calculation based on the content of data  ", 1),
        q("Full-Duplex", " Transmission mode in which each computer takes turn sending and receiving  ", "Transmission mode in which both computers send and receive at the same time  ", "Set boundaries for the beginning and end of message     ", "Mathematical calculation based on the content of data  ", 2),
        q("IRC :    ", " Chatting to other users in real-time. ", "Formula used for decompressing component of data stream  ", "Set boundaries for the beginning and end of message    ", "Mathematical calculation based on the content of data   ", 1),
        q("Moos :  ", " Chatting to other users in real-time.    ", "Formula used for decompressing component of data stream", "Mathematical calculation based on the content of data  ", "Taking part in simulation in shared environment  ", 4),
        q("E-Mail : ", "Chatting to other users in real-time.   ", "Mathematical calculation based on the content of data ", "Sending and receiving message   ", "Taking part in simulation in shared environment   ", 3),
        q("FTP : ", "Downloading file from server.", "Sending and receiving message ", "Taking part in simulation in shared environment     ", "Chatting to other users in real-time. ", 1),
        q("WWW :  ", " Downloading file from server.  ", "Browsing web page  ", "branch computer      ", "Taking part in simulation in shared environment ", 2),
        q("Telnet :  ", "Logging on to your computer at a distance  ", "Accessing web pages ", "Browsing web page  ", "Chatting to other users in real-time.", 1),
        q("Usenet : ", " Browsing web page ", "Accessing web pages ", "branch computer     ", "Chatting to other users in real-time.", 2),
        q("Router : ", " Browsing web page ", "Accessing web pages ", "Special computer that's directs communications.  ", "Main transmission path handling major data traffic", 3),
        q("backbone : ", " Special computer that's directs communications. ", "Taking part in simulation in shared environment  ", "branch computer   ", "Main transmission path handling major data traffic ", 4),
        q("Internet Address ", " Main transmission path handling major data traffic ", "Accessing web pages ", "Taking part in simulation in shared environment ", "A 32-bit number identifying anode on an IP network ", 4),
        q("Resolution Protocol", " Standard used for software that routes data through get way ", "A 32-bit number identifying anode on an IP network", "Main transmission path handling major data traffic  ", "Taking part in simulation in shared environment ", 1),
        q("Look-up Table  ", " Taking part in simulation in shared environment", "Stored information used to route data through get way.", "Main transmission path handling major data traffic  ", "Standard used for software that routes data through get way ", 2),
        q("Get Way :  ", " Device for connecting dissimilar networks. ", "Standard used for software that routes data through get way", "Main transmission path handling major data traffic  ", "Taking part in simulation in shared environment ", 1),
        q("User Datagram Protocol(UDB) ", " Device for connecting dissimilar networks. ", "Taking part in simulation in shared environment", "Standard used by software that moves information to the correct application on the receiving system of a network.   ", "Main transmission path handling major data traffic ", 3),
        q("Transmission Control Protocol(TCP) ", " Device for connecting dissimilar networks. ", "Taking part in simulation in shared environment", "Standard used by software that moves information to the correct application on the receiving system of a network.   ", "Standard used by software that manage communication exchanges between computers on the internet  ", 4),
        q("ISMTP  ", "Device for connecting dissimilar networks.", "Simple Mail Transfer Protocol that is used to send message between server", " Device for connecting dissimilar networks. ", "Taking part in simulation in shared environment", 2),
        q("Push' Operation ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 3),
        q("Pull' Operation  ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 4),
        q("POP   ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "A Message-Retrieval protocol that download all E-Mail messages at the same time ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 3),
        q("IMAP   ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "Mail transfer protocol that initially only retrieves the message handers ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 3),
        q("Metadata   ", " Data about Data  ", "Device for connecting dissimilar networks.  ", "A Message-Retrieval protocol that download all E-Mail messages at the same time ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 1),
        q("GMetalanguage   ", " Data about Data  ", "Language from which you can create other Language. ", "A Message-Retrieval protocol that download all E-Mail messages at the same time ", "Device for connecting dissimilar networks.  ", 2),
        q("HTML  ", " Data about Data  ", "Language from which you can create other Language. ", "example of page presentation Language.", "Device for connecting dissimilar networks.  ", 3),
        q("XML ", " Data about Data  ", "Language from which you can create other Language. ", "example of page presentation Language.", "extensible markup Language ", 4),
        q("Markup Language ", " coding system used for structuring and formatting documents. ", "Language from which you can create other Language. ", "example of page presentation Language.", "extensible markup Language ", 1),
    )
}
