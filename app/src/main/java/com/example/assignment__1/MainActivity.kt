package com.example.assignment__1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var showCounterValue: TextView
    private lateinit var increaseCounter: Button
    private var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showCounterValue = findViewById<TextView>(R.id.counterText)
        increaseCounter = findViewById<Button>(R.id.count)

        increaseCounter.setOnClickListener {
            count++
            showCounterValue.text = "Click Count = " + count

        }
    }

    override fun onResume() {
        super.onResume()
        count = 0
        showCounterValue.text = "Click count  = " + count
    }

    override fun onStop() {
        super.onStop()
        count = 0
        showCounterValue.text = "Click count  = " + count
    }
}