package com.anil.todo.data.remote.dto

import com.anil.todo.data.local.TodoEntity

data class TodoResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)


fun List<TodoResponseItem>.asDatabaseModel(): List<TodoEntity> {
    return map {
        TodoEntity(
            id = it.id,
            title = it.title,
            completed = it.completed,
            userId = it.userId
        )
    }
}