package com.example.test.dragAndDropMenuTwoRCV

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.dragAndDropMenu.MainMenuFragment

class DragAndDropTwoRCVActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop_menu)
        addFragment(MainMenuFragment2rcv.newInstance(), false)
    }

    fun addFragment(fragment: Fragment, isAdd: Boolean) {
        if (isAdd) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}
