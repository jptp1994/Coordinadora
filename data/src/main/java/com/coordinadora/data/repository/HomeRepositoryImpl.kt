package com.coordinadora.data.repository

import com.coordinadora.data.firestore.home.FireStoreHome
import com.coordinadora.domain.model.HomeResponseEntity
import com.coordinadora.domain.model.Response
import com.coordinadora.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val fireStoreUser: FireStoreHome,
): HomeRepository {

    override suspend fun getData(idUser: Long): Flow<Response<HomeResponseEntity?>> = flow {
        emit(fireStoreUser.getData(idUser))
    }
}