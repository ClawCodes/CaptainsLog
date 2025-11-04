package com.example.captainslog.data.model

class FriendsViewModel(private val repo: FriendsRepository) : ViewModel() {
    val friends: StateFlow<List<UserDto>>
    fun searchUsers(username: String): Flow<List<UserDto>>
    fun addFriend(username: String)
}