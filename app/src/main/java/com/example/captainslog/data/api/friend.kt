package com.example.captainslog.data.api

import kotlinx.serialization.Serializable

@Serializable
data class FriendDto(val userId: String, val friendId: String)