package com.omrilhn.noteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omrilhn.noteapp.data.NotesDataSource
import com.omrilhn.noteapp.model.Note
import com.omrilhn.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() { //USING HILT with Inject
    // private var noteList = mutableStateListOf<Note>() -- Commented out because it is  hard to work with mutableState for ROOM

    private val _noteList  = MutableStateFlow<List<Note>>(emptyList()) // Invoke it with Flow -> in order to use it into this class
    val noteList = _noteList.asStateFlow()// It is public so you can use it interclasses


    init { //we want to have some data when we Initialize this class
    //noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{listOfNotes-> // GO and GET THE INFORMATION
                    if(listOfNotes.isNullOrEmpty()){ // If that list is emptyorNull then return log message
                        Log.d("EMPTY",":Empty list")
                    }else{//Else set the values
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

    suspend fun addNote(note:Note) = viewModelScope.launch { repository.addNote(note) } //Suspend : cuz we are use coroutines
    suspend fun updateNote(note:Note) = viewModelScope.launch { repository.updateNote(note) }
    suspend fun removeNote(note:Note) = viewModelScope.launch { repository.deleteNote(note) }
}