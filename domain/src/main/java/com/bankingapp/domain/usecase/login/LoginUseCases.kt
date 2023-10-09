package com.bankingapp.domain.usecase.login

import com.bankingapp.domain.usecase.register.AddUser

data class LoginUseCases (
    val getUser: GetUser,
    val getLastUser: GetLastUser,
    val addUser: AddUser,
    val updateUser: UpdateUser
)