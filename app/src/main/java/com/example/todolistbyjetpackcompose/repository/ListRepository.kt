package com.example.todolistbyjetpackcompose.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    fun loadList(): Flow<List<TodoItem>>

    fun loadList(todoState: TodoState): Flow<List<TodoItem>>

    fun addList(todoItem: TodoItem)

    fun removeListById(id: Int)

    fun updateStateById(id: Int, state: TodoState)

    suspend fun insert(title: String, description: String, state: TodoState)

    suspend fun findById(id: Long): Flow<TodoItem?>

    suspend fun deleteAll()

}