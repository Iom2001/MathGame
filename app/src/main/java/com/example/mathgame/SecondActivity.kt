package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var a = intent.getSerializableExtra("list") as ArrayList<Int>
        score_end.text = "Score: ${a[0]}"
        topdi.text = "Correct: ${a[1]}"
        topolmadi.text = "Incorrect: ${a[2]}"

        restart_game.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        home.setOnClickListener {
            finish()
        }
    }
}