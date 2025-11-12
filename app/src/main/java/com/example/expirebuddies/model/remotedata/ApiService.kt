package com.example.expirebuddies.model.remotedata

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import javax.inject.Inject

interface ApiService {
    suspend fun getData():List<PostResponse>

    suspend fun createData(postRequest:PostRequest):PostResponse?
}


class ApiSerivceImplementation @Inject constructor(
    private val client: HttpClient
):ApiService{
    override suspend fun getData(): List<PostResponse> {
        return try {
            client.get {
                url(HttpRoutes.POSTS)
            }.body()
        }catch (e:Exception){
            println("ERRORE $e")
            return emptyList()
        }
    }

    override suspend fun createData(postRequest: PostRequest): PostResponse? {
        TODO("Not yet implemented")
    }

}