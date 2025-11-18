package com.core.ui.dialog

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.core.ui.data.MsgConfirmation

@Composable
fun ConfirmationSheet(
    msgConfirmation: MsgConfirmation,
    canDismiss: Boolean = false,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    ConfirmationSheet(
        title = msgConfirmation.title,
        message = msgConfirmation.message,
        canDismiss = canDismiss,
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
                Text(text = msgConfirmation.negativeLabel)
            }
            Spacer(modifier = Modifier.height(height = 4.dp))
            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = msgConfirmation.positiveLabel)
            }
        }
    )
}

@Composable
fun ConfirmationSheet(
    title: String,
    message: String,
    canDismiss: Boolean = false,
    actions: @Composable ColumnScope.() -> Unit
) {
    BottomSheet(
        onDismiss = { },
        modifier = Modifier.padding(all = 16.dp).navigationBarsPadding(),
        dismissOnOutside = canDismiss,
        shape = RoundedCornerShape(size = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(all = 16.dp)
        )
        actions()
        Spacer(modifier = Modifier.padding(12.dp))
    }
}