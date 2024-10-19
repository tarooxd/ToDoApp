package com.example.todoapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.viewModel.UserViewModel

@Composable
fun LoginScreen(onNavigate: (String) -> Unit, viewModel: UserViewModel) {
    var userId: String = ""
    var loginTextField by remember { mutableStateOf("") }
    var passwordTextField by remember { mutableStateOf("") }
    var emailRegisterTextField by remember { mutableStateOf("") }
    var passwordRegisterTextField by remember { mutableStateOf("") }
    var passwordConfirmationTextField by remember { mutableStateOf("") }
    var loginRegisterTextField by remember { mutableStateOf("") }
    var showRegister by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Exibir erro, se existir
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        if (showRegister) {
            Dialog(onDismissRequest = {
                loginRegisterTextField = ""
                passwordRegisterTextField = ""
                emailRegisterTextField = ""
                showRegister = false
            }) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = loginRegisterTextField,
                        onValueChange = { newText -> loginRegisterTextField = newText },
                        label = { Text(text = "Login") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = passwordRegisterTextField,
                        onValueChange = { newText -> passwordRegisterTextField = newText },
                        label = { Text(text = "Password") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = passwordConfirmationTextField,
                        onValueChange = { newText -> passwordConfirmationTextField = newText },
                        label = { Text(text = "Password Confirmation") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailRegisterTextField,
                        onValueChange = { newText -> emailRegisterTextField = newText },
                        label = { Text(text = "Email") }
                    )
                    Button(onClick = {
                        if (passwordRegisterTextField == passwordConfirmationTextField) {
                            viewModel.addUser(
                                loginRegisterTextField,
                                passwordRegisterTextField,
                                emailRegisterTextField,
                                onSuccess = {
                                    showRegister = false
                                },
                                onFailure = { e -> errorMessage = e.message ?: "Erro desconhecido" }
                            )
                        } else {
                            errorMessage = "As senhas não coincidem!"
                        }
                    }) {
                        Text(text = "Register now")
                    }
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginTextField,
            onValueChange = { newText -> loginTextField = newText },
            label = { Text(text = "Login") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordTextField,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { newText -> passwordTextField = newText },
            label = { Text(text = "Password") }
        )
        Row(
            modifier = Modifier.align(Alignment.End),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = { showRegister = true }
            ) {
                Text(text = "Register")
            }
            Button(
                onClick = {
                    viewModel.loginUser(
                        loginTextField,
                        passwordTextField,
                        onSuccess = { userId ->
                            onNavigate(userId)
                        },
                        onFailure = { e -> errorMessage = e.message ?: "Erro no login" }
                    )
                }
            ) {
                Text(text = "Login")
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen({})
//}
