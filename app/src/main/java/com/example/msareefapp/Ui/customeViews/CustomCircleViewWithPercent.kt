package com.example.msareefapp.Ui.customeViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.provider.CalendarContract.Colors
import android.util.AttributeSet
import android.view.View
import com.example.msareefapp.R
import kotlin.math.roundToInt

class CustomCircleViewWithPercent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {
    private var aboveCircleColor: Int = Color.RED
    private var circleText: String = "0"
    private var backgroundCircleColor = Color.LTGRAY
    private var circleTextColor = Color.BLACK

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomCircleViewWithPercent,
            0, 0
        )


        try {
            backgroundCircleColor = typedArray.getColor(R.styleable.CustomCircleViewWithPercent_backgroundCircleColor,Color.TRANSPARENT)
            aboveCircleColor =typedArray.getColor(R.styleable.CustomCircleViewWithPercent_aboveCircleColor,Color.RED)
            circleText =typedArray.getString (R.styleable.CustomCircleViewWithPercent_circleText)?:"0"
            circleTextColor = typedArray.getColor(R.styleable.CustomCircleViewWithPercent_circleTextColor,Color.BLACK)
        } finally {
            typedArray.recycle()
        }
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 20f // سمك الخط

    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK //
        textAlign = Paint.Align.CENTER

    }


    private var percentage = 0f // النسبة المئوية

    fun setPercentage(value: Float) {
        percentage = value
        circleText = "${percentage.roundToInt()}%"// م الـ View
        invalidate() // إعادة رس
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (minOf(width, height) / 2f) - circlePaint.strokeWidth

        // تحديد اللون
        circlePaint.color = aboveCircleColor // حدد اللون هنا
        canvas.drawCircle(centerX, centerY, radius, circlePaint) // رسم الخلفية

        // رسم الجزء المعبر عن النسبة
        val sweepAngle = (percentage / 100f) * 360f // زاوية النسبة
        circlePaint.color = backgroundCircleColor // لون الجزء
        canvas.drawArc(
            centerX - radius, centerY - radius,
            centerX + radius, centerY + radius,
            -90f, sweepAngle, false, circlePaint
        )

       textPaint.color=circleTextColor
        textPaint.textAlign=Paint.Align.CENTER
        textPaint.textSize=radius/2

        canvas.drawText(circleText,width/2f,height/2f+20f,textPaint)
    }

}