package com.candidate.android.dev.wongnai_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.candidate.android.dev.wongnai_assignment.Extension.replaceScreen
import com.candidate.android.dev.wongnai_assignment.Screen.MainScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) replaceScreen(MainScreen())
    }


}