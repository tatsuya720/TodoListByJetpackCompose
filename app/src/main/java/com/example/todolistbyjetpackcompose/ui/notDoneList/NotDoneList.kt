package com.example.todolistbyjetpackcompose.ui.notDoneList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.ui.listItem.TodoListItem

@ExperimentalMaterialApi
@Composable
fun NotDoneList(itemList: List<TodoItem>, onClickAddBtn: () -> Unit, onClickListItem:(todoItem: TodoItem) -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onClickAddBtn() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            } },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn {
            items(itemList) { item ->
                TodoListItem(item) {
                    onClickListItem(item)
                }
            }
        }
    }
    
}

@Preview
@Composable
fun NotDoneListPreview() {
    //NotDoneList()
}