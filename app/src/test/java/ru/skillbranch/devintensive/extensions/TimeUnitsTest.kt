package ru.skillbranch.devintensive.extensions

import org.junit.Test

import org.junit.Assert.*

class TimeUnitsTest {

    @Test
    fun plural() {
        assertEquals("1 секунду", TimeUnits.SECOND.plural(1))
        assertEquals("4 минуты", TimeUnits.MINUTE.plural(4))
        assertEquals("19 часов", TimeUnits.HOUR.plural(19))
        assertEquals("222 дня", TimeUnits.DAY.plural(222))
    }
}