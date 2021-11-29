package com.example.todolistbyjetpackcompose.ui.doneList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.ui.listItem.TodoListItem


@ExperimentalMaterialApi
@Composable
fun DoneList(itemList: List<TodoItem>) {
    Scaffold {
        LazyColumn {
            items(itemList) { item ->
                //NotDoneListItem(title = item.title, description = item.description)
                TodoListItem(
                    title = item.title,
                    description = item.description,
                    state = TodoState.Done
                )
            }
        }
    }
}