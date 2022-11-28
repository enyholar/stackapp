package com.example.stackapp.presentation.userList

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.CoroutinesDispatcherProvider
import com.example.domain.usecase.SearchUserUseCase
import com.example.stackapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import java.lang.Exception

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set


    fun getData(text:String) {
        if(text.isNotEmpty()){
            viewModelScope.launch(dispatcherProvider.io()) {
                uiState = uiState.copy(isLoading = true)
                try {
                    val search = searchUserUseCase.invoke(
                        page = "stackoverflow",
                        query = text,
                        sort = "reputation",
                        orderBy = "desc"
                    )
                    val se = search.items
                    uiState = uiState.copy(user = search.items)
                } catch (ex: Exception) {
                    Log.e("errorGideon", ex.message.toString())
                }
                finally {
                    uiState = uiState.copy(isLoading = false)
                }
            }
        }
    }

    data class UiState(
        val user: List<User> = emptyList(),
        val isLoading:Boolean = false
    )

}