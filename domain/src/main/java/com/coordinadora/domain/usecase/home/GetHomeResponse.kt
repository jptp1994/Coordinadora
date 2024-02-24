package com.coordinadora.domain.usecase.home

import com.coordinadora.domain.repository.HomeRepository

class GetHomeResponse (
    private val repo: HomeRepository
) {
    suspend operator fun invoke(idUser:Long) = repo.getData(idUser)
}