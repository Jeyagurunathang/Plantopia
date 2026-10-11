package com.jey.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jey.core.data.repository.HomeRepository
import com.jey.core.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val homeUiState: StateFlow<HomeScreenUiState> = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            homeRepository.getAllPlants()
                .collect {
                    _homeUiState.value = HomeScreenUiState.Success(it)
                }
        }
    }
}

sealed interface HomeScreenUiState {
    object Loading : HomeScreenUiState
    data class Success(val plants: List<Plant>) : HomeScreenUiState
    data class Error(val msg: String?) : HomeScreenUiState
}