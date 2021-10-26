package com.abiramee.codeforceshelper.presentation.ui.have_user_name

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.abiramee.codeforceshelper.common.DataStorePreference
import com.abiramee.codeforceshelper.common.Event
import com.abiramee.codeforceshelper.common.Resource
import com.abiramee.codeforceshelper.domain.use_case.CheckUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import android.appwidget.AppWidgetManager

import android.content.Intent
import com.abiramee.codeforceshelper.presentation.ui.AppWidget


@HiltViewModel
class HaveUserNameViewModel @Inject constructor(
    private val useCase: CheckUserUseCase,
    private val dataStorePreference: DataStorePreference
) : ViewModel() {

    private val _state = mutableStateOf(HaveUserNameState());
    val state: State<HaveUserNameState> = _state

    private val _checkUserState = MutableLiveData<Event<Int>>()
    val checkUserState : LiveData<Event<Int>>
        get() = _checkUserState

    fun getStatus(username: String) {
        useCase(username).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = HaveUserNameState(isLoading = true)
                is Resource.Success -> {
                    _state.value = HaveUserNameState(isLoading = false, data = result.data!!)
                    if(result.data.available) {
                        dataStorePreference.save(DataStorePreference.USER_NAME, username)
                        _checkUserState.value = Event(1);
                    } else {
                        _checkUserState.value = Event(2);
                    }
                }
                is Resource.Error -> _state.value =
                    HaveUserNameState(error = result.message ?: "An unexpected error occurred!", isLoading = false)
            }
        }.launchIn(viewModelScope);
    }
}