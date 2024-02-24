package com.coordinadora.data.firestore.user

import com.coordinadora.data.ConstantsData.EMAIL
import com.coordinadora.data.ConstantsData.ID_USER
import com.coordinadora.data.ConstantsData.PASSWORD
import com.coordinadora.data.ConstantsData.USER
import com.coordinadora.data.firestore.util.Login
import com.coordinadora.domain.model.UserEntity
import com.coordinadora.domain.model.Response
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class FireStoreUser @Inject constructor(
    private val db: FirebaseFirestore
):FireStoreUserFunctions {

    override suspend fun getUser(email: String, password: String): Response<UserEntity?> {
        return try {
            val user=db.collection(USER).whereEqualTo(EMAIL,email)
               .get().await()
            val userList=user.toObjects(UserEntity::class.java)
            if (userList.isEmpty()){
                Login.isEmail= true
                Response.Success(null)
            } else {
                Login.idUser = userList.first().idUser!!
                Login.isEmail= false
                Response.Success(userList.firstOrNull { it.password.equals(password) })
            }

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun updateUser(idUser: Long, lastConnection: Date, isBlocked:Boolean):Response<Boolean> {
        return try {
            val user = db.collection(USER)
          user.whereEqualTo(ID_USER,idUser).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val update: MutableMap<String, Any> = HashMap()
                        update["isBlocked"] = isBlocked
                        update["lastConnection"] = lastConnection
                        user.document(document.id).set(update, SetOptions.merge())
                    }
                }
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }


}