package com.example.expirebuddies.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expirebuddies.model.authentication.User
import com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases.CreateUser
import com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases.RemoteUseCases
import com.squareup.okhttp.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCreationViewModel @Inject constructor(
    private val remoteUseCases: RemoteUseCases
):ViewModel() {

    private val _email = mutableStateOf("")
    private val _password= mutableStateOf("")
    private val _date = mutableStateOf("")

    val email: State<String> = _email


    public fun onEvent(event:UserCreationEvent){
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