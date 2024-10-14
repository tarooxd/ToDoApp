//package com.example.todoapp.db.data
//
//import com.example.todoapp.ToDo
//import kotlinx.coroutines.flow.Flow
//import org.mongodb.kbson.ObjectId
//
//interface MongoRepository {
//    fun configureTheRealm()
//    fun getData(): Flow<List<ToDo>>
//    suspend fun insertToDo(todo : ToDo)
//    suspend fun updateToDo(todo: ToDo)
//    suspend fun deleteToDo(id: ObjectId)
//}