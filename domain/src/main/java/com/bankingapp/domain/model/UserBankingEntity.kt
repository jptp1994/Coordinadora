package com.bankingapp.domain.model

import java.util.Date

data class UserBankingEntity(
    val balance:Double ? = null,
    val bankNumber:String ? = null,
    val brandName: String ? = null,
    val dateCreated: Date ? = null,
    val idUser:String ? = null,
    val imageBrand:String ? = null,
    val typeAccountImage:String ? = null,
    val typeAccountName:String ? = null,
//    var movementEntities: List<MovementEntity> ? = null
)
