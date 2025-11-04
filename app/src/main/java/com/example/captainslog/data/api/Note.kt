package com.example.captainslog.data.api

import kotlinx.serialization.Serializable


@Serializable
data class NoteDto(
    val id: String,
    val ownerId: String,
    val createdAt: Long,
    val transcript: String,
    val audioUrl: String?, //reference to blob storage url
    val durationMs: Long,
    val sharedWith: List<String> = emptyList()
)

// A Dummy Note for testing
fun testNote(id: String, transcript: String): NoteDto {
    return NoteDto(
        id = id,
        ownerId = "0",
        createdAt = 0,
        transcript = transcript,
        audioUrl = null,
        durationMs = 0,
        sharedWith = emptyList()
    )
}
