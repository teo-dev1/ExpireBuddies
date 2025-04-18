package com.example.expirebuddies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.expirebuddies.view.components.FoodList
import com.example.expirebuddies.view.components.MainScreen
import com.example.expirebuddies.view.components.mockFoodList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent {
            MainScreen()
        }
    }
}