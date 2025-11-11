package com.core.utils

import androidx.compose.ui.text.intl.Locale
import kotlin.math.round

fun Double.rounded(): Double {
    val scaled = (this * 1000).toInt()
    val lastDigit = scaled % 10

    val epsilon = 1e-10
    val rounded = round(this * 100 + epsilon) / 100

    return if (lastDigit == 0) this else rounded
}

expect fun Double.toCurrency(locale: Locale = Locale.current): String