package ru.skillbranch.devintensive

import android.graphics.Color
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.skillbranch.devintensive.ui.custom.CircleImageView
import ru.skillbranch.devintensive.ui.profile.ProfileActivity
import java.lang.String

@RunWith(AndroidJUnit4::class)
class Task4 {
    @Rule
    @JvmField
    val rule = ActivityTestRule(ProfileActivity::class.java)

    @Test
    fun circleImageViewTest(){
        val avatarViewId = rule.activity.resources.getIdentifier("iv_avatar", "id", rule.activity.packageName)
        val avatarView = rule.activity.findViewById<CircleImageView>(avatarViewId)

        assertEquals(2, avatarView.getBorderWidth())
        assertEquals(-1, avatarView.getBorderColor())

        rule.activity.runOnUiThread {
            avatarView.setBorderWidth(0)
            assertEquals(0, avatarView.getBorderWidth())

            avatarView.setBorderWidth(23)
            assertEquals(23, avatarView.getBorderWidth())

            val colorAccent = rule.activity.resources.getIdentifier("color_accent", "color", rule.activity.packageName)
            avatarView.setBorderColor(colorAccent)
            val color = rule.activity.resources.getColor(colorAccent, rule.activity.theme)
            Log.d("S_Task4", "${rule.activity.packageName}, $colorAccent(${String.format("#%06X",0xFFFFFF and colorAccent)}), ${rule.activity.theme}, $color(${String.format("#%06X",0xFFFFFF and color)})")
            assertEquals(color, avatarView.getBorderColor())

            avatarView.setBorderColor("#FC4C4C")
            assertEquals(Color.parseColor("#FC4C4C"), avatarView.getBorderColor())
        }
    }
}