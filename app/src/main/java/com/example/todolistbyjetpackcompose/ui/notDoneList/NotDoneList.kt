package com.example.todolistbyjetpackcompose.NotDoneList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotDoneList() {
    Scaffold(
        floatingActionButton =  { FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }},
        floatingActionButtonPosition = FabPosition.End
    ) {
    }
    
}

@Preview
@Composable
fun NotDoneListPreview() {
    NotDoneList()
}