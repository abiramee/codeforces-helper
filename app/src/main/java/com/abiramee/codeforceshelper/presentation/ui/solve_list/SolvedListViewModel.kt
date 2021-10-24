package com.abiramee.codeforceshelper.presentation.ui.solve_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abiramee.codeforceshelper.common.Resource
import com.abiramee.codeforceshelper.domain.model.Status
import com.abiramee.codeforceshelper.domain.use_cases.GetStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SolvedListViewModel @Inject constructor(private val useCase: GetStatusUseCase): ViewModel() {

    private val _state = mutableStateOf(SolvedListState());
    val state: State<SolvedListState> = _state

    init {
        getStatus();
    }

    private fun getStatus() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = SolvedListState(isLoading = true)
                is Resource.Success -> _state.value = SolvedListState(data = result.data)
                is Resource.Error -> _state.value = SolvedListState(error = result.message ?: "An unexpected error occurred!")
            }
        }.launchIn(viewModelScope);
    }
}