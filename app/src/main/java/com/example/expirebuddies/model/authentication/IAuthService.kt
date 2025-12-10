package com.example.expirebuddies.model.authentication

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface IAuthService {
    suspend fun createUser(user: User): String
    suspend fun login(user: AuthenticationUser): String?
    fun logout()
}

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
): IAuthService {
    override suspend fun createUser(user: User): String {
        val result=auth.createUserWithEmailAndPassword(user.email,user.password).await()
        return result.user?.uid ?: ""
    }

    override suspend fun login(user: AuthenticationUser): String? {
        val result=auth.signInWithEmailAndPassword(user.email,user.password).await()
        return result.user?.uid ?: ""
    }

    override fun logout() {
       auth.signOut()
    }


}