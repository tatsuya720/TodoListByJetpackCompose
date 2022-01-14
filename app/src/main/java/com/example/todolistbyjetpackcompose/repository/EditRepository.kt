package com.example.todolistbyjetpackcompose.repository

import com.example.todolistbyjetpackcompose.model.TodoItem

interface EditRepository {
    suspend fun add(todoItem: TodoItem)
    suspend fun update(todoItem: TodoItem)
}