package com.example.captainslog.ui.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captainslog.data.api.FakeDataFactory
import com.example.captainslog.data.api.NoteDto
import com.example.captainslog.data.api.testNote
import com.example.captainslog.data.model.NotesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class NotesTab(val label: String) {
    MY_NOTES("My Notes"),
    SHARED_NOTES("Shared Notes"),
    PUBLIC_NOTES("Public Notes");
}

@Composable
fun NotesScreen(vm: NotesViewModel, onOpenNote: (String) -> Unit, onExit: () -> Unit){
    var selectedTab by remember { mutableStateOf(NotesTab.MY_NOTES) }
    var searchQuery by remember { mutableStateOf("") }

    val myNotes by vm.myNotes.collectAsState()
    val sharedNotes by vm.sharedNotes.collectAsState()
    val publicNotes by vm.publicNotes.collectAsState()

    val filteredNotes = when (selectedTab) {
        NotesTab.MY_NOTES -> myNotes
        NotesTab.SHARED_NOTES -> sharedNotes
        NotesTab.PUBLIC_NOTES -> publicNotes
    }.filter { it.transcript.contains(searchQuery, ignoreCase = true) }

    Row(modifier = Modifier.fillMaxSize()) {
        // Sidebar Tabs
        Column(
            modifier = Modifier
                .width(140.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NotesTab.entries.forEach { tab ->
                TabItem(
                    label = tab.label,
                    selected = tab == selectedTab,
                    onClick = { selectedTab = tab }
                )
            }
            Button(onClick = onExit) {
                Text("Home")
            }
        }

        // Divider between sidebar and content
        HorizontalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            thickness = DividerDefaults.Thickness, color = MaterialTheme.colorScheme.outlineVariant
        )

        // Main content area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Search bar
            NotesSearchBar(
                onQuery = { query -> searchQuery = query },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            // Notes list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredNotes) { note ->
                    NoteCard(
                        note = note,
                        // TODO: Add vm call to share notes
                        onShare = { noteId -> {println("SHARE NOTE")} },
                        onOpen = { onOpenNote(note.id) }
                    )
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreenPreview() {

    open class localModel : NotesViewModel() {
        override val myNotes: StateFlow<List<NoteDto>> = MutableStateFlow(
            listOf(
                testNote("1", "Finish the Compose UI refactor."),
                testNote("2", "Review architecture diagrams for backend service.")
            )
        )
        override val sharedNotes: StateFlow<List<NoteDto>> = MutableStateFlow(
            listOf(
                testNote("3", "Shared project outline with Alice."),
                testNote("4", "Team notes on distributed caching strategies.")
            )
        )
        override val publicNotes: StateFlow<List<NoteDto>> = MutableStateFlow(
            listOf(
                testNote("5", "Public post about Kotlin coroutines best practices."),
                testNote("6", "Compose Navigation deep dive write-up.")
            )
        )

        open fun shareNote(noteId: String) {}
    }

    MaterialTheme {
        NotesScreen(vm = localModel(), onOpenNote = {}, onExit = {})
    }
}
