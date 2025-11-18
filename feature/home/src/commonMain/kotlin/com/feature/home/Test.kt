package com.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.core.ui.BaseScreen
import com.feature.home.screen.HomeViewModel
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import template.feature.home.generated.resources.Res
import template.feature.home.generated.resources.title

object Test {
    @Serializable
    data class Route(val data: String)

    @Composable
    fun Screen(data: String) {
        BaseScreen(
            viewModel = koinViewModel<HomeViewModel>(),
            pageTitle = stringResource(Res.string.title),
            floatingActionButton = {

            }
        ) { state, _ ->
            Column {
                Text("Current: ${state.currentDate}")
                Text("Parameter: $data")
            }
        }
    }
}