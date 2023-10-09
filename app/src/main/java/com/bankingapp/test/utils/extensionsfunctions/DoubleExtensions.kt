package com.bankingapp.test.utils.extensionsfunctions

import java.math.RoundingMode
import java.text.DecimalFormat

// format the amount with two decimals
internal fun Double.formatAmount(): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this)
}