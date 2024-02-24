package com.coordinadora.domain.repository

import com.coordinadora.domain.model.HomeResponseEntity
import com.coordinadora.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getData(idUser:Long): Flow<Response<HomeResponseEntity?>>
}