package com.example.stackapp.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stackapp.model.User

@Composable
fun UsersUi(users: List<User>?, onClick: (User) -> Unit) {
    users?.forEach { user ->
        UserRow(user = user) {
            onClick(user)
        }
        Divider()
    }
}


@Composable
fun UserRow(user: User, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }) {
        Text(user.displayName ?: "", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(user.userType ?: "")
        Spacer(modifier = Modifier.height(4.dp))
    }
}