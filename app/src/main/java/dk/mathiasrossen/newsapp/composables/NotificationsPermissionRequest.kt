package dk.mathiasrossen.newsapp.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotificationPermissionRequestDialog(
    onDismissed: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Notifications, contentDescription = null)
        },
        title = {
            Text(text = "Notifications are off")
        },
        text = {
            Text(text = "If you want the latest and highest quality articles about horses, " +
                    "then you should definitely allow notifications.\n" +
                    "Do you understand this message?")

        },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(text = "Yay")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissed() }) {
                Text(text = "Neigh")
            }
        },
        onDismissRequest = {
            onDismissed()
        }
    )
}

@Preview
@Composable
fun PreviewNotificationPermissionRequestDialog() {
    NotificationPermissionRequestDialog(onDismissed = {}) {}
}