package com.example.features.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.di.IoDispatcher
import com.example.domain.result.ApiResult
import com.example.domain.usecase.GetCricketMatchListUseCase
import com.example.features.list.intent.CricketListIntent

import com.example.features.list.viewstate.CricketListClickState
import com.example.features.list.viewstate.CricketListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CricketListViewModel @Inject constructor(
    private val getCricketMatchListUseCase: GetCricketMatchListUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {


    private val _cricketListState = MutableStateFlow<CricketListViewState>(CricketListViewState.Idle)
    val matchListState: StateFlow<CricketListViewState>
        get() = _cricketListState.asStateFlow()

    private val _cricketListClickState = MutableSharedFlow<CricketListClickState>()
    val cricketListClickState: SharedFlow<CricketListClickState>
        get() = _cricketListClickState

    init {
        sendIntent(CricketListIntent.GetCricketList)
    }


    fun sendIntent(intent: CricketListIntent) {
        when (intent) {
            is CricketListIntent.GetCricketList -> getCricketList()
            is CricketListIntent.CricketListItemClicked -> navigateDetailsScreen(intent)
        }
    }

    private fun navigateDetailsScreen(intent: CricketListIntent.CricketListItemClicked) {
        viewModelScope.launch {
            _cricketListClickState.emit(
                CricketListClickState.NavigateToDetailScreen(intent.matchName)
            )
        }
    }

     private fun getCricketList() {
        viewModelScope.launch(ioDispatcher) {
            when (val cricketList = getCricketMatchListUseCase()) {
                is ApiResult.Success -> {
                    _cricketListState.emit(
                        CricketListViewState.Success(
                            cricketList.data.data
                        )
                    )
                }

                is ApiResult.Error -> {
                    val errorMessage = cricketList.exception.message ?: "An unexpected error"
                    _cricketListState.emit(
                        CricketListViewState.Error(
                            errorMessage
                        )
                    )
                }
            }
        }
    }

}