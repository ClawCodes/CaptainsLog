package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.NoteDto
import com.example.captainslog.data.api.UserDto
import com.example.captainslog.data.repo.NotesRepository
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel() : ViewModel() {
    // TODO: determine if needed
//    private val userRepo: UserRepository
    private val notesRepo: NotesRepository = TODO()
    val selectedUser: StateFlow<UserDto?>
    val publicNotes: StateFlow<List<NoteDto>>
    fun loadUserProfile(userId: String) {}
    fun refreshNotes() {}
}