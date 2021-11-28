package com.example.todolistbyjetpackcompose.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.todolistbyjetpackcompose.utils.StateConverters
import kotlinx.android.parcel.Parcelize

enum class TodoState(val value: Int) {
    NotDone(0),
    Done(1);

    companion object {
        fun from(value: Int): TodoState {
            return values().find { it.value == value} ?: NotDone
        }
    }
}

@Parcelize
@TypeConverters(StateConverters::class)
@Entity(
    tableName = "todoList"
)
data class TodoItem(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String,
    var description: String,
    var state: TodoState
): Parcelable
