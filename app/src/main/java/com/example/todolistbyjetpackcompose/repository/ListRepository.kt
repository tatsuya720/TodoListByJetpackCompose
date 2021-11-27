package com.example.todolistbyjetpackcompose.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    suspend fun loadList(): Flow<List<TodoItem>>

    fun addList(todoItem: TodoItem)

    fun removeListById(id: Int)

    fun updateStateById(id: Int, state: TodoState)
//    fun loadList() = dao.all()

//    suspend fun addList(todoItem: TodoItem) {
////        dao.insert(todoItem)
//    }

//    fun removeListById(id: Int) {
////        dao.deleteById(id)
//    }

//    fun updateStateById(id: Int, state: TodoState) {
////        dao.updateStateById(id, state.value)
//    }
}