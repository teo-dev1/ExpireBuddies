package com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases

import com.example.expirebuddies.model.authentication.IUserGestor
import com.example.expirebuddies.model.authentication.User
import javax.inject.Inject

class DeleteUser @Inject constructor(
    val userGestor: IUserGestor
) {
   suspend operator fun invoke(user:User){
       userGestor.deleteUser(user)
   }
}