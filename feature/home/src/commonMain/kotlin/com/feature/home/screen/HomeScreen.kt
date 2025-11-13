package com.feature.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
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
import androidx.compose.ui.unit.sp
import com.core.extension.asString
import com.core.extension.current
import com.core.extension.toCurrency
import com.core.ui.BaseScreen
import com.design.system.extension.section
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import template.feature.home.generated.resources.Res
import template.feature.home.generated.resources.compose_multiplatform

@Composable
fun HomeScreen() {
    BaseScreen(viewModel = koinViewModel<HomeViewModel>()) { state, dispatch ->
        HomeContent(
            state = state,
            onShowImage = { dispatch(HomeAction.ToggleContent) },
            onTestError = { dispatch(HomeAction.TestShowError) },
            onTestLoading = { dispatch(HomeAction.ToggleLoading) },
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeState,
    onShowImage: () -> Unit = {},
    onTestError: () -> Unit = {},
    onTestLoading: () -> Unit = {},
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

    val sections = remember {
        listOf(
            "Account" to listOf(
                "Profile & Accounts",
                "Security",
                "Privacy & Security",
                "Billing & Subscription"
            ),
            "Personalization" to listOf(
                "Dark Mode",
                "Appearance",
                "Language",
                "Themes Organize"
            )
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        sections.forEach { (title, items) ->
            section(
                title = title,
                items = items,
                itemContent = { item ->
                    SettingItem(
                        title = item,
                        isDarkModeToggle = item == "Dark Mode"
                    )
                }
            )
        }

        item(key = "test_aja") {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                Text("CURRENT => $currentDate")
                Text("CURRENT DATE => $date")
                Text("CURRENT TIME => $time")
                Text("CURRENT IN DEVICE ZONE => $dateTime")
                Text("EXAMPLE CURRENCY ID => ${123.598.toCurrency(locale = Locale("id-ID"))}")

                Row {
                    Button(onClick = onShowImage) {
                        Text("Toggle")
                    }
                    Button(onClick = onTestError) {
                        Text("Snackbar")
                    }
                    Button(onClick = onTestLoading) {
                        Text("Loading")
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
    }
}

@Composable
fun SettingItem(title: String, isDarkModeToggle: Boolean = false) {
    var darkModeEnabled by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        if (isDarkModeToggle) {
            Switch(
                checked = darkModeEnabled,
                onCheckedChange = { darkModeEnabled = it },
            )
        } else {
            Icon(
                imageVector = Icons.Default.KeyboardDoubleArrowRight,
                contentDescription = null,
            )
        }
    }
}