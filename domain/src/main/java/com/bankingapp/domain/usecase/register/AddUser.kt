package com.bankingapp.domain.usecase.register

import com.bankingapp.domain.repository.LoginRepository
import java.util.Date

class AddUser(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(
        idUser: Long,
        imageUser: String,
        age: String,
        name: String,
        lastConnection: Date,
        lastName: String,
        email: String,
        password: String,
        dateBirth: Date?
    ) = repo.addUser(idUser,imageUser,age,name,lastConnection,lastName,email, password, dateBirth)
}