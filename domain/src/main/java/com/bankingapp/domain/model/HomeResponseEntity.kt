package com.bankingapp.domain.model

data class HomeResponseEntity(
    val bankingList:MutableList<UserBankingMovementEntity> ? = null
)
