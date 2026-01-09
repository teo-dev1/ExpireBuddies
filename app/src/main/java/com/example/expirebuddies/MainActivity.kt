package com.example.expirebuddies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.expirebuddies.view.colors.AppTheme
import com.example.expirebuddies.view.screens.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent {
            AppTheme() {
                Home()
            }
        }
    }
}