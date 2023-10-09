package com.bankingapp.data.di

import android.content.Context
import com.bankingapp.data.firestore.home.FireStoreHome
import com.bankingapp.data.firestore.home.FireStoreHomeFunctions
import com.bankingapp.data.firestore.user.FireStoreUser
import com.bankingapp.data.firestore.user.FireStoreUserFunctions
import com.bankingapp.data.repository.HomeRepositoryImpl
import com.bankingapp.data.repository.LoginRepositoryImpl
import com.bankingapp.domain.repository.HomeRepository
import com.bankingapp.domain.repository.LoginRepository
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