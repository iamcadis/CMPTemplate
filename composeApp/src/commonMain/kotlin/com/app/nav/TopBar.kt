package com.app.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.core.extension.current
import com.core.navigation.screen.Screen
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(currentScreen: Screen?) {
    if (currentScreen == null) return

    TopAppBar(
        title = { currentScreen.getTitle()?.let { Text(text = it) } },
        actions = {
            IconButton(
                onClick = {
                    println("DATE TIME CLICKED: ${LocalDateTime.current().time}")
                }
            ) {
                Icon(imageVector = Icons.Default.Print, contentDescription = null)
            }
        }
    )
}