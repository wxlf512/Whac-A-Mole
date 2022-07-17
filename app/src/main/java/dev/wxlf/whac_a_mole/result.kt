package dev.wxlf.whac_a_mole

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class result : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultScore: TextView = findViewById(R.id.resultScore)
        resultScore.text = this.resources.getString(R.string.currentScore) + " " + intent.getIntExtra("currentScore", 0)

        val resultRecord: TextView = findViewById(R.id.resultRecord)
        val pref: SharedPreferences = getSharedPreferences("score", Context.MODE_PRIVATE)
        resultRecord.text = this.resources.getString(R.string.record) + " " + pref.getInt("record", 0).toString()

        val menuBtn: Button = findViewById(R.id.menuBtn)
        menuBtn.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }
}