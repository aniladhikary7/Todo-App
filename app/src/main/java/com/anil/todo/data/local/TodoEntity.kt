package com.anil.todo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anil.todo.domain.model.Todo

@Entity
data class TodoEntity constructor(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val completed: Boolean,
    val userId: Int
)

fun List<TodoEntity>.asDomainModel(): List<Todo> {
    return map {
        Todo(
            id = it.id,
            title = it.title,
            completed = it.completed,
            userId = it.userId
        )
    }
}