package com.example.captainslog.data.repo

import com.example.captainslog.data.api.TranscriptChunkDto
import kotlinx.coroutines.flow.Flow

class TranscriptionRepository {
    val transcript: Flow<TranscriptChunkDto> = TODO("add method to initialize this")
    suspend fun connect(): Unit {}
    suspend fun sendChunk(bytes: ByteArray, seq: Long) {}
    suspend fun close() {}
}