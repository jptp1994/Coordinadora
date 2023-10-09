package com.bankingapp.data.repository

import com.bankingapp.data.firestore.home.FireStoreHome
import com.bankingapp.domain.model.HomeResponseEntity
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.repository.HomeRepository
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