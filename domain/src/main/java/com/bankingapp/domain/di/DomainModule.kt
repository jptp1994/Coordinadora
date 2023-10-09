package com.bankingapp.domain.di

import com.bankingapp.domain.repository.HomeRepository
import com.bankingapp.domain.repository.LoginRepository
import com.bankingapp.domain.usecase.home.GetHomeResponse
import com.bankingapp.domain.usecase.home.HomeUseCases
import com.bankingapp.domain.usecase.register.AddUser
import com.bankingapp.domain.usecase.login.GetUser
import com.bankingapp.domain.usecase.login.LoginUseCases
import com.bankingapp.domain.usecase.login.UpdateUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideLoginUseCases(
        repo: LoginRepository
    ) = LoginUseCases(
        getUser = GetUser(repo),
        addUser = AddUser(repo),
        updateUser = UpdateUser(repo)
    )

    @Provides
    @Singleton
    fun provideHomeUseCases(
        repo: HomeRepository
    ) = HomeUseCases(
        getHomeResponse = GetHomeResponse(repo),
    )
}