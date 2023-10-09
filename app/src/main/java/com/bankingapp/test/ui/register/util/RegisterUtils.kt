package com.bankingapp.test.ui.register.util

import com.bankingapp.test.ui.login.model.User
import java.util.Calendar
import java.util.Date

object RegisterUtils {

    fun createUserModel(
        name: String,
        lastName: String,
        age: String,
        email: String,
        password: String,
        dateBirth: Date
    ): User {
       return User(
            idUser = 0,
            name = name,
            lastName = lastName,
            age = age,
            email = email,
            password = password,
            dateBirth = dateBirth,
            lastConnection = Calendar.getInstance().time,
            imageUser = ""
        )
    }
}