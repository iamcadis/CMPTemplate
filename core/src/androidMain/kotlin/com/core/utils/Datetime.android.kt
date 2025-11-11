package com.core.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toJavaZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
actual fun LocalDateTime.asString(
    pattern: String,
    localeId: String,
    timeZone: TimeZone,
): String? {
    return runCatching {
        val locale = Locale.forLanguageTag(localeId)
        toJavaLocalDateTime()
            .atZone(ZoneOffset.UTC)
            .withZoneSameInstant(timeZone.toJavaZoneId())
            .format(DateTimeFormatter.ofPattern(pattern, locale))
    }.getOrNull()
}

actual fun LocalDate.asString(pattern: String, localeId: String): String? {
    return runCatching {
        val locale = Locale.forLanguageTag(localeId)
        toJavaLocalDate().format(DateTimeFormatter.ofPattern(pattern, locale))
    }.getOrNull()
}

actual fun LocalTime.asString(pattern: String, localeId: String): String? {
    return runCatching {
        val locale = Locale.forLanguageTag(localeId)
        toJavaLocalTime().format(DateTimeFormatter.ofPattern(pattern, locale))
    }.getOrNull()
}