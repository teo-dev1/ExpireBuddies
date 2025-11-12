package com.example.expirebuddies.model.usecases

import com.example.expirebuddies.model.remotedata.PostResponse
import com.example.expirebuddies.model.remotedata.remoterepository.RemoteRepository

class GetRemoteData(
    val remoteRepository:RemoteRepository
) {
   suspend operator fun invoke():List<PostResponse>{
        return remoteRepository.getData()
    }
}