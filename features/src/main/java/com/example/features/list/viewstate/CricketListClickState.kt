package com.example.features.list.viewstate

interface CricketListClickState {
    data class NavigateToDetailScreen(val matchName: String) :
        CricketListClickState
}