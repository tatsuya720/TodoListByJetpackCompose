package com.example.todolistbyjetpackcompose.utils

import androidx.room.TypeConverter
import com.example.todolistbyjetpackcompose.model.TodoState

class StateConverters {

    @TypeConverter
    fun TodoStateToInt(state: TodoState): Int = state.value

    @TypeConverter
    fun IntToTodoState(state: Int): TodoState = TodoState.from(state)
}