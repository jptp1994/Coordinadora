package com.bankingapp.domain.usecase.login

import com.bankingapp.domain.repository.LoginRepository

class UpdateUser(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(bookId: String) = repo.updateUser(bookId)
}