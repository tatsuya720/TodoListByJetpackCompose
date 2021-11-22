package com.example.todolistbyjetpackcompose.repository

import android.content.Context
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.model.database.TodoListDao
import com.example.todolistbyjetpackcompose.model.database.TodoListDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
): ListRepository {

    override fun addList(todoItem: TodoItem) {
        //TODO("Not yet implemented")
    }

    override suspend fun loadList() { //: List<TodoItem> {
        val db = TodoListDatabase.getInstance(context = context)
       // TODO("Not yet implemented")


    }

    override fun removeListById(id: Int) {
       // TODO("Not yet implemented")
    }

    override fun updateStateById(id: Int, state: TodoState) {
       // TODO("Not yet implemented")
    }
}