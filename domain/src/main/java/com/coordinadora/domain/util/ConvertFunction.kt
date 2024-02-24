package com.coordinadora.domain.util

import com.coordinadora.domain.model.MovementEntity
import com.coordinadora.domain.model.UserBankingEntity
import com.coordinadora.domain.model.UserBankingMovementEntity

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