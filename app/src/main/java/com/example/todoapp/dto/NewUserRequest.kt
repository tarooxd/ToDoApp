package com.example.todoapp.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewUserRequest (
    val username: String,
    val password: String,
    val email: String
)