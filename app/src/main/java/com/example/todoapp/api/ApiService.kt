package com.example.testeendpoints.api

import com.example.todoapp.dto.CheckUserRequest
import com.example.todoapp.dto.CheckUserResponse
import com.example.todoapp.dto.NewTodoRequest
import com.example.todoapp.dto.NewUserRequest
import com.example.todoapp.dto.NewUserResponse
import com.example.todoapp.dto.UpdateTodoRequest
import com.example.todoapp.model.ToDo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("gettodos/{userId}")
    suspend fun getTodos(
        @Path("userId") id: String
    ): List<ToDo>

    @POST("newtodo/{userId}")
    suspend fun addTodos(
        @Path("userId") id: String,
        @Body newTodoRequest: NewTodoRequest
    ): Response<NewTodoRequest>

    @PUT("updatetodos/{id}")
    suspend fun editTodos(
        @Path("id") id: String,
        @Body updateTodoRequest: UpdateTodoRequest
    ): Response<UpdateTodoRequest>

    @DELETE("deletetodo/{id}")
    suspend fun deleteTodo(
        @Path("id") id: String
    ): Response<Unit>

    @POST("newuser")
    suspend fun registerUser(
        @Body newUserRequest: NewUserRequest
    ): Response<NewUserResponse>

    @POST("checkuser")
    suspend fun checkUser(
        @Body checkUserRequest: CheckUserRequest
    ): Response<CheckUserResponse>
}