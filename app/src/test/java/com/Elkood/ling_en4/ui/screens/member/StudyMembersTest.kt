package com.Elkood.ling_en4.ui.screens.member

import org.junit.Assert.assertEquals
import org.junit.Test

class StudyMembersTest {
    @Test
    fun hasFourteenMembers() {
        assertEquals(14, STUDY_MEMBERS.size)
    }

    @Test
    fun firstAndLastVerbatim() {
        assertEquals("لانا قدو", STUDY_MEMBERS[0])
        assertEquals("ياسمين اسماعيل  ", STUDY_MEMBERS[13])
    }

    @Test
    fun trailingSpacesPreserved() {
        // index 1 has a fatha on ريَان and two trailing spaces
        assertEquals("ريَان حوري  ", STUDY_MEMBERS[1])
        // index 8 has a double internal space and one trailing space
        assertEquals("فاطمة  الزهراء حمدك ", STUDY_MEMBERS[8])
    }
}
