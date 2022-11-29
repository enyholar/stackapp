package com.example.stackapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.stackapp.model.User
import com.example.stackapp.presentation.userList.UserSearchScreen

object UserListDestination : StackNavigation {
    override val destination: String = "search_users_destination"
    override val route: String = "search_users_route"


    fun NavGraphBuilder.userListGraph(navigateToUserDetailsScreen: (User) -> Unit)  {
        composable(route = UserListDestination.route) {
            UserSearchScreen(navigateToUserDetailsScreen = navigateToUserDetailsScreen)
        }
    }
}