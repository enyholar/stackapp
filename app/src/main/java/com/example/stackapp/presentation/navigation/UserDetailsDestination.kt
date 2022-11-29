package com.example.stackapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.stackapp.presentation.userList.UsersDetailsScreen

object UserDetailsDestination : StackNavigation {
    override val destination: String = "user_details_destination"
    override val route: String = "user_details_route"

    fun NavGraphBuilder.userDetailsGraph( navigateUp: () -> Unit) {
        composable(route = UserDetailsDestination.route) {
            UsersDetailsScreen(navigateUp = navigateUp)
        }
    }
}

