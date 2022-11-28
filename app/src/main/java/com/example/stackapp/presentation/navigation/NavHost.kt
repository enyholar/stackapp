package com.example.stackapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.stackapp.presentation.navigation.UserListDestination.userListGraph

@Composable
internal fun StackNavHost(
    navController: NavHostController,
    modifier: Modifier,
    startDestination: String,
){
    NavHost(
        navController = navController,
       startDestination = startDestination,
        modifier = modifier
    ) {
        userListGraph()
    }
}