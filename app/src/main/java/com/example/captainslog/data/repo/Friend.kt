package com.example.captainslog.data.repo

import com.example.captainslog.data.api.UserDto
import kotlinx.coroutines.flow.Flow

class FriendsRepository {
    suspend fun getFriends(): List<UserDto> {
        return TODO("Provide the return value")
    }
    fun searchUsers(query: String): Flow<List<UserDto>> {
        return TODO("Provide the return value")
    }
    suspend fun addFriend(userId: String) {}
}