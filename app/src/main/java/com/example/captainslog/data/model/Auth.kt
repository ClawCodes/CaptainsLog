package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel() : ViewModel() {
    // TODO: decide if uiState is necessary for PoC
//    val uiState: StateFlow<AuthState>
    fun login(username: String, password: String) {}
    fun create(username: String, password: String) {}
    fun logout() {}
}