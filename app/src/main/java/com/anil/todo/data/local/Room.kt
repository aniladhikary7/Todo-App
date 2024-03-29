package com.anil.todo.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("select * from TodoEntity")
    fun getTodos(): Flow<List<TodoEntity>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTodo(todo: List<TodoEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodoItem(todo: TodoEntity)

}

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}