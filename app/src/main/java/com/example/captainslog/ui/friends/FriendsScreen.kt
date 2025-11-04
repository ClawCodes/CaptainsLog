package com.example.captainslog.ui.friends

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

// TODO: Add go back call back to return to recording screen
@Composable
fun FriendsScreen(vm: FriendsViewModel, onOpenProfile: (String) -> Unit, onExit: () -> Unit){
    var searchQuery by remember { mutableStateOf("") }

    // Example state flows you might have from your ViewModel
    val friends by vm.friends.collectAsState()
    // TODO: Replace with results from VM
    val searchResults by vm.searchResults.collectAsState()

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
                        // TODO: add search functionality
                        // vm.searchFriends(query)
                    }
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(listToShow) { friend ->
                Friend(
                    friend = friend,
                    onOpenProfile = onOpenProfile
                )
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

        private val _searchResults = MutableStateFlow<List<UserDto>>(emptyList())
        override val searchResults: StateFlow<List<UserDto>> = _searchResults

        fun searchFriends(query: String) {
            _searchResults.value = _friends.value.filter {
                it.displayName.contains(query, ignoreCase = true)
            }
        }
    }

    MaterialTheme {
        FriendsScreen(vm = fakeVm, onOpenProfile = {}, onExit = {})
    }
}

