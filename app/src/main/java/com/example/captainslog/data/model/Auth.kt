package com.example.captainslog.data.model

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {
    val uiState: StateFlow<AuthState>
    fun login(username: String, password: String)
    fun create(username: String, password: String)
    fun logout()
}