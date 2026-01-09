package com.example.expirebuddies.view.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expirebuddies.view.colors.AppTheme
import com.example.expirebuddies.view.navigation.BottomNavigationBar

@Composable
fun Home() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(rootNavController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "home"
        ) {
            composable("food") {
                FoodListScreen()
            }
            composable("profile") {
                UserCreationScreen()
            }

            composable("home") {
                HomeScreen()
            }
        }
    }

}
