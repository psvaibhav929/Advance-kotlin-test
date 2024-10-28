package com.example.features.constants

import com.example.features.constants.ScreenConstants.CRICKET_MATCH_DETAILS_SCREEN
import com.example.features.constants.ScreenConstants.CRICKET_MATCh_LIST_SCREEN

sealed class Screen(val route: String) {
    object CricketMatchListScreen : Screen(CRICKET_MATCh_LIST_SCREEN)
    object CricketMatchDetailScreen : Screen(CRICKET_MATCH_DETAILS_SCREEN)
}