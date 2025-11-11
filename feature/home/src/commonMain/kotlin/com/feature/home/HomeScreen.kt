package com.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.core.utils.asString
import com.core.utils.current
import com.core.utils.toCurrency
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.painterResource
import template.feature.home.generated.resources.Res
import template.feature.home.generated.resources.compose_multiplatform

@Composable
fun HomeScreen() {
    var showContent by remember { mutableStateOf(false) }
    val currentDate = remember { LocalDateTime.current() }

    val date = remember(currentDate) {
        currentDate.date.asString(
            pattern = "d MMM yyyy",
            locale = Locale("id-ID"),
        )
    }

    val time = remember(currentDate) {
        currentDate.time.asString(
            pattern = "HH:mm zzz",
            locale = Locale("id-ID"),
        )
    }

    val dateTime = remember(currentDate) {
        currentDate.asString(
            pattern = "d MMMM yyyy, HH:mm zzz",
            locale = Locale("id-ID"),
            timeZone = TimeZone.of("Asia/Jayapura")
        )
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("CURRENT => $currentDate")
        Text("CURRENT DATE => $date")
        Text("CURRENT TIME => $time")
        Text("CURRENT IN JAYAPURA => $dateTime")
        Text("EXAMPLE CURRENCY ID => ${123.598.toCurrency(locale = Locale("id-ID"))}")

        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }

        AnimatedVisibility(showContent) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Hello")
            }
        }
    }
}