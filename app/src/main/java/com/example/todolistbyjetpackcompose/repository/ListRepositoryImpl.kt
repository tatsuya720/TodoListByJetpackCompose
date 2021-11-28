package com.example.todolistbyjetpackcompose.repository

import android.content.Context
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.model.database.TodoListDao
import com.example.todolistbyjetpackcompose.model.database.TodoListDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
): ListRepository {

    override fun addList(todoItem: TodoItem) {
        //TODO("Not yet implemented")
    }

    override fun loadList(): Flow<List<TodoItem>> {
        val db = TodoListDatabase.getInstance(context = context)
        return db.listItemDao().all()
    }

    override fun loadList(todoState: TodoState): Flow<List<TodoItem>> {
        val db = TodoListDatabase.getInstance(context = context)
        return db.listItemDao().getList(todoState.value)
    }

    override fun removeListById(id: Int) {
       // TODO("Not yet implemented")
    }

    override fun updateStateById(id: Int, state: TodoState) {
       // TODO("Not yet implemented")
    }
}