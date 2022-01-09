package com.joaodevsantos.noteapp.feature_note.presentation.notes.components

import com.joaodevsantos.noteapp.feature_note.domain.model.Note
import com.joaodevsantos.noteapp.feature_note.domain.util.NoteOrder
import com.joaodevsantos.noteapp.feature_note.domain.util.OrderType

data class NotesState (
    val notesList: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)