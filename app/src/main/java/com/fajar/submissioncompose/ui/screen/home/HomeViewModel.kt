package com.fajar.submissioncompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.submissioncompose.data.DisasterRepository
import com.fajar.submissioncompose.model.BookmarkedDisaster
import com.fajar.submissioncompose.model.Disaster
import com.fajar.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DisasterRepository): ViewModel() {

    private val _uiState : MutableStateFlow<UiState<List<Disaster>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Disaster>>> get() = _uiState

    private val _disasterList: MutableStateFlow<List<Disaster>> = MutableStateFlow(emptyList())
    val disaster: StateFlow<List<Disaster>> get() = _disasterList


    fun getAllDisaster(){
        viewModelScope.launch {
            repository.getAllDisaster()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())

                }
                .collect{ disaster->
                    _uiState.value = UiState.Success(disaster)
                }
        }
    }

    private val _groupedDisaster = MutableStateFlow(
        repository.getDisaster()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedDisaster: StateFlow<Map<Char, List<Disaster>>> get() = _groupedDisaster

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search (newQuery: String){
        _query.value = newQuery
        _groupedDisaster.value = repository.searchDisaster(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

    fun searchById(id: Long): Disaster {
        return repository.searchDisasterById(id)
    }

}
