package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class RecordViewModel() : ViewModel() {
//    private val recorder: AudioRecorder,
//    private val ws: HttpClient,
//    private val notesRepo: NotesRepository
    val liveText: StateFlow<String> = TODO()
    val isRecording: StateFlow<Boolean>
    val elapsedMs: StateFlow<Long>
    fun startRecording() {}
    fun stopRecording(): Result<String> {
        return TODO("Provide the return value")
    }
}