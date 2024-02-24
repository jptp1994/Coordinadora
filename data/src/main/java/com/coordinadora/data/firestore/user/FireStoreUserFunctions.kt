package com.coordinadora.data.firestore.user

import com.coordinadora.domain.model.UserEntity
import com.coordinadora.domain.model.Response
import java.util.Date


interface FireStoreUserFunctions {


    suspend fun getUser(email:String,password:String) : Response<UserEntity?>


    suspend fun updateUser(idUser: Long, lastConnection: Date, isBlocked:Boolean): Response<Boolean>

}