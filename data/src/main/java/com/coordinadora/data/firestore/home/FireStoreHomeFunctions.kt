package com.coordinadora.data.firestore.home

import com.coordinadora.domain.model.HomeResponseEntity
import com.coordinadora.domain.model.Response

interface FireStoreHomeFunctions {
    suspend fun getData(idUser:Long) : Response<HomeResponseEntity?>
}