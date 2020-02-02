package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Silver", null, lastVisit = Date(), isOnline = true)

//        user2.printMe()
//        user3.printMe()

        println("$user $user2 $user3")
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("John Cena")
        val user2 = user.copy(id="2", lastName = "Adssf", lastVisit = Date())
        print(user2)
    }

    @Test
    fun test_builder() {
        val user = User.Builder()
            .id("123")
            .firstName("Sam")
            .lastName("Dub")
            .avatar("ava")
            .rating(0)
            .respect(1)
            .lastVisit(Date())
            .isOnline(true)
            .build()

        user.toUserView().printMe()
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser(null)

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()}, $firstName, $lastName")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.MINUTE))
        val user3 = user.copy(lastName = "Cena", lastVisit = Date())

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent())
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Семен Дубина")
        val userView = user.toUserView()

        userView.printMe()
    }

    @Test
    fun text_abstract_factory() {
        val user = User.makeUser("Семен Дубина")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), Date().add(20, TimeUnits.MINUTE),"image", "any.url/image.png", true)

        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())

        val textMessage1 = BaseMessage.makeMessage(user, Chat("0"), Date().add(20, TimeUnits.MINUTE), "any text message", "text") //Василий отправил сообщение "any text message" только что
        val textMessage2 = BaseMessage.makeMessage(user, Chat("0"), Date().add(20, TimeUnits.MINUTE), "https://anyurl.com", "image",true) //Василий получил изображение "https://anyurl.com" 2 часа назад

        println(textMessage1.formatMessage())
        println(textMessage2.formatMessage())

    }
}

