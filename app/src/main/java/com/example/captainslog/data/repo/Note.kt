package com.example.captainslog.data.repo

import com.example.captainslog.data.api.FakeDataFactory
import com.example.captainslog.data.api.NoteDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotesRepository {

    private val _myNotes = MutableStateFlow<List<NoteDto>>(emptyList())
    val myNotes: StateFlow<List<NoteDto>> = _myNotes


    private val _sharedNotes = MutableStateFlow<List<NoteDto>>(emptyList())
    val sharedNotes: StateFlow<List<NoteDto>> = _sharedNotes


    private val _otherNotes = MutableStateFlow<List<NoteDto>>(emptyList())
    val otherNotes: StateFlow<List<NoteDto>> = _otherNotes

    init {
        val initialNotes = List(10) { FakeDataFactory.createNote() }
        val initSharedNotes = List(20) { FakeDataFactory.createNote() }
        val initialOthers = List(30) { FakeDataFactory.createNote() }
        _myNotes.value = initialNotes
        _sharedNotes.value = initSharedNotes
        _otherNotes.value = initialOthers
    }

    suspend fun myNotes(): List<NoteDto> {
        return myNotes.value
    }
    suspend fun sharedNotes(): List<NoteDto> {
        return sharedNotes.value
    }
    suspend fun publicNotes(): List<NoteDto> {
        return otherNotes.value
    }
    suspend fun getNote(noteId: String): NoteDto {
        return TODO("Provide the return value")
    }
    suspend fun share(noteId: String, friendIds: List<String>) {}

    suspend fun search(query: String): List<NoteDto> {
        return TODO("Provide the return value")
    }

    fun createNote(transcript: String){
        // Create noteDto and add to _myNotes
    }
}