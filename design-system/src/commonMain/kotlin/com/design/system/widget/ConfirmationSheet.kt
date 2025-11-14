package com.design.system.widget

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmationSheet(
    show: Boolean,
    title: String,
    message: String,
    canDismiss: Boolean = false,
    actions: @Composable ColumnScope.() -> Unit
) {
    if (!show) return

    BottomSheet(
        modifier = Modifier.padding(all = 16.dp).navigationBarsPadding(),
        onDismiss = {},
        shape = RoundedCornerShape(size = 16.dp),
        dismissOnOutside = canDismiss
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