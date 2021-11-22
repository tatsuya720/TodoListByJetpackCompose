package com.example.todolistbyjetpackcompose.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistbyjetpackcompose.model.TodoItem

@Database(entities = [TodoItem::class], version = 1, exportSchema = true)
abstract class TodoListDatabase: RoomDatabase() {

    abstract fun listItemDao(): TodoListDao

    companion object {
        @Volatile
        private var instance: TodoListDatabase? = null

        fun getInstance(context: Context): TodoListDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TodoListDatabase::class.java,
                    "todo_database"
                ).build()
            }
        }
    }
}