package com.bankingapp.test.ui.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.usecase.login.LoginUseCases
import com.bankingapp.test.ui.login.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCases: LoginUseCases
) : ViewModel()  {

    //handle the progressbar state
    var isLoading = MutableLiveData(false)

    //get the response for get last user
    var lastUserResponse = MutableLiveData<Response<Long>>(Response.Initial)
        private set


    //get the response for get last user
    var createUserResponse = MutableLiveData<Response<Boolean>>(Response.Initial)
        private set

    fun getLastUser() = viewModelScope.launch {
        lastUserResponse.value = Response.Loading
        useCases.getLastUser.invoke().collect { response ->
            lastUserResponse.value = response
        }
    }

    fun createUser(userData: User) = viewModelScope.launch {
        createUserResponse.value = Response.Loading
        useCases.addUser.invoke(userData.idUser,
            userData.imageUser,
            userData.age,
            userData.name,
            userData.lastConnection,
            userData.lastName,
            userData.email,
            userData.password,
            userData.dateBirth
            ).collect { response ->
            createUserResponse.value = response
        }
    }


}