package com.example.expirebuddies.model.authentication

sealed class UserResult{
    data class LoginSucces (val uid:String) : UserResult()
    data class LoginError (val message: String):UserResult()

    data class DeleteProfileSuccess(val message: String):UserResult()
    data class DeleteProfileError(val message: String):UserResult()
}


