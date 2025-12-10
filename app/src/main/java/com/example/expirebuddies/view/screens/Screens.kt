package com.example.expirebuddies.view.navigation

sealed class Screen(val route:String){
    object Home :Screen("home")
    object Profile:Screen("profile")
    object Food:Screen("food")
}