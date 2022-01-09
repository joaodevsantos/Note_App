package com.joaodevsantos.noteapp.feature_note.domain.use_case

import com.joaodevsantos.noteapp.feature_note.domain.model.Note
import com.joaodevsantos.noteapp.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val notesRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        notesRepository.deleteNote(note)
    }
}