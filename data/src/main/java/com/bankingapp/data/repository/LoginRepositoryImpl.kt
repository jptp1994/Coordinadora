package com.bankingapp.data.repository


import com.bankingapp.data.firestore.user.FireStoreUser
import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.repository.LoginRepository
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

    override suspend fun getLastUser(): Flow<Response<Long>> = flow {
        emit(fireStoreUser.getLastUser())
    }


    override suspend fun addUser(
        idUser: Long,
        imageUser: String,
        age: String,
        name: String,
        lastConnection: Date,
        lastName: String,
        email: String,
        password: String,
        dateBirth: Date?
    ): Flow<Response<Boolean>> = flow {
        emit(fireStoreUser.saveUser(idUser,imageUser,age,name,lastConnection,lastName,email, password,
            dateBirth))
    }

    override suspend fun updateUser(idUser: Long, lastConnection: Date): Flow<Response<Boolean>> = flow {
        emit(fireStoreUser.updateUser(idUser,lastConnection))
    }
}