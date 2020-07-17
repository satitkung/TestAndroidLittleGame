package com.example.test.CountGame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class CountActivity : AppCompatActivity() {

    enum class Turn {
        Human,
        Bot
    }
    var humanPoint = 0
    var summary = 0
    var id: Int = 0
    var currentTurn = Turn.Bot
    var pointList = ArrayList<TypePoint>()
    lateinit var adapter: ListPointAdapter
    lateinit var winner: Turn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ListPointAdapter(this)
        rcv_list.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)
        rcv_list.adapter = adapter

        initview()
        setOnClick()
    }

    private fun setOnClick() {
        cl_1.setOnClickListener {
            humanPoint = 1
            currentTurn = Turn.Bot
            updateList(Turn.Human, 1)
            initview()
        }

        cl_2.setOnClickListener {
            humanPoint = 2
            currentTurn = Turn.Bot
            updateList(Turn.Human, 2)
            initview()
        }

        cl_reset.setOnClickListener {
            resetGame()
        }
    }

    private fun initview() {
        if (checkWinner()){
            AlertDialog.Builder(this)
                .setTitle("The winner is")
                .setMessage(winner.toString())
                .show()
            resetGame()
        } else {
            checkTurn()
        }
    }

    private fun resetGame() {
        summary = 0
        humanPoint = 0
        id = 0
        currentTurn = Turn.Bot
        pointList.clear()
        adapter.submitList(pointList.toList())
        initview()
    }

    private fun checkTurn() {
        when (currentTurn) {
            Turn.Bot -> {
                Handler().postDelayed({
                    var data= 0
                    when (humanPoint) {
                        0 -> data = 1
                        1 -> data = 2
                        2 -> data = 1
                    }
                    currentTurn = Turn.Human
                    updateList(Turn.Bot, data)
                    initview()
                }, 1000)
            }
            else -> {}
        }
    }

    data class TypePoint(val id: Int, val pointBot: String, val pointHuman: String)

    private fun updateList(turn: Turn, count: Int){
        for (i in 1..count) {
            summary += 1
            if (turn == Turn.Human){
                pointList.add(TypePoint(id++, "", summary.toString()))
            } else {
                pointList.add(TypePoint(id++, summary.toString(), ""))
            }
        }
        adapter.submitList(pointList.toList())
        rcv_list.smoothScrollToPosition(pointList.size - 1)
        if (summary >= 20) {
            winner = turn
        }
    }

    private fun checkWinner(): Boolean {
        return summary >= 20
    }

}
