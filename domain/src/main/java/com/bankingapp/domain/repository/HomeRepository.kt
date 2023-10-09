package com.bankingapp.domain.repository

import com.bankingapp.domain.model.HomeResponseEntity
import com.bankingapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getData(idUser:Long): Flow<Response<HomeResponseEntity?>>
}