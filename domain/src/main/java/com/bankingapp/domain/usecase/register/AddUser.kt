package com.bankingapp.domain.usecase.register

import com.bankingapp.domain.repository.LoginRepository

class AddUser(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(
        title: String,
        author: String
    ) = repo.addUser(title, author)
}