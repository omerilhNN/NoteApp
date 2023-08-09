@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.omrilhn.noteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omrilhn.noteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(){
    Column(modifier = Modifier.padding(6.dp)){
        TopAppBar(title = { Text(text= stringResource(id = R.string.app_name)) },
            actions = {//ACTIONS HELP US TO ADDING ICON TO THE TOPBAR
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "ICON")
            }, colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xFFDADFE3)))

        //Add Content
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){

        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen()
}