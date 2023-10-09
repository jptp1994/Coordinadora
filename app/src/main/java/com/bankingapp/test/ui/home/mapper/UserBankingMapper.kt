package com.bankingapp.test.ui.home.mapper

import com.bankingapp.domain.model.UserBankingMovementEntity
import com.bankingapp.test.ui.home.model.UserBanking
import com.bankingapp.test.ui.movements.mapper.MovementMapper
import com.bankingapp.test.utils.base.Mapper
import java.util.Calendar
import javax.inject.Inject

//Mapper UserBankingMovementEntity - UserBanking
class UserBankingMapper @Inject constructor(
    private val movementMapper: MovementMapper
) : Mapper<UserBankingMovementEntity?, UserBanking> {


    override fun mapFromEntity(type: UserBankingMovementEntity?): UserBanking {
        return UserBanking(
            balance = type?.balance ?: 0.0,
            bankNumber = type?.bankNumber ?: "",
            brandName = type?.brandName ?: "",
            dateCreated =  type?.dateCreated ?: Calendar.getInstance().time,
            idUser = type?.idUser ?: 0,
            imageBrand =  type?.imageBrand ?: "",
            typeAccountName = type?.typeAccountName ?: "",
            typeAccountImage =  type?.typeAccountImage ?: "",
            movementEntities = type?.movementEntities?.map {
                movementMapper.mapFromEntity(it)
            }?: emptyList()
        )
    }

    override fun mapToEntity(type: UserBanking): UserBankingMovementEntity {
        return UserBankingMovementEntity(
            balance = type.balance,
            bankNumber = type.bankNumber,
            brandName = type.brandName,
            dateCreated =  type.dateCreated,
            idUser = type.idUser,
            imageBrand =  type.imageBrand,
            typeAccountName = type.typeAccountName,
            typeAccountImage =  type.typeAccountImage,
            movementEntities = type.movementEntities.map {
                movementMapper.mapToEntity(it)
            }
        )
    }
}
