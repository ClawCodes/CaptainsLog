package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.NoteDto
import com.example.captainslog.data.repo.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

open class NotesViewModel() : ViewModel() {
    private val repo: NotesRepository = NotesRepository()
    open val myNotes: StateFlow<List<NoteDto>> = repo.myNotes
    open val sharedNotes: StateFlow<List<NoteDto>> = repo.sharedNotes
    open val publicNotes: StateFlow<List<NoteDto>> = repo.otherNotes

    fun refresh() {}

    fun search(query: String): Flow<List<NoteDto>> {
        return TODO("Provide the return value")
    }
    fun share(noteId: String, friendIds: List<String>) {}
    fun addNote(note: NoteDto) {
        repo.createNote(note)
    }
}