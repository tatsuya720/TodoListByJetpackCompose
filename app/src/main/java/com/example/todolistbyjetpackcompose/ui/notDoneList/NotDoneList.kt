package com.example.todolistbyjetpackcompose.ui.notDoneList

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistbyjetpackcompose.model.TodoItem

@Composable
fun NotDoneList(itemList: List<TodoItem>) {
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
    //NotDoneList()
}