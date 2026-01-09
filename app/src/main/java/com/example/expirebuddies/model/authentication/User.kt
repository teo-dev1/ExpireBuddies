package com.example.expirebuddies.model.authentication

data class User(//implementare metodo per convertirlo a json(?)
    val uid: String="",
    val name:String="",
    val email:String="",
    val password: String="",
    val dateOfBirth:Long,
    val createdAt: Long =System.currentTimeMillis()
)

data class AuthenticationUser(//implementare metodo per convertirlo a json(?)
    val uid:String="",
    val name:String="",
    val email:String="",
    val password:String="",
    val createdAt: Long =System.currentTimeMillis()
)
