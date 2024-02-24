package com.coordinadora.data.di

import android.content.Context
import com.coordinadora.data.firestore.home.FireStoreHome
import com.coordinadora.data.firestore.home.FireStoreHomeFunctions
import com.coordinadora.data.firestore.user.FireStoreUser
import com.coordinadora.data.firestore.user.FireStoreUserFunctions
import com.coordinadora.data.repository.HomeRepositoryImpl
import com.coordinadora.data.repository.LoginRepositoryImpl
import com.coordinadora.domain.repository.HomeRepository
import com.coordinadora.domain.repository.LoginRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideInitializeFirestore(
        @ApplicationContext context: Context
    ) = FirebaseApp.initializeApp(context)


    @Provides
    @Singleton
    fun provideFireStoreUser(
        db: FirebaseFirestore
    ): FireStoreUserFunctions = FireStoreUser(db)

    @Provides
    @Singleton
    fun provideFireStoreHome(
        db: FirebaseFirestore
    ): FireStoreHomeFunctions = FireStoreHome(db)

    @Provides
    @Singleton
    fun provideUserRepository(
        fireStoreUser:FireStoreUser
    ): LoginRepository = LoginRepositoryImpl(fireStoreUser)

    @Provides
    @Singleton
    fun provideHomeRepository(
        fireStoreHome: FireStoreHome
    ): HomeRepository = HomeRepositoryImpl(fireStoreHome)


}