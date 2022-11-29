@file:OptIn(ExperimentalAnimationApi::class)

package com.example.stackapp.presentation.userList

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stackapp.R
import com.example.stackapp.model.User
import com.example.stackapp.presentation.components.MessageDialog
import com.example.stackapp.presentation.components.SearchBarUI
import com.example.stackapp.presentation.components.UsersUi

@Composable
fun UserSearchScreen(
    userViewModel: UserSearchViewModel = hiltViewModel(),
    navigateToUserDetailsScreen: (User) -> Unit
) {
    val uiState = userViewModel.uiState
    uiState.events.firstOrNull()?.let {
        HandleEvents(
            event = it,
            handleEvent = userViewModel::handleEvent,
        )
    }
    UserSearchContent(
        searchUser = userViewModel::getData,
        isLoading = uiState.isLoading,
        users = uiState.user,
        navigateToUserDetailsScreen = navigateToUserDetailsScreen
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun UserSearchContent(
    searchUser: (String) -> Unit,
    isLoading: Boolean,
    users: List<User>,
    navigateToUserDetailsScreen: (User) -> Unit
) {
    val (searchText, setSearchText) = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchBarUI(
                searchText = searchText,
                onSearchTextChanged = setSearchText,
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                    searchUser(searchText)
                }
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                )
            )

            if (isLoading) {
                LoadingView()
            } else {
                UsersUi(
                    users = users,
                    onClick = {
                    navigateToUserDetailsScreen(it)
                })
            }
        }

    }

}

@Composable
private fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun HandleEvents(
    event: UserSearchViewModel.UserSearchEvent,
    handleEvent: (UserSearchViewModel.UserSearchEvent.EventType) -> Unit,
) {
    when (event) {
        is UserSearchViewModel.UserSearchEvent.SearchUserError -> {
            MessageDialog(
                primaryAction = { handleEvent(event.type) },
                message = stringResource(id = event.error),
                primaryText = stringResource(id = R.string.ok),
            )
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=840,unit=dp,dpi=480")
@Preview(name = "landscape", device = "spec:shape=Normal,width=840,height=800,unit=dp,dpi=480")
@Preview(name = "tablet", device = "spec:shape=Normal,width=600,height=800,unit=dp,dpi=480")
@Composable
private fun UserSearchScreenPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        UserSearchContent(
            searchUser = {},
            isLoading = true,
            users = emptyList(),
            navigateToUserDetailsScreen = {}
        )
    }
}