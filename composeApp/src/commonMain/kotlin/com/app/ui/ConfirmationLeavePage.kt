package com.app.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.navigation.screen.Screen
import com.design.system.widget.ConfirmationSheet
import org.jetbrains.compose.resources.stringResource
import template.composeapp.generated.resources.Res
import template.composeapp.generated.resources.leave_page_message
import template.composeapp.generated.resources.leave_page_title
import template.composeapp.generated.resources.stay_here
import template.composeapp.generated.resources.yes_leave

@Composable
internal fun ConfirmationLeavePage(
    show: Boolean,
    screen: Screen,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    ConfirmationSheet(
        show = show,
        title = screen.getConfirmationTitle() ?: stringResource(
            resource = Res.string.leave_page_title
        ),
        message = screen.getConfirmationMessage() ?: stringResource(
            resource = Res.string.leave_page_message
        ),
        actions = {
            Button(
                onClick = onCancel,
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = screen.cancelLabel() ?: stringResource(resource = Res.string.stay_here)
                )
            }
            Spacer(modifier = Modifier.height(height = 4.dp))
            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = screen.confirmLabel() ?: stringResource(resource = Res.string.yes_leave)
                )
            }
        }
    )
}