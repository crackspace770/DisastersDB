package com.fajar.submissioncompose.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fajar.submissioncompose.DisasterApp
import com.fajar.submissioncompose.R
import com.fajar.submissioncompose.di.Injection
import com.fajar.submissioncompose.model.BookmarkedDisaster
import com.fajar.submissioncompose.model.Disaster
import com.fajar.submissioncompose.ui.ViewModelFactory
import com.fajar.submissioncompose.ui.common.UiState

import com.fajar.submissioncompose.ui.component.DisasterItem
import com.fajar.submissioncompose.ui.component.SearchBar
import com.fajar.submissioncompose.ui.theme.SubmissionComposeTheme
import kotlinx.coroutines.launch
import com.fajar.submissioncompose.model.DisasterData as DisasterData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
    ){

    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllDisaster()
            }
            is UiState.Success -> {
                HomeContent(
                    bookmarkedDisaster = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }

}

@Composable
fun HomeContent(
    bookmarkedDisaster: List<Disaster>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    val groupedSeries by viewModel.groupedDisaster.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        LazyVerticalGrid(

            columns = GridCells.Adaptive(320.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ){

            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    )
            }

           items(DisasterData.disaster, key = {it.id}) { disaster ->
               DisasterItem(
                //   disasterId = disaster.id.toString(),
                   name = disaster.name,
                   date = disaster.date,
                   image = disaster.image,
                   modifier = Modifier.clickable {
                       navigateToDetail(disaster.id)
                   },

               )

           }

        }


    }

}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}

