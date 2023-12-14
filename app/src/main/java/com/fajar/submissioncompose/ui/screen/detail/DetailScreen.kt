package com.fajar.submissioncompose.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fajar.submissioncompose.di.Injection
import com.fajar.submissioncompose.ui.ViewModelFactory
import com.fajar.submissioncompose.ui.common.UiState
import com.fajar.submissioncompose.ui.component.DisasterDetail

@Composable
fun DetailScreen(
    disasterId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
   // navigateToBookmark: () -> Unit
){

     viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getDisasterById(disasterId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DisasterDetail(
                    data.name,
                    data.date,
                    data.location,
                    data.description,
                    data.deathToll,
                    data.image,
                    onBackClick = navigateBack,
                    bookmarkStatus = viewModel.isBookmarked(disasterId),
                    updateBookmarkStatus = {
                        viewModel.addToBookmark(data)
                    }
                )
            }
            is UiState.Error -> {}
            else -> {}
        }
    }
}