package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.todoapp.model.ToDo
import com.example.todoapp.screen.LoginScreen
import com.example.todoapp.screen.ToDoListPage
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.example.todoapp.viewModel.ToDoViewModel
import com.example.todoapp.viewModel.UserViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LoginScreen
                ) {
                    composable<LoginScreen> {
                        LoginScreen(onNavigate = { userId ->
                            navController.navigate(
                                ToDoListPageA(userId = userId)
                            )
                        }, viewModel = userViewModel)
                    }
                    composable<ToDoListPageA> {
                        val args = it.toRoute<ToDoListPageA>()
                        ToDoListPage(viewModel = toDoViewModel, userId = args.userId) }
                }
            }
        }
    }
}

@Serializable
object LoginScreen

@Serializable
data class ToDoListPageA(
    val userId: String
)