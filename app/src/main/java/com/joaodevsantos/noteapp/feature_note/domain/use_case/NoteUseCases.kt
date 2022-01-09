package com.joaodevsantos.noteapp.feature_note.domain.use_case

import javax.inject.Inject

data class NoteUseCases @Inject constructor(
    private val getNoteUseCases: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
)