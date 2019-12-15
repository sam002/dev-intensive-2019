package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?>{
        var parts: List<String>? = null
        if (fullName?.trim()?.isNotBlank() ?: false) {
            parts = fullName?.replace(' ', '+')?.split('+')
        }

        var firstName = parts?.getOrNull(0) ?: "John"
        var lastName = parts?.getOrNull(1) ?: "Doe"

        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String {
        return "string"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return "string"
    }
}