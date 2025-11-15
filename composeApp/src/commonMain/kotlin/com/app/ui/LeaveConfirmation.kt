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
import com.core.data.Confirmation
import com.design.system.widget.ConfirmationSheet
import org.jetbrains.compose.resources.stringResource
import template.composeapp.generated.resources.Res
import template.composeapp.generated.resources.leave_page_message
import template.composeapp.generated.resources.leave_page_title
import template.composeapp.generated.resources.stay_here
import template.composeapp.generated.resources.yes_leave

@Composable
internal fun getDefaultConfirmation(): Confirmation {
    return Confirmation(
        title = stringResource(Res.string.leave_page_title),
        message = stringResource(Res.string.leave_page_message),
        negativeLabel = stringResource(Res.string.stay_here),
        positiveLabel = stringResource(Res.string.yes_leave)
    )
}

@Composable
internal fun LeaveConfirmation(
    show: Boolean,
    confirmation: Confirmation,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    ConfirmationSheet(
        show = show,
        title = confirmation.title,
        message = confirmation.message,
        actions = {
            Button(
                onClick = onCancel,
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = confirmation.negativeLabel)
            }
            Spacer(modifier = Modifier.height(height = 4.dp))
            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = confirmation.positiveLabel)
            }
        }
    )
}