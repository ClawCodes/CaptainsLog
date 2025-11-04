package com.example.captainslog.data.model

class NotesViewModel(private val repo: NotesRepository) : ViewModel() {
    val myNotes: StateFlow<List<NoteDto>>
    val sharedNotes: StateFlow<List<NoteDto>>
    fun refresh()
    fun search(query: String): Flow<List<NoteDto>>
    fun share(noteId: String, friendIds: List<String>)
}