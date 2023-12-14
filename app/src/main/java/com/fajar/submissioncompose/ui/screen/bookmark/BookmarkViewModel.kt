package com.fajar.submissioncompose.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.submissioncompose.data.DisasterRepository
import com.fajar.submissioncompose.model.Disaster
import com.fajar.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: DisasterRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<BookmarkState>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<BookmarkState>> get() = _uiState



    fun loadBookmarkedDisasters() {
        viewModelScope.launch {
            try {
                val bookmarkedDisasters = repository.getBookmarkedDisasters()
                _uiState.value = UiState.Success(BookmarkState(bookmarkedDisasters))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}