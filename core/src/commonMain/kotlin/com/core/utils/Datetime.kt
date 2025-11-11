package com.core.utils

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun LocalDateTime.Companion.current(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDateTime {
    return Clock.System.now().toLocalDateTime(timeZone)
}

expect fun LocalDateTime.asString(
    pattern: String,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String?

expect fun LocalDate.asString(pattern: String, locale: Locale = Locale.current): String?

expect fun LocalTime.asString(pattern: String, locale: Locale = Locale.current): String?