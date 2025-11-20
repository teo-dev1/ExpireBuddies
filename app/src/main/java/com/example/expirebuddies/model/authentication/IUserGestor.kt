package com.example.expirebuddies.model.authentication

import com.example.expirebuddies.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

interface IUserGestor {
    suspend fun RegisterNewUser(user: AuthenticationUser): UserResult
    suspend fun UpdateUser(user: AuthenticationUser)
    suspend fun DeleteUser(user: AuthenticationUser): UserResult

}

class UserGestor(
    private val authService: IAuthService,
    private val firestore: FirebaseFirestore
) : IUserGestor {
    override suspend fun RegisterNewUser(user: AuthenticationUser): UserResult {
        return try {
            val uid = authService.register(user)
            val profileToSave = AuthenticationUser(
                uid = uid,
                name = user.name,
                email = user.email,
                createdAt = user.createdAt
            )
            firestore.collection("users")
                .document(uid)
                .set(profileToSave)
                .await()
            return UserResult.LoginSucces(uid)
        } catch (e: Exception) {
            return UserResult.LoginError("ERRORE REGISTRAZIONE PROFILO")
        }
    }

    override suspend fun UpdateUser(user: AuthenticationUser) {

    }

    override suspend fun DeleteUser(user: AuthenticationUser): UserResult {
        return try {
            firestore.collection("users")
                .document(user.uid)
                .delete()
                .await()

            UserResult.DeleteProfileSuccess("Profilo eliminato")
        } catch (e: Exception) {
            UserResult.DeleteProfileError(e.message ?: "Errore eliminazione profilo")
        }
    }

    class InvalidUser (errorMessage:String) : Exception(errorMessage)
}



