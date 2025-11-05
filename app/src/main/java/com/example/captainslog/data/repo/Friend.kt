package com.example.captainslog.data.repo

import com.example.captainslog.data.api.UserDto
import kotlinx.coroutines.flow.Flow

class FriendsRepository {
    private var localFriends = listOf<UserDto>(
        UserDto("1", "Spock"),
        UserDto("2", "James T. Kirk"),
        UserDto("3", "Picard")
    )
    suspend fun getFriends(): List<UserDto> {
        return TODO("Provide the return value")
    }
    fun searchUsers(query: String): Flow<List<UserDto>> {
        return TODO("Provide the return value")
    }
    suspend fun addFriend(userId: String) {}
}