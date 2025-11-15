package com.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.core.extension.current
import com.core.ui.BaseScreen
import com.feature.home.screen.HomeViewModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class TestRoute(val data: String)

@Composable
fun TestScreen(data: String) {
    BaseScreen(
        viewModel = koinViewModel<HomeViewModel>(),
        pageTitle = "Testing",
        confirmOnLeave = true,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { println("FAB Clicked") }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        },
        topBarActions = {
            IconButton(
                onClick = {
                    println("DATE TIME CLICKED: ${LocalDateTime.current().time}")
                }
            ) {
                Icon(imageVector = Icons.Default.Print, contentDescription = null)
            }
        }
    ) { _, _ ->
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Test: $data")
        }
    }
}