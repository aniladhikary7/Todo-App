package com.anil.todo.presentation.list

import com.anil.todo.domain.model.Todo

data class TodoUiState(
    val list: List<Todo> = listOf(),
    val offline: Boolean = false
)