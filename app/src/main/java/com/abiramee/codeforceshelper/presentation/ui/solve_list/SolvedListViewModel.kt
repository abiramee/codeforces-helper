package com.abiramee.codeforceshelper.presentation.ui.solve_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abiramee.codeforceshelper.common.DataStorePreference
import com.abiramee.codeforceshelper.common.Resource
import com.abiramee.codeforceshelper.domain.use_case.GetStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolvedListViewModel @Inject constructor(private val useCase: GetStatusUseCase,
                                              private val dataStorePreference: DataStorePreference
): ViewModel() {

    private val _state = mutableStateOf(SolvedListState());
    val state: State<SolvedListState> = _state

    init {

        viewModelScope.launch {
            getStatus()
        }
    }

    private suspend fun getStatus() {
        useCase(dataStorePreference.readString(DataStorePreference.USER_NAME)).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = SolvedListState(isLoading = true)
                is Resource.Success -> {
                    _state.value = SolvedListState(data = result.data)
                    dataStorePreference.save(DataStorePreference.MONTHLY_COUNT, result.data!!.monthlyCount)
                    dataStorePreference.save(DataStorePreference.DAILY_COUNT, result.data.dailyCount)
                }
                is Resource.Error -> _state.value = SolvedListState(error = result.message ?: "An unexpected error occurred!")
            }
        }.launchIn(viewModelScope)
    }
}