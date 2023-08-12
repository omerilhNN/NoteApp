package com.omrilhn.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omrilhn.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_tbl")//Go SELECT ALL FROM NOTES_TBL AND RETURN LIST OF NOTE
    fun getNotes(): Flow<List<Note>> //Asyncronious - MutableState but with more options
    @Query ("SELECT * from notes_tbl where id=:id") //Query where id equals the id that we are gonna pass
    suspend fun getNoteById(id:String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE) //When there is an error
    suspend fun insert(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note:Note)

    //If we want to delete everything
    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    //Delete one item
    @Delete
    suspend fun deleteNote(note:Note)

}
