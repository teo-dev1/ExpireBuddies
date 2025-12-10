package com.example.expirebuddies.model.authentication

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface IUserRemoteDataSource {
    suspend fun addUserToCollection(user: User)
    suspend fun deleteUserProfile(user: User)
}

class FirestoreRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
):IUserRemoteDataSource{
    override suspend fun addUserToCollection(user: User) {
        firestore.collection("users")
            .document(user.uid)
            .set(user)
            .await()
    }

    override suspend fun deleteUserProfile(user: User){
        firestore.collection("users")
            .document(user.uid)
            .delete()
            .await()
    }

}