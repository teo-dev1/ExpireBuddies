package com.example.expirebuddies.model.authentication

import android.util.Log
import javax.inject.Inject

interface IUserGestor {
    suspend fun registerNewUser(user: User): UserCreationResult
    suspend fun updateUser(user: User): UserActionResult
    suspend fun deleteUser(user: User): UserActionResult
    suspend fun login(user: AuthenticationUser):String?
    suspend fun logout()

}

class UserGestor @Inject constructor(
    private val authService: IAuthService,
    private val firestore: IUserRemoteDataSource  //le responsabilità andrebbero separate perchè creare la collection di user è un compito che si può astrarre, per ora rimane cosi
) : IUserGestor {
    override suspend fun registerNewUser(user: User): UserCreationResult {
        return try {
            // 1. Registra l'utente su Firebase Authentication
            val uid = authService.createUser(user)

            // 2. Crea il profilo completo da salvare su Firestore
            val newUser = user.copy(uid = uid)

            // 3. Salva su Firestore
            firestore.addUserToCollection(newUser)

            println("PROFILO FATTO")
            // 4. Risultato finale
            UserCreationResult.CreationProfileSuccess("PROFILO CREATO CON SUCCESSO")

        } catch (e: Exception) {
            println("PROFILO NO FATTO")
            UserCreationResult.CreationProfileError("ERRORE REGISTRAZIONE PROFILO")
        }
    }


    override suspend fun updateUser(user: User): UserActionResult {
//stesse istruzioni del registernewuser
        return UserActionResult.LoginSuccess("")//provvisorio, solo per non avere errori e sottolineature rosse momentaneamente
    }

    override suspend fun deleteUser(user: User): UserActionResult {
        return try {
            firestore.deleteUserProfile(user)
            UserActionResult.DeleteProfileSuccess("Profilo eliminato")
        } catch (e: Exception) {
            UserActionResult.DeleteProfileError(e.message ?: "Errore eliminazione profilo")
        }
    }

    override suspend fun login(user:AuthenticationUser): String? {//TODO cambiare con tipo differente, con cosa ritorna il login di firebaseauth
        return try {
            authService.login(user)
        }catch (e:Exception){
            Log.e("ERRORE LOGIN",e.message.toString())
            "USER NOT FOUND"
        }

    }

    override suspend fun logout() {
       authService.logout()
    }

    class InvalidUser (errorMessage:String) : Exception(errorMessage)
}



