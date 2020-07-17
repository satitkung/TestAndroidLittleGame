package com.example.test.DrawLine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent
import com.example.test.R

import kotlinx.android.synthetic.main.activity_draw_line.*
import kotlinx.android.synthetic.main.content_draw_line.*

class DrawLineActivity : AppCompatActivity() {

    enum class Mode {
        DrawLine,
        DeleteLine,
    }

    var currentMode = Mode.DrawLine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_line)

        DrawlineManager.clearView()
        initView()
    }

    private fun initView() {
        fab_plus.setOnClickListener {
            currentMode = Mode.DrawLine
        }

        fab_minus.setOnClickListener {
            currentMode = Mode.DeleteLine
            DrawlineManager.clearDeleteLine()
        }

        fab_clear.setOnClickListener {
            currentMode = Mode.DrawLine
            DrawlineManager.clearView()
            draw.invalidate()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            when (currentMode) {
                Mode.DrawLine -> {
                    DrawlineManager.addPointList(Point(event.x, event.y))
                    draw.invalidate()
                }
                Mode.DeleteLine -> {
                    DrawlineManager.addDeleteLine(Point(event.x, event.y))
                    draw.invalidate()
                }
            }
        }
        return super.onTouchEvent(event)
    }

}
