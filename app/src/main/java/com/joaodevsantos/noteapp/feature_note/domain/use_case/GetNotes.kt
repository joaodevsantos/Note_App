package com.joaodevsantos.noteapp.feature_note.domain.use_case

import com.joaodevsantos.noteapp.feature_note.domain.model.Note
import com.joaodevsantos.noteapp.feature_note.domain.repository.NoteRepository
import com.joaodevsantos.noteapp.feature_note.domain.util.NoteOrder
import com.joaodevsantos.noteapp.feature_note.domain.util.NoteOrder.Title
import com.joaodevsantos.noteapp.feature_note.domain.util.NoteOrder.Date
import com.joaodevsantos.noteapp.feature_note.domain.util.NoteOrder.Color
import com.joaodevsantos.noteapp.feature_note.domain.util.OrderType.Ascending
import com.joaodevsantos.noteapp.feature_note.domain.util.OrderType.Descending
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val notesRepository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = Date(Descending)
    ): Flow<List<Note>> {
        return notesRepository.getAllNotes().map { notesList ->
            when(noteOrder.orderType) {
                is Ascending -> {
                    when(noteOrder) {
                        is Title -> notesList.sortedBy { note -> note.title }
                        is Date -> notesList.sortedBy { note -> note.timestamp }
                        is Color -> notesList.sortedBy { note -> note.color }
                    }
                }
                is Descending -> {
                    when(noteOrder) {
                        is Title -> notesList.sortedByDescending { note -> note.title }
                        is Date -> notesList.sortedByDescending { note -> note.timestamp }
                        is Color -> notesList.sortedByDescending { note -> note.color }
                    }
                }
            }
        }
    }
}