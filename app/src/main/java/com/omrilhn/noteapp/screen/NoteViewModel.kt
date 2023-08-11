package com.omrilhn.noteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.omrilhn.noteapp.data.NotesDataSource
import com.omrilhn.noteapp.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init { //we want to have some data when we Initialize this class
        noteList.addAll(NotesDataSource().loadNotes())

    }

    fun addNote(note:Note){
        noteList.add(note)
    }
    fun removeNote(note:Note){
        noteList.remove(note)
    }
    fun getAllNotes(): List<Note>{
        return noteList
    }
}