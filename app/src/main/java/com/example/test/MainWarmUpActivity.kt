package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.appcompat.app.AppCompatActivity
import com.example.test.CountGame.CountActivity
import com.example.test.DrawLine.DrawLineActivity
import com.example.test.Elevator.MainElevatorActivity
import com.example.test.dragAndDropMenu.DragAndDropMenuActivity
import com.example.test.dragAndDropMenuTwoRCV.DragAndDropTwoRCVActivity
import kotlinx.android.synthetic.main.activity_main_warm_up.*


class MainWarmUpActivity : AppCompatActivity() {

    var start = 1
    var end = 5
    private val TAG = "tee"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_warm_up)

        initView()
    }

    private fun initView() {
        rcv_warm_up.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)
        rcv_warm_up.adapter = WarmUpAdapter(menuList){ menu ->
            var intent = Intent()
            when (menu) {
                countMenu -> intent = Intent(this@MainWarmUpActivity, CountActivity::class.java)
                elevatorMenu -> intent = Intent(this@MainWarmUpActivity, MainElevatorActivity::class.java)
                drawLineMenu -> intent = Intent(this@MainWarmUpActivity, DrawLineActivity::class.java)
                dragAndDropMenu -> intent = Intent(this@MainWarmUpActivity, DragAndDropMenuActivity::class.java)
                dragAndDropMenuTwoPanel -> intent = Intent(this@MainWarmUpActivity, DragAndDropTwoRCVActivity::class.java)
            }
            startActivity(intent)
        }
    }

    fun expand(v: View) {
        val matchParentMeasureSpec: Int = View.MeasureSpec.makeMeasureSpec(
            (v.getParent() as View).getWidth(),
            View.MeasureSpec.EXACTLY
        )
        val wrapContentMeasureSpec: Int =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight: Int = v.getMeasuredHeight()

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1
        v.setVisibility(View.VISIBLE)
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation?
            ) {
                v.getLayoutParams().height =
                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // Expansion speed of 1dp/ms
        a.setDuration(
            (targetHeight / v.getContext().getResources().getDisplayMetrics().density as Float).toLong()

        )
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        val initialHeight: Int = v.getMeasuredHeight()
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation?
            ) {
                if (interpolatedTime == 1f) {
                    v.setVisibility(View.GONE)
                } else {
                    v.getLayoutParams().height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // Collapse speed of 1dp/ms
        a.setDuration(
            ((initialHeight / v.getContext().getResources()
                .getDisplayMetrics().density) as Int).toLong()
        )
        v.startAnimation(a)
    }


    companion object {
        private val countMenu = Menu("count", "Count 20 Number Game")
        private val elevatorMenu = Menu("elevator", "Elevator up/down Game")
        private val drawLineMenu = Menu("drawline", "Line draw/delete Game")
        private val dragAndDropMenu = Menu("DragMenu", "Drag and drop menu")
        private val dragAndDropMenuTwoPanel = Menu("DragMenu 2 Panel", "Drag and drop menu with 2 Panel")
        val menuList =  arrayOf(countMenu, elevatorMenu, drawLineMenu, dragAndDropMenu, dragAndDropMenuTwoPanel)
    }

}
