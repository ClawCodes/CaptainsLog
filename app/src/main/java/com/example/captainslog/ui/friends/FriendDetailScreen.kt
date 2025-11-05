package com.example.captainslog.ui.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captainslog.data.api.UserDto
import com.example.captainslog.data.model.FriendsViewModel
import com.example.captainslog.data.repo.FriendsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.captainslog.R

@Composable
fun FriendDetailScreen(
    user: UserDto,
    accessorVM: FriendsViewModel,
    onExit: () -> Unit
) {
    // Create a new repo instance (simulating user’s own friend count)
    val repo = remember { FriendsRepository() }
    val friends by repo.friends.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onExit) {
                    Text("Go Back")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // avatar image
            Image(
                painter = painterResource(id = R.drawable.outline_person_24),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 8.dp)
            )
            // User info
            Text(
                text = user.displayName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            // Friend count
            Text(
                text = "Friends: ${friends.size}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { accessorVM.addFriend(user.id) },
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Add Friend")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FriendDetailScreenPreview() {
    val fakeVm = object : FriendsViewModel() {
        override val friends: StateFlow<List<UserDto>> =
            MutableStateFlow(emptyList())
    }

    MaterialTheme {
        FriendDetailScreen(
            user = UserDto("123", "Alice Johnson"),
            accessorVM = fakeVm,
            onExit = {}
        )
    }
}
