package com.fajar.submissioncompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fajar.submissioncompose.data.DisasterRepository
import com.fajar.submissioncompose.model.Disaster
import com.fajar.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel(private val repository: DisasterRepository): ViewModel() {

    private val _uiState : MutableStateFlow<UiState<List<Disaster>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Disaster>>> get() = _uiState

    private val _disasterList: MutableStateFlow<List<Disaster>> = MutableStateFlow(emptyList())
    val disaster: StateFlow<List<Disaster>> get() = _disasterList

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    private val _groupedDisaster = MutableStateFlow(
        repository.getAllDisaster()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedDisaster: StateFlow<Map<Char, List<Disaster>>> get() = _groupedDisaster



    fun search (newQuery:String){
        _query.value = newQuery
        _groupedDisaster.value = repository.searchDisaster(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

}
