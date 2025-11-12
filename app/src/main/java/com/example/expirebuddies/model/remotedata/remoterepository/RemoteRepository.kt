package com.example.expirebuddies.model.remotedata.remoterepository

import com.example.expirebuddies.model.remotedata.ApiSerivceImplementation
import com.example.expirebuddies.model.remotedata.ApiService
import com.example.expirebuddies.model.remotedata.PostResponse
import javax.inject.Inject

interface IRemoteRepository{
    suspend fun getData():List<PostResponse>
}

class RemoteRepository @Inject constructor(private val api:ApiSerivceImplementation):IRemoteRepository {

    override suspend fun getData():List<PostResponse>{
        return api.getData()
    }


}