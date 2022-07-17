package dev.wxlf.whac_a_mole

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playBtn: Button = findViewById(R.id.playBtn)
        val recordView: TextView = findViewById(R.id.record)
        val pref: SharedPreferences = getSharedPreferences("score", Context.MODE_PRIVATE)
        recordView.text = this.resources.getString(R.string.record) + "\n" + pref.getInt("record", 0).toString()
        playBtn.setOnClickListener {
            val intent = Intent(this, game::class.java)
            startActivity(intent)
        }
    }
}