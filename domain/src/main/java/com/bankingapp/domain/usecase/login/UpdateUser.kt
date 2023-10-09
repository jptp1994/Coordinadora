package com.bankingapp.domain.usecase.login

import com.bankingapp.domain.repository.LoginRepository
import java.util.Date

class UpdateUser(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(idUser: Long, lastConnection: Date) = repo.updateUser(idUser, lastConnection)
}