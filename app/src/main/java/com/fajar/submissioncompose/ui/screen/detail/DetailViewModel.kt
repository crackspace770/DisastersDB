package com.fajar.submissioncompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.submissioncompose.data.DisasterRepository
import com.fajar.submissioncompose.model.Disaster
import com.fajar.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DisasterRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Disaster>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Disaster>>
        get() = _uiState

    private var currentDisaster: Disaster? = null

    fun getDisasterById(disasterId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDisasterById(disasterId))
        }
    }

    fun addToBookmark(disaster: Disaster) {
        viewModelScope.launch {
            repository.toggleBookmark(disaster.id)
        }
    }

    fun isBookmarked(disasterId: Long): Boolean {
        return repository.isBookmarked(disasterId)
    }


}