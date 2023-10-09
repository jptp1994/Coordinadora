package com.bankingapp.test.ui.login.mapper

import com.bankingapp.domain.model.UserEntity
import com.bankingapp.test.ui.login.model.User
import com.bankingapp.test.utils.base.Mapper
import java.util.Calendar
import javax.inject.Inject

//Mapper User Entity - User
class UserMapper @Inject constructor() : Mapper<UserEntity?, User> {

    override fun mapFromEntity(type: UserEntity?): User {
        return User(
            idUser = type?.idUser ?: 0,
            name = type?.name ?: "",
            lastName = type?.lastName ?: "",
            age = type?.age ?: "",
            dateBirth = type?.dateBirth,
            email = type?.email ?: "",
            password = type?.password ?: "",
            lastConnection = type?.lastConnection ?: Calendar.getInstance().time,
            imageUser = type?.imageUser ?: ""
        )
    }

    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(
            idUser = type.idUser,
            name = type.name,
            lastName = type.lastName,
            age= type.age,
            dateBirth = type.dateBirth,
            email = type.email,
            password = type.password,
            lastConnection = type.lastConnection,
            imageUser = type.imageUser
        )
    }
}
