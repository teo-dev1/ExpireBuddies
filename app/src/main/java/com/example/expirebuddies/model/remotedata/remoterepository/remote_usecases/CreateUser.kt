package com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases

import com.example.expirebuddies.R
import com.example.expirebuddies.model.authentication.User
import com.example.expirebuddies.model.authentication.UserCreationResult
import com.example.expirebuddies.model.authentication.UserGestor
import com.example.expirebuddies.model.remotedata.remoterepository.IUserRemoteRepository
import javax.inject.Inject

class CreateUser(
    val remoteRepository: IUserRemoteRepository
/*private val userGestor: IUserGestor*/
/*vedere se sostituire con repo*/
) {
    suspend operator fun invoke(user: User) {//TODO inserire tipo di ritorno String o String? perchè deve ritornare l id dell utente
        if (user.name.isBlank() || user.password.isBlank()) throw UserGestor.InvalidUser(R.string.invalid_user.toString())
        remoteRepository.createUser(user)
//        userGestor.registerNewUser(user)
    }
}