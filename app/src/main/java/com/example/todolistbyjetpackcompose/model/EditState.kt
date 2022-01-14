package com.example.todolistbyjetpackcompose.model

enum class EditState(val value:Int) {
    Add(0),
    Edit(1);

    companion object {
        fun from(value: Int): EditState {
            return values().find { it.value == value} ?: Add
        }
    }
}