package ru.skillbranch.devintensive.extensions

val transliterationMap = mapOf(
    'а' to "a",
    'б' to "b",
    'в' to "v",
    'г' to "g",
    'д' to "d",
    'е' to "e",
    'ё' to "e",
    'ж' to "zh",
    'з' to "z",
    'и' to "i",
    'й' to "i",
    'к' to "k",
    'л' to "l",
    'м' to "m",
    'н' to "n",
    'о' to "o",
    'п' to "p",
    'р' to "r",
    'с' to "s",
    'т' to "t",
    'у' to "u",
    'ф' to "f",
    'х' to "h",
    'ц' to "c",
    'ч' to "ch",
    'ш' to "sh",
    'щ' to "sh'",
    'ъ' to "",
    'ы' to "i",
    'ь' to "",
    'э' to "e",
    'ю' to "yu",
    'я' to "ya"
)

fun String.translitirate(): String {
    var resultString = ""
    for (sourceChar in this.toCharArray()) {
        if (sourceChar.isUpperCase()) {
            val replaceChar = transliterationMap[sourceChar.toLowerCase()]
            resultString += replaceChar?.capitalize() ?: sourceChar
        } else {
            resultString += transliterationMap[sourceChar] ?: sourceChar
        }
    }
    return resultString
}


fun String.truncate(maxLength:Int = 16):String {
    var headSubString = this.substring(0,maxLength).trimEnd()
    if(headSubString == this.trimEnd()) {
        return headSubString
    }
    return headSubString.trim() + "..."
}
fun String.stripHtml(): String {
    return this
        .replace(Regex("<\\s*\\/?[^>]*>"), "")
        .replace(Regex("&\\w+[^\\s]\\s"), "")
        .replace(Regex("\\s{2,}"), " ")
}