package com.coordinadora.domain.usecase.login

import com.coordinadora.domain.repository.LoginRepository


class GetUser (
    private val repo: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String) = repo.getUser(email,password)
}