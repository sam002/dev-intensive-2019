package ru.skillbranch.devintensive.extensions

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class DateKtTest {

    @Test
    fun format() {
        assertEquals("19:00:00 27.06.19", Date(1561651200 * SECOND).format())
        assertEquals("19:00", Date(1561651200 * SECOND).format("HH:mm"))
    }

    @Test
    fun add() {
        assertEquals("Thu Jun 27 19:00:02 MSK 2019", Date(1561651200 * SECOND).add(2, TimeUnits.SECOND).toString())
        assertEquals("Sun Jun 23 19:00:00 MSK 2019", Date(1561651200 * SECOND).add(-4, TimeUnits.DAY).toString())
    }

    @Test
    fun humanizeDiff() {
        assertEquals("минуту назад", Date().add(-46, TimeUnits.SECOND).humanizeDiff())
        assertEquals("2 часа назад", Date().add(-2, TimeUnits.HOUR).humanizeDiff())
        assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 2 минуты", Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 7 дней", Date().add(7, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 6 дней", Date().add(6, TimeUnits.DAY).humanizeDiff())
        assertEquals("более года назад", Date().add(-400, TimeUnits.DAY).humanizeDiff())
        assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())
    }
}