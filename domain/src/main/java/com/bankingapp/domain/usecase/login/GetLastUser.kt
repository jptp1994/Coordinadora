package com.bankingapp.domain.usecase.login

import com.bankingapp.domain.repository.LoginRepository


class GetLastUser (
    private val repo: LoginRepository
) {
    suspend operator fun invoke() = repo.getLastUser()
}