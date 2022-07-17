package dev.wxlf.whac_a_mole

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class game : AppCompatActivity() {

    var timer: Int = 30

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val holes = mutableListOf<ImageView>(findViewById(R.id.hole0),
            findViewById(R.id.hole1),
            findViewById(R.id.hole2),
            findViewById(R.id.hole3),
            findViewById(R.id.hole4),
            findViewById(R.id.hole5),
            findViewById(R.id.hole6),
            findViewById(R.id.hole7),
            findViewById(R.id.hole8))
        val timerView: TextView = findViewById(R.id.timer)
        val scoreView: TextView = findViewById(R.id.score)
        var score = 0
        val thread = Thread {
            repeat(60) { i ->
                val j = Random.nextInt(0, 8)
                var flag = false
                runOnUiThread {
                    holes[j].setImageResource(R.drawable.lunka_with_mole)
                    holes[j].setOnClickListener {
                        if (!flag) {
                            flag = true
                            score++
                        }
                    }
                    scoreView.text = this.resources.getString(R.string.currentScore) + " " + score.toString()
                }
                Thread.sleep(500L)
                if (i % 2 == 0) {
                    timer--
                    runOnUiThread {
                        timerView.text = timer.toString()
                    }
                }
                runOnUiThread {
                    holes[j].setImageResource(R.drawable.lunka)
                }
            }

            val pref: SharedPreferences = getSharedPreferences("score", Context.MODE_PRIVATE)
            if (score > pref.getInt("score", 0)) {
                val editor = pref.edit()
                editor.putInt("record", score)
                editor.apply()
            }

            val myIntent = Intent(this, result::class.java)
            myIntent.putExtra("currentScore", score)
            startActivity(myIntent)
        }
        thread.start()
    }
}
