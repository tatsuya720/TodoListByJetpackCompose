package com.example.todolistbyjetpackcompose.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.paging.PagingSource
import com.example.todolistbyjetpackcompose.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TodoItem)

    @Query("UPDATE todoList SET state= :state WHERE id =:id")
    fun updateStateById(id: Int, state: Int)

    @Query("DELETE FROM todoList WHERE id= :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM todoList ORDER BY `id` ASC")
    fun all(): Flow<List<TodoItem>>

    @Query("DELETE FROM todoList")
    suspend fun deleteAll()

}