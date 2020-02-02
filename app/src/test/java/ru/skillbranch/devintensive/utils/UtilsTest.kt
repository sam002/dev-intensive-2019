package ru.skillbranch.devintensive.utils

import org.junit.Test

import org.junit.Assert.*

class UtilsTest {

    @Test
    fun parseFullName() {
        assertEquals(Utils.parseFullName(null), Pair(null, null))
        assertEquals(Utils.parseFullName(""), Pair(null, null))
        assertEquals(Utils.parseFullName(" "), Pair(null, null))
        assertEquals(Utils.parseFullName("John"), Pair("John", null))
        assertEquals(Utils.parseFullName("John Wick"), Pair("John", "Wick"))
    }

    @Test
    fun transliteration() {
        assertEquals("Zhenya Stereotipov", Utils.transliteration("Женя Стереотипов"))
        assertEquals("Amazing_Petr", Utils.transliteration("Amazing Петр","_"))
    }

    @Test
    fun toInitials() {
        assertEquals(Utils.toInitials("john" ,"doe"), "JD")
        assertEquals(Utils.toInitials("John", null), "J")
        assertEquals(Utils.toInitials(null, null), null)
        assertEquals(Utils.toInitials(" ", ""), null)
    }
}