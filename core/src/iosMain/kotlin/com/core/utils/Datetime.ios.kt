package com.core.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.toNSDateComponents
import org.koin.core.component.getScopeId
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
actual fun LocalDateTime.asString(
    pattern: String,
    localeId: String,
    timeZone: TimeZone,
): String? {
    val components = toInstant(TimeZone.UTC).toLocalDateTime(timeZone).toNSDateComponents()
    return components.format(pattern, localeId)
}

actual fun LocalDate.asString(pattern: String, localeId: String): String? {
    return toNSDateComponents().format(pattern, localeId)
}

actual fun LocalTime.asString(pattern: String, localeId: String): String? {
    val components = NSDateComponents()
    components.hour = this.hour.toLong()
    components.minute = this.minute.toLong()
    components.second = this.second.toLong()
    components.nanosecond = this.nanosecond.toLong()
    return components.format(pattern, localeId)
}

private fun NSDateComponents.format(pattern: String, localeId: String): String? {
    return runCatching {
        val nsDate = NSCalendar.currentCalendar.dateFromComponents(this) ?: return null
        val dateFormatter = NSDateFormatter().apply {
            dateFormat = pattern
            locale = NSLocale(localeIdentifier = localeId)
        }

        dateFormatter.stringFromDate(nsDate)
    }.getScopeId()
}