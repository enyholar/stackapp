package com.example.stackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.stackapp.presentation.navigation.MainDestination
import com.example.stackapp.presentation.navigation.StackNavHost
import com.example.stackapp.presentation.navigation.rememberRegistrationDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var startDestination: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StackContent()
            }
        }
    }
    @Composable
    private fun StackContent(){
        val destinationState = rememberRegistrationDestination()

 //       val navController = rememberNavController()
        StackNavHost(
            destinationState = destinationState,
            modifier = Modifier,
            startDestination = startDestination ?: MainDestination.route
        )
    }
}