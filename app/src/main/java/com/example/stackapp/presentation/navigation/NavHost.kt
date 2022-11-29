package com.example.stackapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.stackapp.presentation.navigation.MainDestination.mainGraph

@Composable
internal fun StackNavHost(
   destinationState: RegistrationDestinationState,
    modifier: Modifier,
    startDestination: String,
){
    NavHost(
        navController = destinationState.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        mainGraph(
            navigateToScreen = destinationState::navigate,
            navigateUp = destinationState::navigateUp
        )
    }
}