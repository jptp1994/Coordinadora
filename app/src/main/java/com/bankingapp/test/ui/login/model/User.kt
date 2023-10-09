package com.bankingapp.test.ui.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class User(
    var idUser:Long,
    val name:String,
    val lastName:String,
    val age:String,
    val dateBirth: Date ? = null,
    val email:String,
    val password:String,
    val lastConnection:Date,
    var imageUser:String
):Parcelable
