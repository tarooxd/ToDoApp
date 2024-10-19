package com.example.todoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeendpoints.api.RetrofitInstance
import com.example.todoapp.dto.NewTodoRequest
import com.example.todoapp.dto.UpdateTodoRequest
import com.example.todoapp.model.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ToDoViewModel : ViewModel() {

    private val apiService = RetrofitInstance.api
    val _todos = MutableStateFlow<List<ToDo>>(emptyList())
    val todos = _todos.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    suspend fun getAllTodo(userId: String) {
        try {
            val response = apiService.getTodos(userId)
            _todos.value = response
            _error.value = null
        } catch (e: Exception) {
            _error.value = "Por enquanto nada novo"
        }
    }

    suspend fun addTodo(title: String, userId: String) {
        viewModelScope.launch {
            try {
                apiService.addTodos(userId, newTodoRequest = NewTodoRequest(title))
                _error.value = null
            } catch (e: Exception) {
                println(e)
            }
        }.join()
        getAllTodo(userId)
    }

    suspend fun deleteTodo(id: String, userId: String) {
        viewModelScope.launch {
            try {
                apiService.deleteTodo(id)
            } catch (e: Exception) {
                println(e)
            }
        }.join()
        getAllTodo(userId)
    }

    suspend fun editTodo(id: String, newTitle: String, status: Boolean, userId: String) {
        viewModelScope.launch {
            try {
                apiService.editTodos(
                    id,
                    updateTodoRequest = UpdateTodoRequest(title = newTitle, status = status)
                )
            } catch (e: Exception) {
                println(e)
            }
        }.join()
        getAllTodo(userId)
    }
}