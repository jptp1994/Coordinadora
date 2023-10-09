package com.bankingapp.data.firestore.user

import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.model.Response
import java.util.Date


interface FireStoreUserFunctions {

    suspend fun saveUser(
        idUser: Long,
        imageUser: String,
        age: String,
        name: String,
        lastConnection: Date,
        lastName: String,
        email: String,
        password: String,
        dateBirth: Date?
    ):Response<Boolean>
    suspend fun getUser(email:String,password:String) : Response<UserEntity?>

    suspend fun getLastUser() : Response<Long>

    suspend fun updateUser(idUser: Long, lastConnection: Date): Response<Boolean>

}