package com.bankingapp.test.utils.extensionsfunctions

import com.bankingapp.test.utils.constants.Constants

// This are the validation for the form in register

internal fun Boolean.setEmailValidate():String = if (this) Constants.empty else Constants.errorEmailField

internal fun Boolean.setFirstNameValidate():String = if (this) Constants.empty else Constants.errorFirstName

internal fun Boolean.setLastNameValidate():String = if (this) Constants.empty else Constants.errorLastName

internal fun Boolean.setAgeValidate():String = if (this) Constants.empty else Constants.errorAge

internal fun Boolean.setDateBirthValidate():String = if (this) Constants.empty else Constants.errorDateBirth

internal fun Boolean.setErrorPassword():String = if (this) Constants.empty else Constants.errorPasswordField
