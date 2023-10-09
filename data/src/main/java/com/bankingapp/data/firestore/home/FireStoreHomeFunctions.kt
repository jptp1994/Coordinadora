package com.bankingapp.data.firestore.home

import com.bankingapp.domain.model.HomeResponseEntity
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.model.UserEntity

interface FireStoreHomeFunctions {
    suspend fun getData(idUser:String) : Response<HomeResponseEntity?>
}