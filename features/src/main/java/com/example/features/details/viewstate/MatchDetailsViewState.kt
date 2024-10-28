package com.example.features.details.viewstate

import com.example.domain.model.MatchDetailsInfo

sealed interface MatchDetailsViewState {
    object Idle : MatchDetailsViewState
    object Loading : MatchDetailsViewState
    data class Error(val message: String) : MatchDetailsViewState
    data class Success(val matchInfo: MatchDetailsInfo?) : MatchDetailsViewState
}