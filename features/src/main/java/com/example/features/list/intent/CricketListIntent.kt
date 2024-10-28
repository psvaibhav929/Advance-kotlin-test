package com.example.features.list.intent

sealed interface CricketListIntent {
    object GetCricketList : CricketListIntent
    data class CricketListItemClicked(
        val matchName: String,
    ) : CricketListIntent
}