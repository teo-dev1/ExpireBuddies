package com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases

import com.example.expirebuddies.model.authentication.IUserGestor
import com.example.expirebuddies.model.authentication.User

class UpdateUser(
    val userGestor: IUserGestor
) {
    suspend operator fun invoke(user: User){
        userGestor.updateUser(user)
    }
}