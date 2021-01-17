package com.example.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var random: Random
    var son1: Int? = null
    var son2: Int? = null
    var result: Int? = null
    var c: Char? = null
    var a: ArrayList<Int>? = null
    var barStatus = 60
    var score = 0
    var correct = 0
    var incorrect = 0
    var press = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        random = Random
        setElements()
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        progressCount()
    }

    private fun progressCount() {
        Thread(Runnable {
            while (barStatus > 0) {
                barStatus -= 1
                var m = barStatus / 60
                var s = barStatus % 60
                try {
                    Thread.sleep(1000)
                    prgBar.progress = barStatus
                } catch (exp: InterruptedException) {
                    exp.printStackTrace()
                }
                runOnUiThread(Runnable() {
                    kotlin.run {
                        if (s == 0)
                            gameTime.setText("$m:$s$s")
                        else
                            gameTime.setText("$m:$s")
                    }
                })
            }
            finish()
            val intent = Intent(this, SecondActivity::class.java)
            var list = ArrayList<Int>()
            list.add(score)
            list.add(correct)
            list.add(incorrect)
            intent.putExtra("list", list)
            startActivity(intent)
        }).start()

    }

    override fun onBackPressed() {
        var countDownTimer = object : CountDownTimer(1500, 1500) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                press = false
            }
        }
        if (press)
            finish()
        else
            Toast.makeText(this, "Click again to Exit", Toast.LENGTH_SHORT).show()
        press = true
    }

    private fun setElements() {
        randSon()
        var n = random.nextInt(4)
        when (n) {
            0 -> {
                c = '-'
                while (true) {
                    if (son1?.minus(son2!!)!! < 0)
                        randSon()
                    else break
                }
                result = son1?.minus(son2!!)
            }
            1 -> {
                c = '+'
                result = son1?.plus(son2!!)
            }
            2 -> {
                c = '*'
                result = son1?.times(son2!!)
            }
            3 -> {
                c = '/'
                while (true) {
                    if (son1?.rem(son2!!) != 0)
                        randSon()
                    else break
                }
                result = son1?.div(son2!!)
            }
        }
        tvques.text = "$son1 $c $son2 = ?"
        a = ArrayList<Int>()
        a!!.clear()
        result?.let { a!!.add(it) }
        for (i in 0..2) {
            var ran: Int = 0
            do {
                var b = false
                ran = random.nextInt(result!! - 20, result!! + 20)
                for (j in 0 until a!!.size) {
                    if (a!![j] == ran) b = true
                }
            } while (b)
            a!!.add(ran)
        }
        a!!.shuffle()
        if (a!![0].toString().length > 3) button1.setTextSize(25F)
        else button1.setTextSize(35F)
        button1.text = a!![0].toString()
        if (a!![1].toString().length > 3) button2.setTextSize(25F)
        else button2.setTextSize(35F)
        button2.text = a!![1].toString()
        if (a!![2].toString().length > 3) button3.setTextSize(25F)
        else button3.setTextSize(35F)
        button3.text = a!![2].toString()
        if (a!![3].toString().length > 3) button4.setTextSize(25F)
        else button4.setTextSize(35F)
        button4.text = a!![3].toString()
    }

    private fun randSon() {
        son1 = random.nextInt(100) + 1
        son2 = random.nextInt(100) + 1
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View?) {
        var id = v?.id

        when (id) {
            R.id.button1 -> {
                if (result != a?.get(0)) {
                    incorrect++
                    if (score < 5) score = 0
                    else score -= 5
                    gameScore.text = score.toString()
                    barStatus -= 10
                    tvSplash.setTextColor(Color.RED)
                } else {
                    correct++
                    score += 10
                    gameScore.text = score.toString()
                    if (barStatus < 55)
                        barStatus += 5
                    else barStatus = 60
                    tvSplash.setTextColor(R.color.purple_500)
                }
                animTextView()
            }
            R.id.button2 -> {
                if (result != a?.get(1)) {
                    incorrect++
                    if (score < 5) score = 0
                    else score -= 5
                    gameScore.text = score.toString()
                    barStatus -= 10
                    tvSplash.setTextColor(Color.RED)
                } else {
                    correct++
                    score += 10
                    gameScore.text = score.toString()
                    if (barStatus < 55)
                        barStatus += 5
                    else barStatus = 60
                    tvSplash.setTextColor(R.color.purple_500)
                }
                animTextView()
            }
            R.id.button3 -> {
                if (result != a?.get(2)) {
                    incorrect++
                    if (score < 5) score = 0
                    else score -= 5
                    gameScore.text = score.toString()
                    barStatus -= 10
                    tvSplash.setTextColor(Color.RED)
                } else {
                    correct++
                    score += 10
                    gameScore.text = score.toString()
                    if (barStatus < 55)
                        barStatus += 5
                    else barStatus = 60
                    tvSplash.setTextColor(R.color.purple_500)
                }
                animTextView()
            }
            R.id.button4 -> {
                if (result != a?.get(3)) {
                    incorrect++
                    if (score < 5) score = 0
                    else score -= 5
                    gameScore.text = score.toString()
                    barStatus -= 10
                    tvSplash.setTextColor(Color.RED)
                } else {
                    correct++
                    score += 10
                    gameScore.text = score.toString()
                    if (barStatus < 55)
                        barStatus += 5
                    else barStatus = 60
                    tvSplash.setTextColor(R.color.purple_500)
                }
                animTextView()
            }
        }
    }

    fun animTextView() {
        tvSplash.text = tvques.text
        setElements()
        val bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_top)
        val move = AnimationUtils.loadAnimation(this, R.anim.move_top)
        tvques.startAnimation(bottom)
        tvSplash.visibility = View.VISIBLE
        tvSplash.startAnimation(move)
    }
}