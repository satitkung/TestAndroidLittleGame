package com.example.test.Elevator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R
import kotlinx.android.synthetic.main.activity_main_elevator.*


class MainElevatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_elevator)

        btn_start.setOnClickListener {
            val numberLift = edt_number.text.toString()
            val intent = Intent(this, ElevatorActivity::class.java)
            intent.putExtra(MAX_NUMBER_LIFT, numberLift.toInt())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        edt_number?.setText("")
    }

    companion object {
        const val MAX_NUMBER_LIFT = "MAX_NUMBER_LIFT"
    }

}
