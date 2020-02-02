package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toColorInt
import ru.skillbranch.devintensive.R
import kotlin.math.min

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    companion object{
        private const val DEFAULT_BORDER_COLOR = -1 //Color::WHITE
        private const val DEFAULT_BORDER_WIDTH = 2.0F
    }

    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidthPx:Float = dp2px(DEFAULT_BORDER_WIDTH)

    private val paint = Paint()
    private val paintBorder = Paint()
    private var image:Bitmap? = null

    init {
        if(attrs!=null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            borderWidthPx = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
            a.recycle()
        }

        paintBorder.color = borderColor
        setLayerType(View.LAYER_TYPE_HARDWARE, paintBorder)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width <= 0 || height <= 0) {
            return
        }
        if (image == null) {
            image = drawable.toBitmap()
        }
        paint.shader = BitmapShader(
            Bitmap.createScaledBitmap(
                image!!,
                width,
                height,
                true
            ), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )

        val x = width
        val y = height
        val radius: Int = min(width, height)/2

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC)
        canvas.drawCircle((x / 2).toFloat(), (y / 2).toFloat(), radius.toFloat(), paintBorder)
        canvas.drawCircle((x / 2).toFloat(), (y / 2).toFloat(), radius.toFloat()-borderWidthPx, paint)
    }

    private fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        )
    }

    private fun px2dp (px: Float): Float {
        return px/TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            1.0F,
            Resources.getSystem().displayMetrics
        )
    }

    @Dimension
    fun getBorderWidth():Int{
        return px2dp(borderWidthPx).toInt()
    }
    fun setBorderWidth(@Dimension dp:Int){
        borderWidthPx = dp2px(dp.toFloat())
    }

    @ColorRes
    fun getBorderColor():Int{
        return borderColor
    }

    fun setBorderColor(hex:String){
        borderColor = hex.toColorInt()
    }

    fun setBorderColor(@ColorRes colorId: Int){
        val color = ContextCompat.getColor(context, colorId)
        borderColor = color
    }
}