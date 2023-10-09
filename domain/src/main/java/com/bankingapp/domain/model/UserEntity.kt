package com.bankingapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class UserEntity(
    val idUser:String ? = "",
    val name:String ? = "",
    val lastName:String ? = "",
    val age:String ? = "",
    val dateBirth: Date ? = null,
    val email:String ? = "",
    val password:String ? = "",
    val lastConnection: Date ? = null,
    val imageUser: String ? = null
):Parcelable
