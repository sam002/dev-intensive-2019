package ru.skillbranch.devintensive.ui.custom

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
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

        val maxTextSize = (0.9*size/sqrt(2.0)).toFloat()

        fontPaint.textSize = maxTextSize
        fontPaint.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)

//        Log.d("S_AvatarTextDrawable", "$width, $height, $radius")

        // Draw a red circle in the center
//        val redPaint: Paint = Paint().apply { setARGB(255, 255, 0, 0) }
//        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), size/2.toFloat(), redPaint)

//        canvas.drawColor(-0x10000)
        canvas.drawColor(backgroundColor)

        val textBound = Rect()
        fontPaint.getTextBounds(text, 0, text.length, textBound)
        canvas.drawText(text,(width / 2).toFloat(), (height+textBound.height())/2.toFloat(), fontPaint)
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
}