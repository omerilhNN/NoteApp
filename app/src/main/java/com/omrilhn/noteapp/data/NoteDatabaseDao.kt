package com.omrilhn.noteapp.data

import androidx.room.Dao
import androidx.room.Query
import com.omrilhn.noteapp.model.Note

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_tbl")
    fun getNotes(): List<Note> //Go SELECT ALL FROM NOTES_TBL AND RETURN LIST OF NOTE 
}
