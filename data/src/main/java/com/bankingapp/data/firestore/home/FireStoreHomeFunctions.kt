package com.bankingapp.data.firestore.home

import com.bankingapp.domain.model.HomeResponseEntity
import com.bankingapp.domain.model.Response

interface FireStoreHomeFunctions {
    suspend fun getData(idUser:Long) : Response<HomeResponseEntity?>
}