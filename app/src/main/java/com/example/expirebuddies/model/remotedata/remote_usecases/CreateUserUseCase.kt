package com.example.expirebuddies.model.remotedata.remote_usecases

import com.example.expirebuddies.R
import com.example.expirebuddies.model.authentication.AuthenticationUser
import com.example.expirebuddies.model.authentication.IUserGestor
import com.example.expirebuddies.model.authentication.UserGestor
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
private val userGestor: IUserGestor
/*vedere se sostituire con repo*/
){
    suspend operator fun invoke(user:AuthenticationUser){
        if(user.name.isBlank() || user.password.isBlank())throw UserGestor.InvalidUser(R.string.invalid_user.toString())
        userGestor.RegisterNewUser(user)
    }
}