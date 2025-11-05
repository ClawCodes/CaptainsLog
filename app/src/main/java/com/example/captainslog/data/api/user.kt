package com.example.captainslog.data.api

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(val id: String, var displayName: String)