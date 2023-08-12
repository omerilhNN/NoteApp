package com.omrilhn.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl") //Creating table
data class Note(
    @PrimaryKey
    val id : UUID = UUID.randomUUID(),//everytime create random ID

    @ColumnInfo("note_title")
    val title:String,

    @ColumnInfo("note_description")
    val description : String,

    @ColumnInfo("note_entry_date")
    val entryDate : Date = Date.from(Instant.now())

)
