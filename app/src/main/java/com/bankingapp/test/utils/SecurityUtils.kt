package com.bankingapp.test.utils

import android.util.Log
import com.bankingapp.test.utils.constants.Constants
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object SecurityUtils {

    //Encrypt a String variable from a KEY
    fun String.cypherEncrypt(encryptionKey: String): String? {
        try {
            val secretKeySpec = SecretKeySpec(encryptionKey.toByteArray(), Constants.algorithmEncription)
            val iv = encryptionKey.toByteArray()
            val ivParameterSpec = IvParameterSpec(iv)

            val cipher = Cipher.getInstance(Constants.methodEncription)
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

            val encryptedValue = cipher.doFinal (this.toByteArray())
            return Base64.getEncoder().encodeToString(encryptedValue)
        } catch (e: Exception) {
            e.message?.let{ Log.e("encryptor", it) }
        }
        return null
    }

    //Decrypt a string variable from a Key
    fun String.cypherDecrypt(encryptionKey: String): String? {
        try {
            val secretKeySpec = SecretKeySpec(encryptionKey.toByteArray(), Constants.algorithmEncription)
            val iv = encryptionKey.toByteArray()
            val ivParameterSpec = IvParameterSpec(iv)

            val cipher = Cipher.getInstance(Constants.methodEncription)
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

            val decodedValue = Base64.getDecoder().decode(this)
            val decryptedValue = cipher.doFinal(decodedValue)
            return String(decryptedValue)
        } catch (e: Exception) {
            e.message?.let{ Log.e("decrypt", it) }
        }
        return null
    }



}