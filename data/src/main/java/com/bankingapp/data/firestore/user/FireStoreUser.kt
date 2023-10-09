package com.bankingapp.data.firestore.user

import com.bankingapp.domain.model.UserEntity
import com.bankingapp.domain.model.Response
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreUser @Inject constructor(
    private val db: FirebaseFirestore
):FireStoreUserFunctions {
    override suspend fun saveUser(): Response<Boolean> {
        return try {
            /*
            val id = booksRef.document().id
            val book = User(
                nombre = "jptp1994@gmail.com",
                apellido = "jean",
                edad = "dateBirth",
                email = "jptpp1994@gmail.com",
                password = "ksaoskoaks",
            )
            booksRef.document(id).set(book).await()*/
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getUser(email: String, password: String): Response<UserEntity?> {
        return try {
            val user=db.collection("user").whereEqualTo("email",email)
                .whereEqualTo("password",password).get().await()
            val userList=user.toObjects(UserEntity::class.java)
            Response.Success(userList.firstOrNull())
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun updateUser():Response<Boolean> {
        return try {
            /*
            booksRef
            booksRef.document("bookId").delete().await()*/
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }


}