package com.junaid.todoapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.junaid.todoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}