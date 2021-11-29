package com.example.todolistbyjetpackcompose.ui.notDoneList

import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistbyjetpackcompose.model.TodoItem

@ExperimentalMaterialApi
@Composable
fun NotDoneList(itemList: List<TodoItem>) {
    Scaffold(
        floatingActionButton =  { FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }},
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn {
            items(itemList) { item ->
                NotDoneListItem(title = item.title, description = item.description)
            }
        }
    }
    
}

@Preview
@Composable
fun NotDoneListPreview() {
    //NotDoneList()
}