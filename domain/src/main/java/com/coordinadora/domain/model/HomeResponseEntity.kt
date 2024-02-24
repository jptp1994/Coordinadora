package com.coordinadora.domain.model

data class HomeResponseEntity(
    val bankingList:MutableList<UserBankingMovementEntity> ? = null
)
