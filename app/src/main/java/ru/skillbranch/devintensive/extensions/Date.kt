package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        else -> throw  IllegalStateException("Invalid unit")
    }

    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value:Int) : String {
        return when (this) {
            SECOND -> pluralize(value, "секунда", "секунды", "секунд")
            MINUTE -> pluralize(value, "минута", "минуты", "минут")
            HOUR -> pluralize(value, "час", "часа", "часов")
            DAY -> pluralize(value, "день", "дня", "дней")
        }
    }
}


fun pluralize(number:Int, form1:String, form2: String, form5: String) : String {
    val number = abs(number)
    if (number % 10 === 1 && number % 100 !== 11)
        return form1
    else if (number % 10 >= 2 && number % 10 <= 4 && (number % 100 < 10 || number % 100 >= 20))
        return form2
    else
        return form5
}

fun Date.humanizeDiff(date:Date = Date()) : String {
    val diffMs = date.time - this.time
    return when (diffMs) {
        in -Long.MAX_VALUE..-360*DAY-> "более чем через год"
        in -360*DAY..-26*HOUR-> "через ${abs(diffMs/DAY).toInt()} ${TimeUnits.DAY.plural((diffMs/DAY).toInt())}"
        in -26*HOUR..-22*HOUR-> "через день"
        in -22*HOUR..-75*MINUTE-> "через ${abs(diffMs/HOUR).toInt()} ${TimeUnits.HOUR.plural((diffMs/HOUR).toInt())}"
        in -75*MINUTE..-45*MINUTE-> "через час"
        in -45*MINUTE..-75*SECOND-> "через ${abs(diffMs/MINUTE).toInt()} ${TimeUnits.MINUTE.plural((diffMs/MINUTE).toInt())}"
        in -75*SECOND..-45*SECOND-> "через минуту"
        in -45*SECOND..-1*SECOND-> "через ${abs(diffMs/SECOND).toInt()} ${TimeUnits.SECOND.plural((diffMs/SECOND).toInt())}"
        in -1*SECOND..1*SECOND-> "только что"
        in 1*SECOND..45*SECOND-> "${(diffMs/SECOND).toInt()} ${TimeUnits.SECOND.plural((diffMs/SECOND).toInt())} назад"
        in 45*SECOND..75*SECOND-> "минуту назад"
        in 75*SECOND..45*MINUTE-> "${(diffMs/MINUTE).toInt()} ${TimeUnits.MINUTE.plural((diffMs/MINUTE).toInt())} назад"
        in 45*MINUTE..75*MINUTE-> "час назад"
        in 75*MINUTE..22*HOUR-> "${(diffMs/HOUR).toInt()} ${TimeUnits.HOUR.plural((diffMs/HOUR).toInt())} назад"
        in 22*HOUR..26*HOUR-> "день назад"
        in 26*HOUR..360*DAY-> "${(diffMs/DAY).toInt()} ${TimeUnits.DAY.plural((diffMs/DAY).toInt())} назад"
        in 360*DAY..Long.MAX_VALUE-> "более года назад"
        else -> throw UnknownError("Unexpected time")
    }
}