package com.example.stackapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.stackapp.presentation.navigation.UserDetailsDestination.userDetailsGraph
import com.example.stackapp.presentation.navigation.UserListDestination.userListGraph

object MainDestination : StackNavigation {
    override val destination: String = "main_destination"
    override val route: String = "main_route"

    fun NavGraphBuilder.mainGraph(
        navigateToScreen: (StackNavigation, String?) -> Unit,
        navigateUp: () -> Unit,
    ) {
        navigation(
            route = MainDestination.route,
            startDestination = UserListDestination.route,
        ) {
            userListGraph(
                navigateToUserDetailsScreen = {
                    navigateToScreen(UserDetailsDestination, null) },
            )
            userDetailsGraph(navigateUp = navigateUp)
        }
    }
}