package ru.skillbranch.devintensive.ui.custom

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.TypedValue
import kotlin.math.min
import kotlin.math.sqrt

class AvatarTextDrawable(
    val text:String,
    val charColor: Int = -0x1, //Color::WHITE,
    val backgroundColor: Int = -0x10000//Color::RED
): Drawable() {

    override fun draw(canvas: Canvas) {
        val fontPaint = Paint()
        fontPaint.color = charColor
        fontPaint.textAlign = Paint.Align.CENTER

        // Get the drawable's bounds
        val width: Int = bounds.width()
        val height: Int = bounds.height()
        val size: Float = min(width, height).toFloat()

        val maxTextSize = (0.8*size/sqrt(2.0)).toFloat()

        fontPaint.textSize = maxTextSize
        fontPaint.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)

        canvas.drawColor(backgroundColor)

        val textBound = Rect()
        fontPaint.getTextBounds(text, 0, text.length, textBound)
        canvas.drawText(text,(width / 2).toFloat(), (height+textBound.height())/2.toFloat(), fontPaint)
    }

    private fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        )
    }

    private fun px2dp (px: Float): Float {
        return px/ TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            1.0F,
            Resources.getSystem().displayMetrics
        )
    }

    override fun setAlpha(alpha: Int) {
        Log.d("S_AvatarTextDrawable", "setAlpha")
    }

    override fun getOpacity(): Int {
        Log.d("S_AvatarTextDrawable", "getOpacity")
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        return PixelFormat.UNKNOWN
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        Log.d("S_AvatarTextDrawable", "setColorFilter")
    }

    override fun getIntrinsicWidth(): Int {
        return dp2px(bounds.width().toFloat()).toInt()
    }

    override fun getIntrinsicHeight(): Int {
        return  dp2px(bounds.height().toFloat()).toInt()
    }

}