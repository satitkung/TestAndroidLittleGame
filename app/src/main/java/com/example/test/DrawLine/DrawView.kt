package com.example.test.DrawLine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.concurrent.timerTask

class DrawView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val deletePaint = Paint()

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 2f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        deletePaint.isAntiAlias = true
        deletePaint.strokeWidth = 2f
        deletePaint.color = Color.RED
        deletePaint.style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        DrawlineManager.deleteLine?.let { deleteLine ->
            drawLineFromList(canvas)
            canvas?.drawLine(
                deleteLine.startPoint.plotX,
                deleteLine.startPoint.plotY,
                deleteLine.endPoint.plotX,
                deleteLine.endPoint.plotY,
                deletePaint
            )

            Timer().schedule(timerTask {
                DrawlineManager.deleteIntersectLine()
                invalidate()
            }, DrawlineManager.TIME_DELAY)

        } ?: run {
            drawLineFromList(canvas)
        }

    }

    private fun drawLineFromList(canvas: Canvas?) {
        DrawlineManager.listLine.forEach { normalLine ->
            canvas?.drawLine(
                normalLine.startPoint.plotX,
                normalLine.startPoint.plotY,
                normalLine.endPoint.plotX,
                normalLine.endPoint.plotY,
                paint
            )
        }
    }

}