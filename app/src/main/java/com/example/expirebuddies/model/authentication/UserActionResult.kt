package com.example.expirebuddies.model.authentication

sealed class UserActionResult{//obsoleta, usare resource che sta in general
    data class LoginSuccess (val uid:String) : UserActionResult()
    data class LoginError (val message: String):UserActionResult()

    data class DeleteProfileSuccess(val message: String):UserActionResult()
    data class DeleteProfileError(val message: String):UserActionResult()


}


sealed class UserCreationResult(){
    data class CreationProfileSuccess(val message: String):UserCreationResult()
    data class CreationProfileError(val message: String):UserCreationResult()
}




