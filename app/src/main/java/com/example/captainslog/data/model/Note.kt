package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.NoteDto
import com.example.captainslog.data.repo.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class NotesViewModel() : ViewModel() {
    private val repo: NotesRepository = TODO()
    val myNotes: StateFlow<List<NoteDto>>
    val sharedNotes: StateFlow<List<NoteDto>>
    fun refresh() {}
    fun search(query: String): Flow<List<NoteDto>> {
        return TODO("Provide the return value")
    }
    fun share(noteId: String, friendIds: List<String>) {}
}