package ru.skillbranch.devintensive

import android.widget.EditText
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.skillbranch.devintensive.ui.profile.ProfileActivity

@RunWith(AndroidJUnit4::class)
class Task7 {
    @Rule
    @JvmField
    val rule = ActivityTestRule(ProfileActivity::class.java)

    var repoId: Int? = null
    var repo: EditText? = null
    var weRepoId: Int? = null
    var weRepo: TextInputLayout? = null
    var editBtnId: Int? = null
    var eyeId: Int? = null

    @Before
    fun beforeTest(){
        updateFields()
    }

    @Test
    fun onlyBySB() {
        check("https://github.com/johnDoe", true)
        check("https://www.github.com/johnDoe", true)
        check("www.github.com/johnDoe", true)
        check("github.com/johnDoe", true)
        check("https://anyDomain.github.com/johnDoe", false)
        check("https://github.com/", false)
        check("https://github.com", false)
        check("https://github.com/johnDoe/tree", false)
        check("https://github.com/johnDoe/tree/something", false)
        check("https://github.com/enterprise", false)
        check("https://github.com/pricing", false)
        check("https://github.com/join", false)
    }

//    @Test
//    fun validateRepoTest(){
//        check("https://anyDomain.github.com/johnDoe", false)
//        check("https://github.com/johnDoe", true)
//        check("https://github5com/johnDoe", false)
//        check("https://github.com/", false)
//        check("https://www.github.com/johnDoe", true)
//        check("https://www4github.com/johnDoe", false)
//        check("https://github.com", false)
//        check("www.github.com/johnDoe", true)
//        check("https://github.com/johnDoe/tree", false)
//        check("github.com/johnDoe", true)
//        check("https://github.com/johnDoe/tree/something", false)
//        check("https://github.com/john-Doe", true)
//        check("https://github.com/johnDoe/", true)
//        check("https://github.com/enterprise", false)
//        check("", true)
//        check("https://github.com/pricing", false)
//        check("https://github.com/surpricing", true)
//        check("https://github.com/join", false)
//        check("https://github.com/join2", true)
//        check("https://github.com/features", false)
//        check("https://github.com/ufeatures", true)
//        check("https://github.com/topics", false)
//        check("https://github.com/topics-knopics", true)
//        check("https://github.com/collections", false)
//        check("https://github.com/my-collections", true)
//        check("https://github.com/trending", false)
//        check("https://github.com/events", false)
//        check("https://github.com/marketplace", false)
//        check("https://github.com/nonprofit", false)
//        check("https://github.com/johnDoe123", true)
//        check("https://github.com/customer-stories", false)
//        check("https://github.com/security", false)
//        check("https://github.com/securitys", true)
//        check("https://github.com/ myrep", false)
//        check("https://github.com/my_rep", false)
//        check("https://github.com/my rep", false)
//        check("https://github.com/myrep _", false)
//        check("https://github.com//myrep", false)
//        check("https://github.com/myrep//", false)
//        check("/myrep", false)
//        check("myrep", false)
//    }
//
//    /* ВНИМАНИЕ!!! Этот тест не является обязательным. Но соответствует реальной валидации на github
//     * Можете проверить лишь для себя */
//    @Test
//    fun fullValidationTest() {
//        check("github.com/_myrep", false)
//        check("github.com/myrep_", false)
//        check("github.com/my_rep", false)
//        check("github.com/my--rep", false)
//        check("github.com/-myrep", false)
//        check("github.com/myrep-", false)
//        check("github.com/myrep!", false)
//        check("github.com/+myrep", false)
//    }


    @Test
    fun saveOnRotate() {
        Espresso.onView(ViewMatchers.withId(editBtnId!!)).perform(ViewActions.click())
        typeRepo("https://www.github.com/johnDoe")
        rotateScreen(rule.activity, true)
        Espresso.onView(ViewMatchers.withId(editBtnId!!)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(editBtnId!!)).perform(ViewActions.click())
    }

    private fun check(text: String, isValid: Boolean) {
        checkEye(true)
        Espresso.onView(ViewMatchers.withId(editBtnId!!)).perform(ViewActions.click())
        checkEye(false)
        typeRepo(text)
        checkEye(false)
        checkError(isValid)
        rotateScreen(rule.activity, true)
        Espresso.onView(ViewMatchers.withId(editBtnId!!)).perform(ViewActions.click())
        rotateScreen(rule.activity, false)
        updateFields()
        checkSave(isValid, text)
        checkExit(isValid, text)
    }

    private fun checkEye(isVisible: Boolean) {
        if (isVisible)
            Espresso.onView(ViewMatchers.withId(eyeId!!)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        else
            Espresso.onView(ViewMatchers.withId(eyeId!!)).check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }

    private fun checkExit(isValid: Boolean, text: String) {
        val intent = rule.activity.intent
        rule.finishActivity()
        Thread.sleep(1000)
        rule.launchActivity(intent)
        Thread.sleep(1000)
        updateFields()

        checkSave(isValid, text)
    }

    private fun checkSave(isValid: Boolean, text: String) {
        if (isValid)
            assertEquals(text, repo!!.text.toString())
        else
            assertEquals("", repo!!.text.toString())

        assertTrue(weRepo!!.error == null)
        assertTrue(weRepo!!.isErrorEnabled.not())
    }

    private fun checkError(isValid: Boolean) {
        if (!isValid){
            assertEquals("Невалидный адрес репозитория", weRepo!!.error.toString())
            assertTrue(weRepo!!.isErrorEnabled)
            assertTrue(weRepo!!.error != null)
        }
        else {
            assertTrue(weRepo!!.error == null)
            assertTrue(weRepo!!.isErrorEnabled.not())
        }
    }

    private fun typeRepo(text: String) {
        Espresso.onView(ViewMatchers.withId(repoId!!)).perform(ViewActions.replaceText(""))
        Espresso.onView(ViewMatchers.withId(repoId!!)).perform(ViewActions.typeText(text))
        Espresso.onView(ViewMatchers.withId(repoId!!)).perform(ViewActions.closeSoftKeyboard())
    }

    private fun updateFields() {
        repoId = rule.activity.resources.getIdentifier("et_repository", "id", rule.activity.packageName)
        repo = rule.activity.findViewById<EditText>(repoId!!)
        weRepoId = rule.activity.resources.getIdentifier("wr_repository", "id", rule.activity.packageName)
        weRepo = rule.activity.findViewById<TextInputLayout>(weRepoId!!)
        editBtnId = rule.activity.resources.getIdentifier("btn_edit", "id", rule.activity.packageName)
        eyeId = rule.activity.resources.getIdentifier("ic_eye", "id", rule.activity.packageName)
    }
}