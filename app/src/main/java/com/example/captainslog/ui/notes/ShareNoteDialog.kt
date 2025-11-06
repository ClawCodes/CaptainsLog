package com.example.captainslog.ui.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.captainslog.data.api.UserDto

@Composable
fun ShareNoteDialog(
    friends: List<UserDto>,
    onDismiss: () -> Unit,
    onConfirmShare: (List<UserDto>) -> Unit
) {
    // Keep track of which friends are selected
    val selectedFriends = remember { mutableStateOf(setOf<String>()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                val selected = friends.filter { it.id in selectedFriends.value }
                onConfirmShare(selected)
            }) {
                Text("Share")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        },
        title = { Text("Share note with:") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                friends.forEach { friend ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (friend.id in selectedFriends.value)
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                else
                                    MaterialTheme.colorScheme.surface
                            )
                            .padding(8.dp)
                            .clickable {
                                selectedFriends.value =
                                    if (friend.id in selectedFriends.value)
                                        selectedFriends.value - friend.id
                                    else
                                        selectedFriends.value + friend.id
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(friend.displayName)
                        if (friend.id in selectedFriends.value) {
                            Text("✓", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    )
}
