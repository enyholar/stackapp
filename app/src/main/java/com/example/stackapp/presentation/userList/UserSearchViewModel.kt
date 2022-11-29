package com.example.stackapp.presentation.userList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.CoroutinesDispatcherProvider
import com.example.domain.usecase.SearchUserUseCase
import com.example.stackapp.R
import com.example.stackapp.model.User
import com.example.stackapp.presentation.translateExceptionToStringId
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


    fun getData(text: String) {
        if (text.isNotEmpty()) {
            viewModelScope.launch(dispatcherProvider.io()) {
                uiState = uiState.copy(isLoading = true)
                uiState = try {
                    val search = searchUserUseCase.invoke(
                        page = "stackoverflow",
                        query = text,
                        sort = "reputation",
                        orderBy = "desc"
                    )
                    if (search.items.isNotEmpty()){
                        uiState.copy(user = search.items,isLoading = false)
                    }else{
                        val events = uiState.events +
                            UserSearchEvent.SearchUserError(error = R.string.error_no_user_found)
                        uiState.copy(events = events,isLoading = false)
                    }
                } catch (ex: Exception) {
                    val error = translateExceptionToStringId(ex)
                    val events = uiState.events +
                        UserSearchEvent.SearchUserError(error = error)
                    uiState.copy(events = events,isLoading = false)
                }
            }
        }
    }

    fun handleEvent(type: UserSearchEvent.EventType) {
        val events = uiState.events.filterNot { it.type == type }
        uiState = uiState.copy(events = events)
    }

    data class UiState(
        val user: List<User> = emptyList(),
        val isLoading: Boolean = false,
        val events: List<UserSearchEvent> = emptyList(),
    )

    sealed class UserSearchEvent {
        enum class EventType {
            ERROR,
            NAVIGATE
        }

        abstract val type: EventType

        data class SearchUserError(
            override val type: EventType = EventType.ERROR,
            val error: Int,
        ) : UserSearchEvent()
    }

}