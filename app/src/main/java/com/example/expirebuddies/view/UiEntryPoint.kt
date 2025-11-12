package com.example.expirebuddies.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expirebuddies.view.screens.Home

@Composable
fun UiEntryPoint(){
    val navController = rememberNavController()

    NavHost(
        navController,
        "home"
    ){
        //composable("home"){ Home(navController) }
    }
}