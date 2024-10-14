package com.example.todoapp

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.bson.codecs.pojo.annotations.BsonId
import org.mongodb.kbson.ObjectId
import java.util.Date

data class ToDo(
    @BsonId
    var id: ObjectId = ObjectId.invoke(),
    var title: String,
    var date: Date,
    var status: Boolean
)
