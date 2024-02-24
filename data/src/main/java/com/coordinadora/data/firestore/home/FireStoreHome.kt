package com.coordinadora.data.firestore.home

import com.coordinadora.domain.model.HomeResponseEntity
import com.coordinadora.domain.model.MovementEntity
import com.coordinadora.domain.model.Response
import com.coordinadora.domain.model.UserBankingEntity
import com.coordinadora.domain.util.createUserBankingMovementEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreHome @Inject constructor(
    private val db: FirebaseFirestore
): FireStoreHomeFunctions {
    private var homeResponseEntity: HomeResponseEntity = HomeResponseEntity(
        bankingList = arrayListOf()
    )
    override suspend fun getData(idUser: Long): Response<HomeResponseEntity?> {
        return try {
            homeResponseEntity.bankingList?.clear()
            val dataBanking = db.collection("routes").whereEqualTo("idUser", idUser).get().await()
            val userBankingList = dataBanking.toObjects(UserBankingEntity::class.java)
            userBankingList.forEach { userBanking ->
                val movementQuery =
                    db.collection("movement").whereEqualTo("idAccount", userBanking.bankNumber)
                        .get().await()
                val movementList = movementQuery.toObjects(MovementEntity::class.java)
                homeResponseEntity.bankingList?.add(createUserBankingMovementEntity(userBanking,movementList))
            }
            Response.Success(homeResponseEntity)
        }catch (e: Exception) {
            Response.Failure(e)
        }
    }
}