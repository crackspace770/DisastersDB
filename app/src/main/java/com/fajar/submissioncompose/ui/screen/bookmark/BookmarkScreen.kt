package com.fajar.submissioncompose.ui.screen.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fajar.submissioncompose.R
import com.fajar.submissioncompose.di.Injection
import com.fajar.submissioncompose.model.Disaster
import com.fajar.submissioncompose.ui.ViewModelFactory
import com.fajar.submissioncompose.ui.common.UiState
import com.fajar.submissioncompose.ui.component.DisasterItem


@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    when (uiState) {
        is UiState.Loading -> {
            viewModel.loadBookmarkedDisasters()
        }
        is UiState.Success -> {
            BookmarkContent(
                (uiState as UiState.Success<BookmarkState>).data
            )
        }
        is UiState.Error -> {
            // Error state UI if needed
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkContent(
    state: BookmarkState,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.bookmark),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    )
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
    ) {
        items(state.bookmarkedDisaster, key = { it.id }) { item ->
            DisasterItem(
                name = item.name,
                date = item.date,
                image = item.image
            )
        }
    }
}