package com.bankingapp.domain.repository

import com.bankingapp.domain.model.Response
import com.bankingapp.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date


interface LoginRepository {
    suspend fun getUser(email:String, password:String): Flow<Response<UserEntity?>>

    suspend fun getLastUser(): Flow<Response<Long>>

    suspend fun addUser(
        idUser: Long,
        imageUser: String,
        age: String,
        name: String,
        lastConnection: Date,
        lastName: String,
        email: String,
        password: String,
        dateBirth: Date?
    ): Flow<Response<Boolean>>

    suspend fun updateUser(idUser: Long, lastConnection: Date): Flow< Response<Boolean>>
}