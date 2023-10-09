package com.bankingapp.domain.usecase.login

import com.bankingapp.domain.repository.LoginRepository


class GetUser (
    private val repo: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String) = repo.getUser(email,password)
}