package com.core.extension

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaZoneId
import java.time.ZoneId
import java.time.format.DateTimeFormatter

actual fun LocalDateTime.asString(
    format: String,
    locale: Locale,
    atZone: TimeZone,
    toZone: TimeZone,
): String? {
    return this.toJavaLocalDateTime()
        .atZone(atZone.toJavaZoneId())
        .withZoneSameInstant(toZone.toJavaZoneId())
        .format(DateTimeFormatter.ofPattern(format, locale.platformLocale))
}

actual fun LocalDate.asString(format: String, locale: Locale): String? {
    return runCatching {
        toJavaLocalDate().format(getDateFormatter(format, locale))
    }.getOrNull()
}

actual fun LocalTime.asString(format: String, locale: Locale, atZone: TimeZone): String? {
    return this.atDate(year = 2000, month = 1, day = 1)
        .asString(format = format, locale = locale, atZone = atZone)
}

private fun getDateFormatter(
    format: String,
    locale: Locale,
    zoneId: ZoneId? = null
): DateTimeFormatter {
    val formatter = DateTimeFormatter.ofPattern(format, locale.platformLocale)
    zoneId?.let {
        formatter.withZone(zoneId)
    }
    return DateTimeFormatter.ofPattern(format, locale.platformLocale)
}