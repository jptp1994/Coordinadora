package com.bankingapp.test.ui.home.model

import android.os.Parcelable
import com.bankingapp.test.ui.movements.model.Movement
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class UserBanking(
    val balance:Double,
    val bankNumber:String,
    val brandName:String,
    val dateCreated: Date,
    val idUser:Long,
    val imageBrand:String,
    val typeAccountImage:String,
    val typeAccountName:String,
    val movementEntities: List<Movement>
):Parcelable
