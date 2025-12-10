package com.example.expirebuddies.model.localrepository

import com.example.expirebuddies.model.authentication.User
import com.example.expirebuddies.model.authentication.UserActionResult
import com.example.expirebuddies.model.remotedata.remoterepository.IUserRemoteRepository

class UserRemoteRepository:IUserRemoteRepository {
    override suspend fun createUser(user: User){

    }

    override suspend fun login():String?{
return ""//TODO da implementare, solo per togliere errori build
    }

}