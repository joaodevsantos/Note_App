package com.joaodevsantos.noteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaodevsantos.noteapp.feature_note.domain.model.Note
import com.joaodevsantos.noteapp.feature_note.domain.use_case.NoteUseCases
import com.joaodevsantos.noteapp.feature_note.domain.util.NoteOrder
import com.joaodevsantos.noteapp.feature_note.presentation.notes.components.NotesEvent
import com.joaodevsantos.noteapp.feature_note.presentation.notes.components.NotesState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    fun onEvent(notesEvent: NotesEvent) {
        when(notesEvent){
            is NotesEvent.Order -> {
                if(state.value.noteOrder::class == notesEvent.noteOrder::class
                    && state.value.noteOrder.orderType == notesEvent.noteOrder.orderType) {
                    return
                }

                getNotes(notesEvent.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(notesEvent.note)
                    recentlyDeletedNote = notesEvent.note
                }
            }
            is NotesEvent.RestoreNotes -> {
                viewModelScope.launch {
                    noteUseCases.addNote.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notesList ->
                _state.value = state.value.copy(
                    notesList = notesList,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}