package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import com.example.captainslog.data.api.NoteDto
import com.example.captainslog.data.api.UserDto
import com.example.captainslog.data.repo.FriendsRepository
import com.example.captainslog.data.repo.NotesRepository
import kotlinx.coroutines.flow.StateFlow

// TODO: determine how transcriptSegment should be created and used - can change if overkill
class TranscriptSegment{}

class NoteDetailViewModel(
    private val notesRepo: NotesRepository,
    private val friendsRepo: FriendsRepository
) : ViewModel() {
    val note: StateFlow<NoteDto?> = TODO("Initialize this")
    val transcript: StateFlow<List<TranscriptSegment>>
    val isPlaying: StateFlow<Boolean>
    val friendsList: StateFlow<List<UserDto>>
    fun loadNoteDetail(noteId: String) {}
    fun play() {}
    fun pause() {}
    fun share(noteId: String, friendIds: List<String>) {}
}