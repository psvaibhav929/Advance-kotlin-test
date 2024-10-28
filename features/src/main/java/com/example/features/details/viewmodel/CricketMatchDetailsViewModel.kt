package com.example.features.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.di.IoDispatcher
import com.example.domain.result.ApiResult
import com.example.domain.usecase.GetMatchDetailsUseCase
import com.example.features.constants.BundleConstant
import com.example.features.details.intent.CricketDetailsIntent
import com.example.features.details.viewstate.MatchDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CricketMatchDetailsViewModel @Inject constructor(
    private val getMatchDetailsUseCase: GetMatchDetailsUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _matchDetailsState = MutableStateFlow<MatchDetailsViewState>(MatchDetailsViewState.Idle)
    val matchDetailsState: StateFlow<MatchDetailsViewState> = _matchDetailsState.asStateFlow()

    init {
        sendIntent(CricketDetailsIntent.GetCricketDetails)
    }

    private fun sendIntent(intent: CricketDetailsIntent) {
        when (intent) {
            is CricketDetailsIntent.GetCricketDetails -> {
                getMatchDetailsName(
                    savedStateHandle.get<String>(BundleConstant.PARAM_MATCH_NAME) ?: ""
                )
            }
        }
    }

    private fun getMatchDetailsName(dogBreedName: String) {
        viewModelScope.launch(ioDispatcher) {
            _matchDetailsState.emit(MatchDetailsViewState.Loading)
            when (val dogBreedDetails = getMatchDetailsUseCase(dogBreedName)) {
                is ApiResult.Success -> {
                    _matchDetailsState.emit(
                        MatchDetailsViewState.Success(
                            matchInfo = dogBreedDetails.data.info
                        )
                    )
                }

                is ApiResult.Error -> {
                    _matchDetailsState.emit(
                        MatchDetailsViewState.Error(
                            dogBreedDetails.exception.message ?: "An unexpected error"
                        )
                    )
                }
            }
        }

    }
}