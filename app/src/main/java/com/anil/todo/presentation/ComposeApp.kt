package com.anil.todo.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anil.todo.presentation.list.TodoScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.LIST
    ) {
        composable(Route.LIST) { backStackEntry ->
            TodoScreen(onCompleteState = {})
        }
    }
}

object Route {
    const val LIST = "list"
}