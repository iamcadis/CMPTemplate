package com.feature.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.core.ui.BaseScreen
import com.core.utils.asString
import com.core.utils.current
import com.core.utils.toCurrency
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import template.feature.home.generated.resources.Res
import template.feature.home.generated.resources.compose_multiplatform

@Composable
fun HomeScreen() {
    BaseScreen(
        viewModel = koinViewModel<HomeViewModel>(),
        pageLoadingText = "Loading ..."
    ) { state, dispatch ->
        HomeContent(
            state = state,
            onShowImage = { dispatch(HomeAction.ToggleContent) },
            onTestError = { dispatch(HomeAction.TestShowError) }
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeState,
    onShowImage: () -> Unit = {},
    onTestError: () -> Unit = {},
) {
    val currentDate = remember { LocalDateTime.current(timeZone = TimeZone.UTC) }

    val date = remember(currentDate) {
        currentDate.date.asString(
            format = "d MMM yyyy",
            locale = Locale("id-ID"),
        )
    }

    val time = remember(currentDate) {
        currentDate.time.asString(
            format = "HH:mm zzz",
            locale = Locale("id-ID"),
            atZone = TimeZone.UTC
        )
    }

    val dateTime = remember(currentDate) {
        currentDate.asString(
            format = "d MMMM yyyy, HH:mm zzz",
            locale = Locale("id-ID"),
            atZone = TimeZone.UTC,
            toZone = TimeZone.currentSystemDefault(),
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text("CURRENT => $currentDate")
        Text("CURRENT DATE => $date")
        Text("CURRENT TIME => $time")
        Text("CURRENT IN DEVICE ZONE => $dateTime")
        Text("EXAMPLE CURRENCY ID => ${123.598.toCurrency(locale = Locale("id-ID"))}")

        Row {
            Button(onClick = onShowImage) {
                Text("Show Image")
            }
            Button(onClick = onTestError) {
                Text("Test Snackbar")
            }
        }

        AnimatedVisibility(state.showContent) {
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