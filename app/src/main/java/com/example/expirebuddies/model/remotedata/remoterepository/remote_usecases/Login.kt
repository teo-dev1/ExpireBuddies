package com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases

import com.example.expirebuddies.R
import com.example.expirebuddies.model.authentication.User
import com.example.expirebuddies.model.authentication.UserGestor
import com.example.expirebuddies.model.remotedata.remoterepository.IUserRemoteRepository
import javax.inject.Inject

class Login (
    val remoteRepository: IUserRemoteRepository
){
    suspend operator fun invoke(user: User) {
        if (user.name.isBlank() || user.password.isBlank()) throw UserGestor.InvalidUser(R.string.invalid_user.toString())
        remoteRepository.login()
//        userGestor.registerNewUser(user)
    }
}