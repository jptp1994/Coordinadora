package com.bankingapp.data.repository


import com.bankingapp.data.firestore.user.FireStoreUser
import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val fireStoreUser: FireStoreUser,
): LoginRepository {


    //TODO: validar como hacer el mapper para user en data
    override suspend fun getUser(email: String, password: String): Flow<Response<UserEntity?>> = flow {
        emit(fireStoreUser.getUser(email, password))
    }

    override suspend fun addUser(title: String, author: String): Flow<Response<Boolean>> = flow {
        emit(fireStoreUser.saveUser())
    }

    override suspend fun updateUser(bookId: String): Flow<Response<Boolean>> = flow {
        emit(fireStoreUser.updateUser())
    }
}