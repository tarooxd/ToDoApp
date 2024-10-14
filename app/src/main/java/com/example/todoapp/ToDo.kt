package com.example.todoapp

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.Date

data class ToDo(
    var id: Int,
    var title: String,
    var date: Date,
    var status: Boolean
)
