package com.core.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

val TimeZone.Companion.AsiaJakarta: TimeZone
    get() = TimeZone.of("Asia/Jakarta")

@OptIn(ExperimentalTime::class)
fun LocalDateTime.Companion.current(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDateTime {
    return Clock.System.now().toLocalDateTime(timeZone)
}

expect fun LocalDateTime.asString(
    pattern: String,
    localeId: String = "id-ID",
    timeZone: TimeZone = TimeZone.AsiaJakarta,
): String?

expect fun LocalDate.asString(pattern: String, localeId: String = "id-ID"): String?

expect fun LocalTime.asString(pattern: String, localeId: String = "id-ID"): String?