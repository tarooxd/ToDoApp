package com.example.todoapp.model

import java.util.Date

data class ToDo(
    var id: String,
    var title: String,
    var date: Date,
    var status: Boolean,
    var userId: String
)
