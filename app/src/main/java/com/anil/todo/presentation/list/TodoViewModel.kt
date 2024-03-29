package com.anil.todo.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anil.todo.data.local.TodoEntity
import com.anil.todo.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    var uiState by mutableStateOf(TodoUiState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.refreshTodos()
            todoRepository.todos.collect { list ->
                withContext(Dispatchers.Main) {
                    uiState = if (list.isNullOrEmpty()) {
                        uiState.copy(offline = true)
                    } else {
                        uiState.copy(
                            list = list,
                            offline = false
                        )
                    }
                }
            }
        }
    }

    fun updateNote(todo: TodoEntity) = viewModelScope.launch {
        todoRepository.updateTodo(todo = todo)
    }

}