package com.joaodevsantos.noteapp.feature_note.domain.use_case

data class NoteUseCases (
    private val getNoteUseCases: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
)