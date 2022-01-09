package com.joaodevsantos.noteapp.feature_note.domain.use_case

import com.joaodevsantos.noteapp.feature_note.domain.model.InvalidNoteException
import com.joaodevsantos.noteapp.feature_note.domain.model.Note
import com.joaodevsantos.noteapp.feature_note.domain.repository.NoteRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class AddNote @Inject constructor(
    private val notesRepository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend fun addNote(note: Note) {
        if(note.title.isBlank())
            throw InvalidNoteException("Note's title cannot be empty.")
        if(note.content.isBlank())
            throw InvalidNoteException("Note's content cannot be empty.")

        notesRepository.addNote(note)
    }
}