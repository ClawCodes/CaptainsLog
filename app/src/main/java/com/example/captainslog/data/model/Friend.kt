package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.UserDto
import com.example.captainslog.data.repo.FriendsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class FriendsViewModel() : ViewModel() {
    private val repo: FriendsRepository = FriendsRepository()

    open val friends: StateFlow<List<UserDto>> = repo.friends

    private var _userSearchResults: MutableStateFlow<List<UserDto>> = MutableStateFlow(emptyList())
    val userSearchResults: StateFlow<List<UserDto>> = _userSearchResults

    private var _friendSearchResults: MutableStateFlow<List<UserDto>> = MutableStateFlow(emptyList())
    open val friendSearchResults: StateFlow<List<UserDto>> = _friendSearchResults


    fun searchUsers(query: String){
        _userSearchResults.value = repo.searchUsers(query)
    }

    fun findMyFriends(query: String) {
        _friendSearchResults.value = repo.searchFriends(query)
    }

    fun addFriend(username: String) {
        repo.addFriend(username)
    }

    fun addFriend(user: UserDto) {
        repo.addFriend(user)
    }
}