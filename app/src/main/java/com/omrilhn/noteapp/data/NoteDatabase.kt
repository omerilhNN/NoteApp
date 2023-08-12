package com.omrilhn.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omrilhn.noteapp.model.Note

@Database(entities = [Note::class],version = 1, exportSchema = false)
abstract  class NoteDatabase: RoomDatabase(){
    abstract fun noteDao():NoteDatabaseDao //DAO: Database access object



}