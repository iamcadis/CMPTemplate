package com.core.utils

import androidx.compose.ui.text.intl.Locale
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

actual fun LocalDateTime.asString(pattern: String, locale: Locale, timeZone: TimeZone): String? {
    return runCatching {
        toJavaLocalDateTime()
            .atZone(ZoneOffset.UTC)
            .withZoneSameInstant(timeZone.toJavaZoneId())
            .format(DateTimeFormatter.ofPattern(pattern, locale.platformLocale))
    }.getOrNull()
}

actual fun LocalDate.asString(pattern: String, locale: Locale): String? {
    return runCatching {
        toJavaLocalDate().format(getDateFormatter(pattern, locale))
    }.getOrNull()
}

actual fun LocalTime.asString(pattern: String, locale: Locale): String? {
    return runCatching {
        toJavaLocalTime().format(getDateFormatter(pattern, locale))
    }.getOrNull()
}

private fun getDateFormatter(pattern: String, locale: Locale): DateTimeFormatter {
    return DateTimeFormatter.ofPattern(pattern, locale.platformLocale)
}