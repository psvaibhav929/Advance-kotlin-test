package com.example.features.list.viewstate

import com.example.domain.model.CricketMatchList


sealed interface CricketListViewState {
    object Idle : CricketListViewState
    object Loading : CricketListViewState
    data class Error(val message: String) : CricketListViewState
    data class Success(val data: List<CricketMatchList>) : CricketListViewState
}