package com.bankingapp.data.firestore.user

import com.bankingapp.data.ConstantsData.EMAIL
import com.bankingapp.data.ConstantsData.ID_USER
import com.bankingapp.data.ConstantsData.PASSWORD
import com.bankingapp.data.ConstantsData.USER
import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.model.Response
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class FireStoreUser @Inject constructor(
    private val db: FirebaseFirestore
):FireStoreUserFunctions {
    override suspend fun saveUser(
        idUser: Long,
        imageUser: String,
        age: String,
        name: String,
        lastConnection: Date,
        lastName: String,
        email: String,
        password: String,
        dateBirth: Date?
    ): Response<Boolean> {
        return try {
            val book = UserEntity(
                idUser= idUser,
                imageUser = imageUser,
                age = age,
                name = name,
                lastName = lastName,
                dateBirth = dateBirth,
                lastConnection = lastConnection,
                email = email,
                password = password,
            )
            db.collection(USER).add(book).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getUser(email: String, password: String): Response<UserEntity?> {
        return try {
            val user=db.collection(USER).whereEqualTo(EMAIL,email)
                .whereEqualTo(PASSWORD,password).get().await()
            val userList=user.toObjects(UserEntity::class.java)
            Response.Success(userList.firstOrNull())
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getLastUser(): Response<Long> {
        return try {
            val user=db.collection(USER).limit(1)
                .orderBy(ID_USER, Query.Direction.DESCENDING)
                .get().await()
            val userList=user.toObjects(UserEntity::class.java)
            Response.Success(userList.firstOrNull()?.idUser?:0)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun updateUser(idUser: Long, lastConnection: Date):Response<Boolean> {
        return try {
            val user = db.collection(USER)
          user.whereEqualTo(ID_USER,idUser).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val update: MutableMap<String, Any> = HashMap()
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