package com.example.todoapp.dto

data class CheckUserRequest(
    val username: String,
    val password: String,
)