package com.example.captainslog.ui.record

import android.annotation.SuppressLint
import androidx.activity.result.launch
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captainslog.data.api.FakeDataFactory
import com.example.captainslog.data.api.FakeDataFactory.createNoteFromTranscript
import com.example.captainslog.data.api.NoteDto
import com.example.captainslog.data.model.NotesViewModel
import com.example.captainslog.data.model.RecordViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordScreen(vm: RecordViewModel, notesViewModel: NotesViewModel, onGoToNotes: () -> Unit, onGoToFriends: () -> Unit){
    var isRecording by remember { mutableStateOf(false) }
    // TODO: update transcript to use vm recording methods
    var transcript by remember { mutableStateOf("") }
    // val transcript by vm.transcript.collectAsState() // assuming vm exposes a Flow or LiveData
    val coroutineScope = rememberCoroutineScope()

    suspend fun trickleTranscript(t: String): NoteDto {
        val words = t.split(Regex("\\s+"))
        val stringBuilder = StringBuilder()
        for (word in words) {
            stringBuilder.append(word).append(" ")
            transcript = stringBuilder.toString()
            delay(300)
        }
        isRecording = false
        return createNoteFromTranscript(stringBuilder.toString())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recording") },
                actions = {
                    TextButton(onClick = onGoToFriends) {
                        Text("Find friends", color = MaterialTheme.colorScheme.primary)
                    }
                    TextButton(onClick = onGoToNotes) {
                        Text("Go to Notes", color = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Scrollable text box for live transcript
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = transcript.ifEmpty { "Your transcription will appear here..." },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons row: Record + Playback
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Record button
                Button(
                    onClick = {
                        isRecording = !isRecording
                        if (isRecording) {
                            transcript = ""
                            coroutineScope.launch {
                                val fakeNote = FakeDataFactory.createNote()
                                val note = trickleTranscript(fakeNote.transcript)
                                notesViewModel.addNote(note)
                            }
                        } else transcript = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRecording)
                            MaterialTheme.colorScheme.errorContainer
                        else
                            MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(if (isRecording) "Stop" else "Record")
                }

                // Playback button
                // TODO: add vm playback method
                OutlinedButton(onClick = { println("TODO: PLAYING TRANSCRIPT") }) {
                    Text("Playback")
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecordScreenPreview() {
    val fakeVm = RecordViewModel()

    MaterialTheme {
        RecordScreen(vm = fakeVm, NotesViewModel() , onGoToFriends = {}, onGoToNotes = {})
    }
}

