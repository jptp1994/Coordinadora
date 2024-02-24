package com.coordinadora.domain.repository

import com.coordinadora.domain.model.Response
import com.coordinadora.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date


interface LoginRepository {
    suspend fun getUser(email:String, password:String): Flow<Response<UserEntity?>>


    suspend fun updateUser(idUser: Long, lastConnection: Date, isBlocked:Boolean): Flow< Response<Boolean>>
}