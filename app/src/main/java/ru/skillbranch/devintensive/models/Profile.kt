package ru.skillbranch.devintensive.models

import android.graphics.drawable.Drawable
import ru.skillbranch.devintensive.extensions.translitirate
import ru.skillbranch.devintensive.ui.custom.AvatarTextDrawable
import ru.skillbranch.devintensive.utils.Utils

data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val nickName: String
        get() {
            return Utils.transliteration("$firstName $lastName", "_")
        }

    fun getDefaultAvatar(charColor:Int, backgroundColor:Int) : AvatarTextDrawable? {
        val initials = Utils.toInitials(firstName, lastName)?.translitirate()
        if(initials.isNullOrEmpty()) {
            return null
        }

        return AvatarTextDrawable(initials, charColor, backgroundColor)

    }

    val rank: String = "Android Developer"

    fun  toMap(): Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}