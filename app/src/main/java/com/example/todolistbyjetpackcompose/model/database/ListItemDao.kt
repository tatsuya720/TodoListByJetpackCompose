package com.example.todolistbyjetpackcompose.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.example.todolistbyjetpackcompose.model.ListItem

@Dao
interface ListItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ListItem)

    @Query("UPDATE todoList SET state= :state WHERE id =:id")
    fun updateStateById(id: Int, state: Int)

    @Query("DELETE FROM todoList WHERE id= :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM todoList ORDER BY `id` ASC")
    fun all(): PagingSource<Int, ListItem>

    @Query("DELETE FROM todoList")
    suspend fun deleteAll()
}