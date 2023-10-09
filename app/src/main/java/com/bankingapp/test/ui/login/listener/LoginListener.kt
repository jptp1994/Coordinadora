package com.bankingapp.test.ui.login.listener

import com.bankingapp.test.utils.base.BaseListener

//Listener for redirect to register
interface LoginListener: BaseListener {
    fun clickRegister()
}