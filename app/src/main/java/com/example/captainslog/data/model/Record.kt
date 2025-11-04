package com.example.captainslog.data.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class RecordViewModel() : ViewModel() {
//    private val recorder: AudioRecorder,
//    private val ws: HttpClient,
//    private val notesRepo: NotesRepository
    val liveText: StateFlow<String> = MutableStateFlow("Preview transcript goes here.")
    val isRecording: StateFlow<Boolean> = MutableStateFlow(false)
    val elapsedMs: StateFlow<Long> = MutableStateFlow(0)
    fun startRecording() {}
    fun stopRecording(): Result<String> {
        return TODO("Provide the return value")
    }
}