package com.example.todoapp.repository

import com.example.testeendpoints.api.RetrofitInstance
import com.example.todoapp.model.ToDo

class ToDoRepository {
    suspend fun getTodos(id: String): List<ToDo>{
        return RetrofitInstance.api.getTodos(id)
    }
}