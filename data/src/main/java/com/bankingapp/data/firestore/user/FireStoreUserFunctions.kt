package com.bankingapp.data.firestore.user

import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.model.Response


interface FireStoreUserFunctions {

    suspend fun saveUser() :Response<Boolean>
    suspend fun getUser(email:String,password:String) : Response<UserEntity?>
    suspend fun updateUser() : Response<Boolean>

}