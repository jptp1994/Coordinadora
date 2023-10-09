package com.bankingapp.data.repository


import com.bankingapp.data.firestore.home.FireStoreHome
import com.bankingapp.data.firestore.user.FireStoreUser
import com.bankingapp.domain.model.HomeResponseEntity
import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.repository.HomeRepository
import com.bankingapp.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val fireStoreUser: FireStoreHome,
): HomeRepository {

    //TODO: validar como hacer el mapper para user en data
    override suspend fun getData(idUser: String): Flow<Response<HomeResponseEntity?>> = flow {
        emit(fireStoreUser.getData(idUser))
    }
}