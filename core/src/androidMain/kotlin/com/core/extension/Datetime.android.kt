package com.core.extension

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.atTime
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.time.ExperimentalTime
import java.util.TimeZone as JavaTimeZone

@OptIn(ExperimentalTime::class)
actual fun LocalDateTime.asString(
    format: String,
    locale: Locale,
    atZone: TimeZone,
    toZone: TimeZone,
): String? {
    return this.toInstant(atZone)
        .toLocalDateTime(toZone)
        .format(format = format, locale = locale, toZone = toZone)
}

actual fun LocalDate.asString(format: String, locale: Locale): String? {
    return this.atTime(hour = 1, minute = 1).format(format = format, locale = locale)
}

actual fun LocalTime.asString(format: String, locale: Locale, atZone: TimeZone): String? {
    return this.atDate(year = 2000, month = 1, day = 1)
        .asString(format = format, locale = locale, atZone = atZone)
}

private fun LocalDateTime.format(
    format: String,
    locale: Locale,
    toZone: TimeZone? = null
): String? {
    val calendar = Calendar.getInstance().apply {
        toZone?.let {
            timeZone = JavaTimeZone.getTimeZone(it.id)
        }

        with(this@format) {
            set(Calendar.YEAR, date.year)
            set(Calendar.MONTH, date.month.number - 1)
            set(Calendar.DAY_OF_MONTH, date.day)
            set(Calendar.HOUR_OF_DAY, time.hour)
            set(Calendar.MINUTE, time.minute)
            set(Calendar.SECOND, time.second)
        }
    }

    return runCatching {
        val formatter = SimpleDateFormat(format, locale.platformLocale)
        toZone?.let {
            formatter.timeZone = JavaTimeZone.getTimeZone(it.id)
        }
        formatter.format(calendar.time)
    }.getOrNull()
}