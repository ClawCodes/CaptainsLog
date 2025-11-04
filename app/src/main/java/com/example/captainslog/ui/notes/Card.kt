package com.example.captainslog.ui.notes

import androidx.compose.runtime.Composable
import com.example.captainslog.data.api.NoteDto

@Composable
fun NoteCard(note: NoteDto, onShare: (String) -> Unit, onOpen: () -> Unit){}
