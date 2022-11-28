package com.example.stackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.stackapp.presentation.navigation.StackNavHost
import com.example.stackapp.presentation.navigation.UserListDestination
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
        val navController = rememberNavController()
        StackNavHost(
            navController = navController,
            modifier = Modifier,
            startDestination = startDestination ?: UserListDestination.route
        )
    }
}