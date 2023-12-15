package com.fajar.submissioncompose.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fajar.submissioncompose.di.Injection
import com.fajar.submissioncompose.ui.ViewModelFactory
import com.fajar.submissioncompose.ui.component.DisasterItem
import com.fajar.submissioncompose.ui.component.SearchBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {

    val groupedDisaster by viewModel.groupedDisaster.collectAsState()
    val query by viewModel.query

    val disasterList = groupedDisaster.values.flatten()

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        LazyColumn(


            contentPadding = PaddingValues(8.dp),

            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ){

            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    )
            }

           items(disasterList, key = {it.id}) { disaster ->
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



