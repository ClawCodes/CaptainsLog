package com.example.captainslog.data.model

class RecordViewModel(
    private val recorder: AudioRecorder,
    private val ws: HttpClient,
    private val notesRepo: NotesRepository
) : ViewModel() {
    val liveText: StateFlow<String>
    val isRecording: StateFlow<Boolean>
    val elapsedMs: StateFlow<Long>
    fun startRecording()
    fun stopRecording(): Result<String>
}