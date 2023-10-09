package com.bankingapp.domain.model

import java.util.Date

data class UserBankingMovementEntity(
    val balance:Double ? = null,
    val bankNumber:String ? = null,
    val brandName: String ? = null,
    val dateCreated: Date ? = null,
    val idUser:Long ? = null,
    val imageBrand:String ? = null,
    val typeAccountImage:String ? = null,
    val typeAccountName:String ? = null,
    val movementEntities: List<MovementEntity> ? = null
)
