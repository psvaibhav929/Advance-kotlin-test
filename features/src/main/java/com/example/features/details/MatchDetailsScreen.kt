package com.example.features.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.features.details.viewmodel.CricketMatchDetailsViewModel
import com.example.features.details.viewstate.MatchDetailsViewState


@Composable
fun MatchDetailsScreen(
    viewModel: CricketMatchDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.matchDetailsState.collectAsState(initial = MatchDetailsViewState.Idle)

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        when (state) {
            is MatchDetailsViewState.Success -> {
                val data = state as MatchDetailsViewState.Success
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MatchDetailsRow(
                            "Hits Today", data.matchDetails.info.hitsToday.toString()
                        )
                        MatchDetailsRow(
                            "Hits Used", data.matchDetails.info.hitsUsed.toString()
                        )
                        
                    }


                }
            }

            is MatchDetailsViewState.Error -> {
                val errorState = state as MatchDetailsViewState.Error
                Text(
                    text = errorState.message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            is MatchDetailsViewState.Loading, MatchDetailsViewState.Idle -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }
    }
}