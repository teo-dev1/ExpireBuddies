package com.example.expirebuddies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.expirebuddies.model.remotedata.remoterepository.remote_usecases.RemoteUseCases
import javax.inject.Inject

class UserLoginViewModel @Inject constructor(
    private val remoteUseCases: RemoteUseCases
):ViewModel() {

}