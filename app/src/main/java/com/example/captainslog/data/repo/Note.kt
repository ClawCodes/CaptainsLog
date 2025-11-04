package com.example.captainslog.data.repo

import com.example.captainslog.data.api.NoteDto

class NotesRepository {
    suspend fun myNotes(): List<NoteDto> {
        return TODO("Provide the return value")
    }
    suspend fun sharedNotes(): List<NoteDto> {
        return TODO("Provide the return value")
    }
    suspend fun publicNotes(): List<NoteDto> {
        return TODO("Provide the return value")
    }
    suspend fun getNote(noteId: String): NoteDto {
        return TODO("Provide the return value")
    }
    suspend fun share(noteId: String, friendIds: List<String>) {}
    suspend fun search(query: String): List<NoteDto> {
        return TODO("Provide the return value")
    }
}