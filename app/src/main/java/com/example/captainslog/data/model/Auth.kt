package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.FakeDataFactory
import com.example.captainslog.data.api.UserDto

class AuthViewModel() : ViewModel() {
    lateinit var user: UserDto
    fun login(username: String, password: String) {
        user = FakeDataFactory.createUser()
        user.displayName = username
    }
    fun create(username: String, password: String) {}
    fun logout() {}
}