package ru.skillbranch.devintensive.utils

import android.content.res.Resources
import android.util.TypedValue
import ru.skillbranch.devintensive.extensions.translitirate

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

    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        )
    }

    fun px2dp (px: Float): Float {
        return px/ TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            1.0F,
            Resources.getSystem().displayMetrics
        )
    }
}
