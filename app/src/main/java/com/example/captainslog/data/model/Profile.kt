package com.example.captainslog.data.model

class ProfileViewModel(
    private val userRepo: UserRepository,
    private val notesRepo: NotesRepository
) : ViewModel() {
    val selectedUser: StateFlow<UserDto?>
    val publicNotes: StateFlow<List<NoteDto>>
    fun loadUserProfile(userId: String)
    fun refreshNotes()
}