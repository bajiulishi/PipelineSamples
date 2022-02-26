package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
private const val TAG = "MainActivity1"

class MainActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
    }
}