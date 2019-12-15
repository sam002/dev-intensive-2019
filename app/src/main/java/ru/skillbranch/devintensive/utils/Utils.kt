package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.translitirate
import ru.skillbranch.devintensive.models.User
import java.util.*

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?>{
        var parts: List<String>? = null
        if (fullName?.trim()?.isNotBlank() ?: false) {
            parts = fullName?.trim()?.split(' ')
        }

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String = payload.translitirate().replace(" ", divider)

    fun toInitials(firstName: String?, lastName: String?): String? {
        var initals:String = ""
        initals += firstName?.getOrNull(0)?.toUpperCase() ?: ""
        initals += lastName?.getOrNull(0)?.toUpperCase() ?: ""

        return (if(initals.isNullOrBlank()) null else initals)
    }
}
