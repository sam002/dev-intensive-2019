package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.res.Resources
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.R

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = this.currentFocus
    if (view != null) {
        imm. hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView:View = this.findViewById(android.R.id.content)
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)

    val heightDiff = rootView.height - rect.height()
    val maxDiff = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50.0F, this.resources.displayMetrics)
//    Log.d("S_Activity", "$heightDiff > $maxDiff, ${heightDiff > maxDiff}")
    return heightDiff > maxDiff
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}