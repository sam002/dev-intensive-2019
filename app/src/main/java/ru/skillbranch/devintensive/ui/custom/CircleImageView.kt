package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toColorInt
import ru.skillbranch.devintensive.R
import kotlin.math.min


class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    companion object{
        private const val DEFAULT_BORDER_COLOR = -1 //Color::WHITE
//        private const val DEFAULT_BORDER_COLOR = -0x10000//Color::RED
        private const val DEFAULT_BORDER_WIDTH = 2.0F
    }

    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    init {
        if(attrs!=null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val x = width
        val y = height
        val radius: Int = min(width, height)/2
        val drawable = drawable ?: return
        if (width == 0 || height == 0) {
            return
        }
        val image: Bitmap = drawable.toBitmap()
        val paint = Paint()
        val paintBorder = Paint()
        paintBorder.color = borderColor
        setLayerType(LAYER_TYPE_SOFTWARE, paintBorder)

        val shader = BitmapShader(
            Bitmap.createScaledBitmap(
                image,
                width,
                height,
                true
            ), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )

        paint.shader = shader
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC)
        canvas.drawCircle((x / 2).toFloat(), (y / 2).toFloat(), radius.toFloat(), paintBorder)
        canvas.drawCircle((x / 2).toFloat(), (y / 2).toFloat(), radius.toFloat()-borderWidth, paint)
    }

    @Dimension
    fun getBorderWidth():Int{
        return borderWidth.toInt()
    }
    fun setBorderWidth(@Dimension dp:Int){
        borderWidth = dp.toFloat()
    }

    @ColorRes
    fun getBorderColor():Int{
        return borderColor
    }
    fun setBorderColor(hex:String){
        borderColor = hex.toColorInt()
    }
    fun setBorderColor(@ColorRes colorId: Int){
        borderColor = colorId
    }
}