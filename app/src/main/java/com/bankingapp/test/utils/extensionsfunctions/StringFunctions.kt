package com.bankingapp.test.utils.extensionsfunctions

import android.text.TextUtils
import java.util.regex.Pattern

//Validate email pattern
internal fun String.isValidEmail():Boolean = !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()


//Validate the password pattern must include a capital letter a regular letter and at least three numbers
internal fun String.validatePassword(): Boolean {
    // check for pattern
    val uppercase: Pattern = Pattern.compile("[A-Z]")
    val lowercase: Pattern = Pattern.compile("[a-z]")
    val digit: Pattern = Pattern.compile("[0-9]")
    return (lowercase.matcher(this).find() && uppercase.matcher(this).find() && digit.matcher(this).find()
            && this.length >=5)
}

//Validate if the field is empty
internal fun String.isEmptyField():Boolean = this.isEmpty()

//This let to show enmascarated the bankNumber
internal fun String.hideCharacters():String = this.replaceRange(IntRange(0,this.length-4),"*")


