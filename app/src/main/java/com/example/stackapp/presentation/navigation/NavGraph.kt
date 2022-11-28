package com.example.stackapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.stackapp.presentation.userList.UserSearchScreen

object UserListDestination : StackNavigation {
    override val destination: String = "choose_your_plan_destination"
    override val route: String = "choose_your_plan_route"

    fun NavGraphBuilder.userListGraph() {
        composable(route = UserListDestination.route) {
            UserSearchScreen()
        }
    }
}