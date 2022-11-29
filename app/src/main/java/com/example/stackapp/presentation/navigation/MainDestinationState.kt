@file:OptIn(ExperimentalAnimationApi::class)

package com.example.stackapp.presentation.navigation

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberRegistrationDestination(navController: NavHostController = rememberNavController()): RegistrationDestinationState {
    val hostActivity = LocalContext.current

    return remember(navController) {
        RegistrationDestinationState(navController)
    }
}

@Stable
class RegistrationDestinationState(
    val navController: NavHostController,
) {

    fun navigateUp() {
        navController.popBackStack()
    }

    fun navigate(destination: StackNavigation, route: String? = null) {
        navController.navigate(route ?: destination.route)
    }

    fun popBackStack(destination: StackNavigation, route: String? = null) {
        navController.popBackStack(route ?: destination.route, false)
    }
}
