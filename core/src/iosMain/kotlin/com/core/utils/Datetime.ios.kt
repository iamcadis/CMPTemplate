package com.core.utils

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.toNSDateComponents
import kotlinx.datetime.toNSTimeZone
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone

actual fun LocalDateTime.asString(pattern: String, locale: Locale, timeZone: TimeZone): String? {
    return toNSDateComponents().format(
        pattern = pattern,
        nsLocale = locale.platformLocale,
        nsTimeZone = timeZone.toNSTimeZone()
    )
}

actual fun LocalDate.asString(pattern: String, locale: Locale): String? {
    return toNSDateComponents().format(pattern = pattern, nsLocale = locale.platformLocale)
}

actual fun LocalTime.asString(pattern: String, locale: Locale): String? {
    return this.atDate(year = 2000, month = 1, day = 1)
        .toNSDateComponents()
        .format(pattern = pattern, nsLocale = locale.platformLocale)
    }

private fun NSDateComponents.format(
    pattern: String,
    nsLocale: NSLocale,
    nsTimeZone: NSTimeZone? = null
): String? {
    return runCatching {
        val nsDate = NSCalendar.currentCalendar.dateFromComponents(this) ?: return null
        val dateFormatter = NSDateFormatter().apply {
            locale = nsLocale
            dateFormat = pattern

            nsTimeZone?.let { timeZone = it }
        }

        dateFormatter.stringFromDate(nsDate)
    }.getOrNull()
}