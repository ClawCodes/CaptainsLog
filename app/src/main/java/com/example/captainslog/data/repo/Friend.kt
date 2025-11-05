package com.example.captainslog.data.repo

import com.example.captainslog.data.api.FakeDataFactory
import com.example.captainslog.data.api.UserDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FriendsRepository {

    private val _friends = MutableStateFlow<List<UserDto>>(emptyList())
    val friends: StateFlow<List<UserDto>> = _friends

    private val _otherUsers = MutableStateFlow<List<UserDto>>(emptyList())
    val otherUsers: StateFlow<List<UserDto>> = _otherUsers

    init {
        val initialFriends = List(10) { FakeDataFactory.createUser() }
        val initialOthers = List(30) { FakeDataFactory.createUser() }
        _friends.value = initialFriends
        _otherUsers.value = initialOthers
    }

    private fun search(query: String, searchSet: MutableStateFlow<List<UserDto>>): List<UserDto>{
        val results = searchSet.value.filter {
            it.displayName.contains(query, ignoreCase = true)
        }
        return results
    }
    fun searchUsers(query: String): List<UserDto> {
        return search(query, _otherUsers)
    }

    fun searchFriends(query: String): List<UserDto> {
        return search(query, _friends)
    }

    fun addFriend(displayName: String) {
        val user = _otherUsers.value.find { it.displayName == displayName } ?: return

        _otherUsers.update { users -> users.filterNot { it.displayName == displayName } }

        _friends.update { current -> current + user }
    }

    fun addFriend(user: UserDto) {
        _otherUsers.update { users -> users.filterNot { it == user } }
        _friends.update { current -> current + user }
    }
}
