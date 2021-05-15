package com.example.aminotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstYear.setOnClickListener {
            startActivity(Intent(this,FirstYearActivity::class.java))
        }
        secondYear.setOnClickListener {

        }
        thirdYear.setOnClickListener {

        }
        fourthYear.setOnClickListener {

        }

    }
}