package com.bankingapp.domain.repository

import com.bankingapp.domain.model.Response
import com.bankingapp.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow


interface LoginRepository {
    suspend fun getUser(email:String, password:String): Flow<Response<UserEntity?>>

    suspend fun addUser(title: String, author: String): Flow<Response<Boolean>>

    suspend fun updateUser(bookId: String): Flow< Response<Boolean>>
}