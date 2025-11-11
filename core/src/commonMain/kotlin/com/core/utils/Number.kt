package com.core.utils

import androidx.compose.ui.text.intl.Locale

expect fun Double.toCurrency(locale: Locale = Locale.current): String