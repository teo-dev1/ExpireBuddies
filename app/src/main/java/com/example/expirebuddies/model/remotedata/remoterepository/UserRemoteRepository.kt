package com.example.expirebuddies.model.remotedata.remoterepository

import com.example.expirebuddies.model.authentication.IUserGestor
import com.example.expirebuddies.model.authentication.User
import com.example.expirebuddies.model.authentication.UserActionResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IUserRemoteRepository {
    suspend fun createUser(user: User) //restituisce se azione andata a buon fine o no
    suspend fun login( ):String?//restituisce id
}

class UserRemoteRepository @Inject constructor(
   private val gestor: IUserGestor
    ):IUserRemoteRepository{
    override suspend fun createUser(user: User){
        gestor.registerNewUser(user)
    }

    override suspend fun login(): String? {
        TODO("Not yet implemented")
    }

}