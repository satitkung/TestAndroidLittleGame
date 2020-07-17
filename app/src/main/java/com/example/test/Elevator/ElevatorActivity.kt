package com.example.test.Elevator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.test.R


class ElevatorActivity : AppCompatActivity(), FloorElevatorView.goDetailPage {

    private var maxNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elevator)

        maxNumber = intent?.extras?.getInt(MainElevatorActivity.MAX_NUMBER_LIFT) ?: 1
        addFragment(HomeElevatorFragment.newInstance(maxNumber), false)
    }

    fun addFragment(fragment: androidx.fragment.app.Fragment, isAdd: Boolean) {
        if (isAdd) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.content, fragment)
                .commit()
        }
    }

    override fun onDetailClicked(position: Int) {
        addFragment(DetailElevatorFragment.newInstance(position + 1), true)
    }

}
