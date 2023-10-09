package com.bankingapp.test.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.usecase.login.LoginUseCases
import com.bankingapp.test.ui.login.mapper.UserMapper
import com.bankingapp.test.ui.login.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


//Handle the loginUseCases and the mapper
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCases,
    private val userMapper: UserMapper
):ViewModel() {

    //handle the progressbar state
    var isLoading = MutableLiveData(false)

    //get the response for login
    var loginResponse = MutableLiveData<Response<UserEntity?>>(Response.Initial)
        private set

    var updateConnection = MutableLiveData<Response<Boolean>>(Response.Initial)
        private set


    //validate the user data by email and password
    fun getUser(email: String, password: String) = viewModelScope.launch {
        loginResponse.value = Response.Loading
        useCases.getUser.invoke(email,password).collect { response ->
            loginResponse.value = response
        }
    }

    fun updateConnection(idUser:Long, lastConnection: Date) = viewModelScope.launch {
        updateConnection.value = Response.Loading
        useCases.updateUser.invoke(idUser,lastConnection).collect { response ->
            updateConnection.value = response
        }
    }


    // convert user entity to user
    fun convertData(it1: UserEntity): User {
      return  userMapper.mapFromEntity(it1)
    }
}

