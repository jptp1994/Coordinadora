package com.bankingapp.test.ui.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class User(
    val idUser:String,
    val name:String,
    val lastName:String,
    val age:String,
    val dateBirth: Date ? = null,
    val email:String,
    val password:String,
    val lastConnection:Date,
    val imageUser:String
):Parcelable
