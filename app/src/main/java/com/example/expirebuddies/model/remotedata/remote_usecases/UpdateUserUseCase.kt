package com.example.expirebuddies.model.remotedata.remote_usecases

import com.example.expirebuddies.model.authentication.AuthenticationUser
import com.example.expirebuddies.model.authentication.IUserGestor

class UpdateUserUseCase(
    val userGestor: IUserGestor
) {
    suspend operator fun invoke(user: AuthenticationUser){
        userGestor.UpdateUser(user)
    }
}