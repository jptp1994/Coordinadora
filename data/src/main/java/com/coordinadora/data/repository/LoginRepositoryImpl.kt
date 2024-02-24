package com.coordinadora.data.repository


import com.coordinadora.data.firestore.user.FireStoreUser
import com.coordinadora.domain.model.UserEntity
import com.coordinadora.domain.model.Response
import com.coordinadora.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val fireStoreUser: FireStoreUser,
): LoginRepository {

    override suspend fun getUser(email: String, password: String): Flow<Response<UserEntity?>> = flow {
        emit(fireStoreUser.getUser(email, password))
    }


    override suspend fun updateUser(idUser: Long, lastConnection: Date, isBlocked:Boolean): Flow<Response<Boolean>> = flow {
        emit(fireStoreUser.updateUser(idUser,lastConnection, isBlocked))
    }
}