package com.bankingapp.domain.util

import com.bankingapp.domain.model.MovementEntity
import com.bankingapp.domain.model.UserBankingEntity
import com.bankingapp.domain.model.UserBankingMovementEntity

fun createUserBankingMovementEntity(userBanking: UserBankingEntity,movementList:List<MovementEntity> ?):UserBankingMovementEntity{
    return UserBankingMovementEntity(
        balance = userBanking.balance,
        bankNumber = userBanking.bankNumber,
        brandName = userBanking.brandName,
        dateCreated= userBanking.dateCreated,
        idUser = userBanking.idUser,
        imageBrand = userBanking.imageBrand,
        typeAccountImage = userBanking.typeAccountImage,
        typeAccountName= userBanking.typeAccountName,
        movementEntities = movementList
    )
}