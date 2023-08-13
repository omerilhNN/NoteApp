package com.omrilhn.noteapp.repository

import com.omrilhn.noteapp.data.NoteDatabase
import com.omrilhn.noteapp.data.NoteDatabaseDao
import com.omrilhn.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {//We have acces to DAO

    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)
    suspend fun updateNote(note:Note) = noteDatabaseDao.update(note)
    suspend fun deleteNote(note:Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    suspend fun getAllNotes():Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO)
        .conflate()
}