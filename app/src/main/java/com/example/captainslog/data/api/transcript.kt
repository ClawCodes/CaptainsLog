package com.example.captainslog.data.api

import kotlinx.serialization.Serializable

@Serializable
//used for sending audio chunks for real time translation
data class TranscriptChunkDto(
    val noteSessionId: String,
    val sequence: Long,
    val text: String,
    val isFinal: Boolean
)