@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.omrilhn.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omrilhn.noteapp.R
import com.omrilhn.noteapp.components.NoteButton
import com.omrilhn.noteapp.components.NoteInputText
import com.omrilhn.noteapp.data.NotesDataSource
import com.omrilhn.noteapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(notes:List<Note>,
               onAddNote: (Note)-> Unit,
               onRemoveNote:(Note)->Unit){
    var title by remember{
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)){
        TopAppBar(title = { Text(text= stringResource(id = R.string.app_name)) },
            actions = {//ACTIONS HELP US TO ADDING ICON TO THE TOPBAR
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "ICON")
            }, colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xFFDADFE3)))

        //Add Content
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){

            NoteInputText( modifier = Modifier.padding(
                top=9.dp, bottom = 8.dp
            ),
                text =title,
                label = "hello",
                onTextChange ={
                    //Check whether this char array does have characters or not
                    if(it.all{char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                } )

            NoteInputText( modifier = Modifier.padding(
                top = 9.dp, bottom = 8.dp
            ),
                text =description,
                label = "hello",
                onTextChange ={
                    if(it.all{char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                } )

            Spacer(modifier = Modifier.padding(3.dp))
            NoteButton(text = "Save",
                onClick = {
                    if(title.isNotEmpty() && description.isNotEmpty()){
                        //If these are not empty -> save and add to list
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                    }
                })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){note->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(it)
                }) // Show each one of the items in the list
            }
        }
    }
}
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note:Note,
    onNoteClicked: (Note) -> Unit
){
    Surface(modifier.padding(4.dp).clip(RoundedCornerShape(topEnd = 33.dp,bottomStart = 33.dp))
        .fillMaxWidth(), color = Color(0xFFDFE6EB), shadowElevation = 6.dp) {
            Column(modifier.clickable {
                    onNoteClicked(note)//WE PROCESS THE PARAMETER WHICH YOU HAVE SENT, LINE: 106
            }
                .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.Start){
                Text(text = note.title,
                    style = MaterialTheme.typography.bodySmall)
                Text(text=note.description,style = MaterialTheme.typography.labelSmall)
//                Text(text=note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM"))
//                    ,style = MaterialTheme.typography.headlineSmall)

            }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}