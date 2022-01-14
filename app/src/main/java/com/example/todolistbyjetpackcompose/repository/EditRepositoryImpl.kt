package com.example.todolistbyjetpackcompose.repository

import android.content.Context
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.database.TodoListDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EditRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
): EditRepository {

    override suspend fun add(todoItem: TodoItem) {
        val db = TodoListDatabase.getInstance(context = context)
        db.listItemDao().insert(todoItem)
    }

    override suspend fun update(todoItem: TodoItem) {
        val db = TodoListDatabase.getInstance(context = context)
        db.listItemDao().update(id= todoItem.id, title = todoItem.title, description = todoItem.description, state = todoItem.state.value)
    }
}