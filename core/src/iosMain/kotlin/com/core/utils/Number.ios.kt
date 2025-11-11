package com.core.utils

import androidx.compose.ui.text.intl.Locale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle

actual fun Double.toCurrency(locale: Locale): String {
    val formatter = NSNumberFormatter().apply {
        this.locale = locale.platformLocale
        this.numberStyle = NSNumberFormatterCurrencyStyle
        this.maximumFractionDigits = 2.toULong()
    }

    val number = NSNumber(double = rounded())
    val defaultValue = "${formatter.currencySymbol}0${formatter.decimalSeparator}00"

    return formatter.stringFromNumber(number) ?: defaultValue
}