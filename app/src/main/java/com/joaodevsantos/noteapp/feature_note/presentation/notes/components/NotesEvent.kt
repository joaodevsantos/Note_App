package com.joaodevsantos.noteapp.feature_note.presentation.notes.components

import com.joaodevsantos.noteapp.feature_note.domain.model.Note
import com.joaodevsantos.noteapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    class Order(val noteOrder: NoteOrder): NotesEvent()
    class DeleteNote(val note: Note): NotesEvent()
    object RestoreNotes: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
