package com.bankingapp.test.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bankingapp.domain.model.HomeResponseEntity
import com.bankingapp.domain.model.Response
import com.bankingapp.domain.usecase.home.HomeUseCases
import com.bankingapp.test.ui.home.mapper.HomeMapper
import com.bankingapp.test.ui.home.model.HomeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Inject the use case and the mapper
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
    private val homeMapper: HomeMapper):ViewModel() {

    //state for loading
    var isLoading = MutableLiveData(false)

    //get data from home
    var homeResponse = MutableLiveData<Response<HomeResponseEntity?>>(Response.Initial)
        private set


    // function to get data by the id user
    fun getData(idUser: Long) = viewModelScope.launch {
        homeResponse.value = Response.Loading
        homeUseCases.getHomeResponse.invoke(idUser).collect { response ->
            homeResponse.value = response
        }
    }

    // function to use the home mapper
    fun convertData(it1: HomeResponseEntity): HomeResponse {
        return  homeMapper.mapFromEntity(it1)
    }

}