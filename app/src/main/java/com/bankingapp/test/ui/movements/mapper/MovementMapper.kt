package com.bankingapp.test.ui.movements.mapper

import com.bankingapp.domain.model.MovementEntity
import com.bankingapp.test.ui.movements.model.Movement
import com.bankingapp.test.utils.base.Mapper
import java.util.Calendar
import javax.inject.Inject


//Mapper Movement Entity - Movement
class MovementMapper @Inject constructor() : Mapper<MovementEntity?, Movement> {

    override fun mapFromEntity(type: MovementEntity?): Movement {
        return Movement(
            idMovement = type?.idMovement ?: "",
            amount = type?.amount ?: 0.0,
            concept = type?.concept ?: "",
            dateCreated = type?.dateCreated ?: Calendar.getInstance().time,
            idAccount = type?.idAccount ?: "",
            typeMovement = type?.typeMovement ?: "",
            typeMovementImage = type?.typeMovementImage ?: ""
        )
    }

    override fun mapToEntity(type: Movement): MovementEntity {
        return MovementEntity(
            idMovement = type.idMovement,
            amount = type.amount,
            concept = type.concept,
            dateCreated = type.dateCreated,
            idAccount = type.idAccount,
            typeMovement = type.typeMovement,
            typeMovementImage = type.typeMovementImage
        )

    }
}
