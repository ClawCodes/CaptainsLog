package com.example.captainslog.ui.friends

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captainslog.data.api.UserDto
import com.example.captainslog.data.model.FriendsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


private enum class Caption(val label: String) {
    FRIENDS("My Friends"),
    NOT_FRIENDS("Add Friend"),
}

@Composable
fun FriendsScreen(vm: FriendsViewModel, onOpenProfile: (UserDto) -> Unit, onExit: () -> Unit){
    var searchQuery by remember { mutableStateOf("") }
    var caption by remember { mutableStateOf(Caption.FRIENDS.label) }

    val friends by vm.friends.collectAsState()
    val searchResults by vm.userSearchResults.collectAsState() // Users that are NOT fiends

    if (searchResults.isNotEmpty()){
        caption = Caption.NOT_FRIENDS.label
    }

    val listToShow = if (searchQuery.isNotEmpty()) searchResults else friends

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Exit button
                Button(onClick = onExit) {
                    Text("Home")
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Search bar takes up most of the space
                FriendsSearchBar(
                    onQuery = { query ->
                        searchQuery = query
                        vm.searchUsers(query)
                    }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            Text(
                text = caption,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                items(listToShow) { friend ->
                    Friend(
                        friend = friend,
                        onOpenProfile = { onOpenProfile(friend) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FriendsScreenPreview() {
    // Simple fake ViewModel for preview
    val fakeVm = object : FriendsViewModel() {
        private val _friends = MutableStateFlow(
            listOf(
                UserDto("1", "Alice Johnson"),
                UserDto("2", "Bob Smith"),
                UserDto("3", "Charlie Kim"),
                UserDto("4", "Diana Lopez")
            )
        )
        override val friends: StateFlow<List<UserDto>> = _friends

        private val _friendSearchResults = MutableStateFlow<List<UserDto>>(emptyList())
        override val friendSearchResults: StateFlow<List<UserDto>> = _friendSearchResults

        fun searchFriends(query: String) {
            _friendSearchResults.value = _friends.value.filter {
                it.displayName.contains(query, ignoreCase = true)
            }
        }
    }

    MaterialTheme {
        FriendsScreen(vm = fakeVm, onOpenProfile = {}, onExit = {})
    }
}

