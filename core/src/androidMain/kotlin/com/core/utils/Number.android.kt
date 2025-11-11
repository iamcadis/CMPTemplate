package com.core.utils

import androidx.compose.ui.text.intl.Locale
import java.text.NumberFormat

actual fun Double.toCurrency(locale: Locale): String {
    return NumberFormat
        .getCurrencyInstance(locale.platformLocale).apply {
            maximumFractionDigits = 2
        }
        .format(this)
}