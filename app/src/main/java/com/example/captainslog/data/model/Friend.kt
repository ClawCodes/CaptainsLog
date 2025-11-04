package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.UserDto
import com.example.captainslog.data.repo.FriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class FriendsViewModel() : ViewModel() {
    private val repo: FriendsRepository = TODO()
    val friends: StateFlow<List<UserDto>>
    fun searchUsers(username: String): Flow<List<UserDto>> {
        return TODO("Provide the return value")
    }
    fun addFriend(username: String) {}
}