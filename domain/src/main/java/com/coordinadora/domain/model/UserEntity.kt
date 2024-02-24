package com.coordinadora.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class UserEntity(
    val idUser:Long ? = 0,
    val name:String ? = "",
    val lastName:String ? = "",
    val userName:String ? = "",
    val password:String ? = "",
    val lastConnection: Date ? = null,
    val isBlocked:Boolean? = false,
    val equipo:String ? = "",
    val terminal: String? = "",
    val rol: String? = "",
):Parcelable
