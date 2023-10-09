package com.bankingapp.test.ui.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bankingapp.test.utils.extensionsfunctions.isValidEmail
import com.bankingapp.test.utils.extensionsfunctions.setAgeValidate
import com.bankingapp.test.utils.extensionsfunctions.setEmailValidate
import com.bankingapp.test.utils.extensionsfunctions.setErrorPassword
import com.bankingapp.test.utils.extensionsfunctions.setFirstNameValidate
import com.bankingapp.test.utils.extensionsfunctions.setLastNameValidate
import com.bankingapp.test.utils.extensionsfunctions.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel()  {

    //Values set in the double vinculation with databinding
    val firstName= MutableLiveData<String>()
    val lastName= MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()


    //Contains the observer to set the error in the textFields
    private val _firstNameValidText = MutableLiveData<String>()
    val firstNameValidText: MutableLiveData<String> =_firstNameValidText

    private val _lastNameValidText = MutableLiveData<String>()
    val lastNameValidText: MutableLiveData<String> = _lastNameValidText

    private val _ageNameValidText = MutableLiveData<String>()
    val ageNameValidText: MutableLiveData<String> = _ageNameValidText

    private val _isEmailValidText = MutableLiveData<String>()
    val emailValidText: MutableLiveData<String> = _isEmailValidText

    private val _passwordValidText = MutableLiveData<String>()
    val passwordValidText: MutableLiveData<String> = _passwordValidText


    private val emailObserver = Observer<String> { onEmailChanged(it) }
    private val firstNameObserver = Observer<String> { onFirstNameChanged(it) }
    private val lastNameObserver = Observer<String> { onLastNameChanged(it) }
    private val ageObserver = Observer<String> { onAgeChanged(it) }
    private val passwordObserver = Observer<String> { onPasswordChanged(it) }


    init {
        email.observeForever(emailObserver)
        firstName.observeForever(firstNameObserver)
        lastName.observeForever(lastNameObserver)
        age.observeForever(ageObserver)
        password.observeForever(passwordObserver)
    }

    override fun onCleared() {
        firstName.removeObserver(firstNameObserver)
        lastName.removeObserver(lastNameObserver)
        age.removeObserver(ageObserver)
        password.removeObserver(passwordObserver)
        email.removeObserver(emailObserver)
    }

    private fun onFirstNameChanged(it: String) {
        firstNameValidText.value = it.isEmpty().setFirstNameValidate()
    }

    private fun onLastNameChanged(it: String) {
        lastNameValidText.value = it.isEmpty().setLastNameValidate()
    }

    private fun onAgeChanged(it: String) {
        ageNameValidText.value = it.isEmpty().setAgeValidate()
    }
    private fun onEmailChanged(newEmail: String) {
        emailValidText.value= newEmail.isValidEmail().setEmailValidate()
    }

    private fun onPasswordChanged(password: String) {
        passwordValidText.value= password.validatePassword().setErrorPassword()
    }

}