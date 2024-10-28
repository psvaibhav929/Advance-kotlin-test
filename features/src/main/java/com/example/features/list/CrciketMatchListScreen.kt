package com.example.features.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.features.list.intent.CricketListIntent
import com.example.features.list.viewmodel.CricketListViewModel
import com.example.features.list.viewstate.CricketListClickState
import com.example.features.list.viewstate.CricketListViewState


@Composable
fun CricketMatchListScreen(
    callback: (cricketMatchName: String) -> Unit,
    viewModel: CricketListViewModel = hiltViewModel(),

    ) {
    val state by viewModel.matchListState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val currentState = state) {
            is CricketListViewState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(currentState.data) { index, cricketNatch ->
                        CricketMatchListItem(
                            modifier = Modifier.fillMaxWidth(),
                            dogItemIndex = "${index + 1}",
                            cricketNatch = cricketNatch,
                            onItemClick = {
                                viewModel.sendIntent(
                                    CricketListIntent.CricketListItemClicked(
                                        cricketNatch.name ?: "",

                                        )
                                )
                            }
                        )
                    }
                }
            }

            is CricketListViewState.Error -> {
                val errorMessage = currentState.message
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            is CricketListViewState.Loading, CricketListViewState.Idle -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.cricketListClickState.collect { clickState ->
            if (clickState is CricketListClickState.NavigateToDetailScreen) {
                callback.invoke(clickState.matchName)
            }
        }
    }

}

