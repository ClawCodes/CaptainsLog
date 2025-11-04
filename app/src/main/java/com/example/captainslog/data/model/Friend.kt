package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.UserDto
import com.example.captainslog.data.repo.FriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class FriendsViewModel() : ViewModel() {
    // TODO: replace default initializers?
    private val repo: FriendsRepository = FriendsRepository()

    open val friends: StateFlow<List<UserDto>> = MutableStateFlow(emptyList())
    open val searchResults: StateFlow<List<UserDto>> = MutableStateFlow(emptyList())

    fun searchUsers(keyword: String): Flow<List<UserDto>> {
        return TODO("Provide the return value")
    }
    fun findMyFriends(username: String): Flow<List<UserDto>> {
        return TODO("Provide the return value")
    }
    fun addFriend(username: String) {}
}