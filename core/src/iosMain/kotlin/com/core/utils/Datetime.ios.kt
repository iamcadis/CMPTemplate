package com.core.utils

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toNSDateComponents
import kotlinx.datetime.toNSTimeZone
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale

actual fun LocalDateTime.asString(pattern: String, locale: Locale, timeZone: TimeZone): String? {
    val components = toNSDateComponents()
    components.setTimeZone(timeZone.toNSTimeZone())
    return components.format(pattern = pattern, nsLocale = locale.platformLocale)
}

actual fun LocalDate.asString(pattern: String, locale: Locale): String? {
    return toNSDateComponents().format(pattern = pattern, nsLocale = locale.platformLocale)
}

actual fun LocalTime.asString(pattern: String, locale: Locale): String? {
    val components = NSDateComponents()
    components.hour = this.hour.toLong()
    components.minute = this.minute.toLong()
    components.second = this.second.toLong()
    components.nanosecond = this.nanosecond.toLong()
    return components.format(pattern = pattern, nsLocale = locale.platformLocale)
}

private fun NSDateComponents.format(pattern: String, nsLocale: NSLocale): String? {
    return runCatching {
        val nsDate = NSCalendar.currentCalendar.dateFromComponents(this) ?: return null
        val dateFormatter = NSDateFormatter().apply {
            locale = nsLocale
            dateFormat = pattern
        }

        dateFormatter.stringFromDate(nsDate)
    }.getOrNull()
}