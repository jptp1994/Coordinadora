package com.bankingapp.test.ui.home.mapper

import com.bankingapp.domain.model.HomeResponseEntity
import com.bankingapp.test.ui.home.model.HomeResponse
import com.bankingapp.test.utils.base.Mapper
import javax.inject.Inject

//Mapper Home Response Entity - Home Response
class HomeMapper @Inject constructor(
    private val userBankingMapper: UserBankingMapper
) : Mapper<HomeResponseEntity?, HomeResponse> {

    override fun mapFromEntity(type: HomeResponseEntity?): HomeResponse {
        return HomeResponse(
            bankingList = type?.bankingList?.map { userBankingMapper.mapFromEntity(it) }
                ?.toMutableList()?:arrayListOf()
        )
    }

    override fun mapToEntity(type: HomeResponse): HomeResponseEntity {
        return HomeResponseEntity(
            bankingList = type.bankingList.map { userBankingMapper.mapToEntity(it) }.toMutableList()
        )
    }
}
