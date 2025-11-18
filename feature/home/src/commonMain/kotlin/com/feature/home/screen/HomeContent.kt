package com.feature.home.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.common.asString
import com.core.common.toCurrency
import com.design.system.extension.section
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeContent(
    state: HomeState,
    dispatch: (HomeAction) -> Unit = {}
) {
    val date = remember(state.currentDate) {
        state.currentDate.date.asString(
            format = "d MMM yyyy",
            languageTag = "id-ID",
        )
    }

    val time = remember(state.currentDate) {
        state.currentDate.time.asString(
            format = "HH:mm zzz",
            atZone = TimeZone.UTC,
            languageTag = "id-ID"
        )
    }

    val dateTime = remember(state.currentDate) {
        state.currentDate.asString(
            format = "d MMMM yyyy, HH:mm zzz",
            atZone = TimeZone.UTC,
            toZone = TimeZone.currentSystemDefault(),
            languageTag = "id-ID"
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
                "Open Test Page",
                "Show Snackbar",
                "Show Loading"
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
                        onClick = {
                            when(item) {
                                "Show Snackbar" -> dispatch(HomeAction.TestShowError)
                                "Show Loading" -> dispatch(HomeAction.ToggleLoading)
                                else -> {}
                            }
                        }
                    )
                }
            )
        }

        section(title = "Date Time Sample") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("CURRENT: ${state.currentDate}")
                Text("CURRENT IN UTC: $date, $time")
                Text("CURRENT IN DEVICE: $dateTime")
                Text("CURRENCY LOCALE ID: ${123.598.toCurrency(
                    languageTag = "id-ID"
                )}")
            }
        }
    }
}

@Composable
fun SettingItem(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 52.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeContent(state = HomeState.Initial)
}