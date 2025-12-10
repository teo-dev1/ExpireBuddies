package com.example.expirebuddies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expirebuddies.model.authentication.User
import com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases.CreateUser
import com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases.RemoteUseCases
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserCreationViewModel @Inject constructor(
    private val remoteUseCases: RemoteUseCases
):ViewModel() {

    fun onEvent(event:UserCreationEvent){
        when(event){
            is UserCreationEvent.CreateUser ->{
                viewModelScope.launch(Dispatchers.IO){
                    remoteUseCases.createUser(event.user)
                }
            }
        }
    }



}




sealed class UserCreationEvent(){
    data class CreateUser(val user: User): UserCreationEvent()
}

//COSA DOVREBBE ESSERE TRATTATO COME STATE
//lista utenti
//
//profilo utente
//
//stato del login (logged / not logged)
//
//stato del caricamento: loading / success / error
//
//un contatore, timer, input di un form
//
//stato dei campi TextField