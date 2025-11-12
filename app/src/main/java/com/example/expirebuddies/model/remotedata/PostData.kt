package com.example.expirebuddies.model.remotedata

@kotlinx.serialization.Serializable
data class PostRequest(
    val body:String,
    val title:String,
    val userId:Int
)
@kotlinx.serialization.Serializable
data class PostResponse(
    val userId: Int,
    val id:Int,
    val title:String,
    val body:String
)
