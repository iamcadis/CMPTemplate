package com.core.extension

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

actual fun LocalDateTime.asString(
    format: String,
    locale: Locale,
    atZone: TimeZone,
    toZone: TimeZone,
): String? {
    val nsDateComponents = this.toNSDateComponents()
    nsDateComponents.setTimeZone(atZone.toNSTimeZone())

    return nsDateComponents.format(
        format = format,
        nsLocale = locale.platformLocale,
        nsTimeZone = toZone.toNSTimeZone()
    )
}

actual fun LocalDate.asString(format: String, locale: Locale): String? {
    return this.toNSDateComponents()
        .format(format = format, nsLocale = locale.platformLocale)
}

actual fun LocalTime.asString(format: String, locale: Locale, atZone: TimeZone): String? {
    val nsDateComponents = this.atDate(year = 2000, month = 1, day = 1).toNSDateComponents()
    nsDateComponents.setTimeZone(atZone.toNSTimeZone())

    return nsDateComponents.format(
        format = format,
        nsLocale = locale.platformLocale,
        nsTimeZone = atZone.toNSTimeZone()
    )
}


private fun NSDateComponents.format(
    format: String,
    nsLocale: NSLocale,
    nsTimeZone: NSTimeZone? = null
): String? {
    return runCatching {
        val nsDate = NSCalendar.currentCalendar.dateFromComponents(this) ?: return null
        val dateFormatter = NSDateFormatter().apply {
            locale = nsLocale
            dateFormat = format

            nsTimeZone?.let { timeZone = it }
        }

        dateFormatter.stringFromDate(nsDate)
    }.getOrNull()
}