package com.coordinadora.domain.di

import com.coordinadora.domain.repository.HomeRepository
import com.coordinadora.domain.repository.LoginRepository
import com.coordinadora.domain.usecase.home.GetHomeResponse
import com.coordinadora.domain.usecase.home.HomeUseCases
import com.coordinadora.domain.usecase.login.GetUser
import com.coordinadora.domain.usecase.login.LoginUseCases
import com.coordinadora.domain.usecase.login.UpdateUser
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