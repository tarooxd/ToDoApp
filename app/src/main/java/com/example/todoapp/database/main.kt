package com.example.todoapp.database

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.runBlocking


fun getDatabase(): MongoDatabase {
val client = MongoClient.create(connectionString = "mongodb+srv://gabrielimacosta:7A5MehmSYY6U1598@todoapp.hd1ch.mongodb.net/?retryWrites=true&w=majority&appName=ToDoApp")
    return client.getDatabase(databaseName = "ToDoApp")
}

fun main(args: Array<String>){
    val database = getDatabase()

    runBlocking { database.listCollections().collect{
        println(it)
    } }
}