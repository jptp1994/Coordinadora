package com.coordinadora.domain.usecase.login

import com.coordinadora.domain.repository.LoginRepository
import java.util.Date

class UpdateUser(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(idUser: Long, lastConnection: Date, isBlocked:Boolean) = repo.updateUser(idUser, lastConnection, isBlocked)
}