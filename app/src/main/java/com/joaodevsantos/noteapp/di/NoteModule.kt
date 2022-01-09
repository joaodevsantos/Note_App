package com.joaodevsantos.noteapp.di

import android.app.Application
import androidx.room.Room
import com.joaodevsantos.noteapp.feature_note.data.data_source.NoteDatabase
import com.joaodevsantos.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.joaodevsantos.noteapp.feature_note.domain.repository.NoteRepository
import com.joaodevsantos.noteapp.feature_note.domain.use_case.AddNote
import com.joaodevsantos.noteapp.feature_note.domain.use_case.DeleteNote
import com.joaodevsantos.noteapp.feature_note.domain.use_case.GetNotes
import com.joaodevsantos.noteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoteModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        NoteDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase) : NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun providesNoteUseCase(
        getNotes: GetNotes,
        deleteNote: DeleteNote,
        addNote: AddNote
    ): NoteUseCases = NoteUseCases(getNotes, deleteNote, addNote)
}