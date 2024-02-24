package com.coordinadora.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class MovementEntity(
    val idMovement:String ? = null,
    val amount:Double ?= null,
    val concept:String ? = null,
    val dateCreated: Date ? = null,
    val idAccount:String ? = null,
    val typeMovement:String ? = null,
    val typeMovementImage:String ? = null,
    ):Parcelable
