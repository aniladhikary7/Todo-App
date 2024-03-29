package com.anil.todo.domain.repository

import com.anil.todo.data.local.AppDatabase
import com.anil.todo.data.local.TodoEntity
import com.anil.todo.data.local.asDomainModel
import com.anil.todo.data.remote.TodoApi
import com.anil.todo.data.remote.dto.asDatabaseModel
import com.anil.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoApi: TodoApi,
    private val appDatabase: AppDatabase
) {


    val todos: Flow<List<Todo>?> =
        appDatabase.todoDao.getTodos().map { it?.asDomainModel() }

    suspend fun refreshTodos() {
        try {
            val todos = todoApi.getTodoList()
            appDatabase.todoDao.insertTodo(todos.asDatabaseModel())
        } catch (e: Exception) {
            //Timber.w(e)
        }
    }

    suspend fun updateTodo(todo: TodoEntity) = appDatabase.todoDao.updateTodoItem(todo = todo)

}