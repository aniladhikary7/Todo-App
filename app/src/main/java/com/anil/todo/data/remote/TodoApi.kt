package com.anil.todo.data.remote

import com.anil.todo.data.remote.dto.TodoResponseItem
import retrofit2.http.GET

interface TodoApi {

    @GET("todos")
    suspend fun getTodoList(): List<TodoResponseItem>

}