package com.example.expirebuddies.model.authentication

data class User(
    val uid:String="",
    val name:String="",
    val email:String="",
    val createdAt: Long =System.currentTimeMillis()
)

data class AuthenticationUser(
    val uid:String="",
    val name:String="",
    val email:String="",
    val password:String="",
    val createdAt: Long =System.currentTimeMillis()
)
