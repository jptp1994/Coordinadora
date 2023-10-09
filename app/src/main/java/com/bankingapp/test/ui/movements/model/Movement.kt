package com.bankingapp.test.ui.movements.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Movement(
    val idMovement:String,
    val amount:Double,
    val concept:String,
    val dateCreated: Date,
    val idAccount:String,
    val typeMovement:String,
    val typeMovementImage:String,
    ):Parcelable
