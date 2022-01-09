package com.joaodevsantos.noteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joaodevsantos.noteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int
) {
    companion object {
        val colors = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen)
    }
}
