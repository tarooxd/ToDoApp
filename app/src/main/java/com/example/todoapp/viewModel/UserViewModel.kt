package com.example.todoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeendpoints.api.RetrofitInstance
import com.example.todoapp.dto.CheckUserRequest
import com.example.todoapp.dto.NewUserRequest
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val apiService = RetrofitInstance.api

    fun addUser(username: String, password: String, email: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.registerUser(NewUserRequest(username, password, email))
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(Exception("Erro no cadastro"))
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    fun loginUser(username: String, password: String, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.checkUser(CheckUserRequest(username, password))
                if (response.isSuccessful) {
                    val userId = response.body()?.userId ?: throw Exception("Falha ao obter ID do usuário")
                    onSuccess(userId)
                } else {
                    onFailure(Exception("Login inválido"))
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
}
