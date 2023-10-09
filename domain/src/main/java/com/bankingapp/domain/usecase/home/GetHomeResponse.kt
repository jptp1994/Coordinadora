package com.bankingapp.domain.usecase.home

import com.bankingapp.domain.repository.HomeRepository

class GetHomeResponse (
    private val repo: HomeRepository
) {
    suspend operator fun invoke(idUser:Long) = repo.getData(idUser)
}