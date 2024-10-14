package com.example.todoapp

import java.time.Instant
import java.util.Date

object ToDoManager {
    private val todoList = mutableListOf<ToDo>()

    fun getAllTodo(): List<ToDo>{
        return todoList
    }
    fun addTodo(title : String){
        todoList.add(ToDo(System.currentTimeMillis().toInt(), title, Date.from(Instant.now()),false))
    }
    fun deleteTodo(id : Int){
       todoList.removeIf{
           it.id==id
       }
    }
    fun editTodo(id: Int,newTitle : String){
     val todo = todoList.find { it.id == id }
        todo?.let {
            it.title = newTitle
        }
    }
}