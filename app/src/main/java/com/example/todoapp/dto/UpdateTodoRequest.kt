package com.example.todoapp.dto

data class UpdateTodoRequest(
    val title: String,
    val status: Boolean,
)