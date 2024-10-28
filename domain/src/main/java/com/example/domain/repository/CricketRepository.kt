package com.example.domain.repository


import com.example.domain.model.CricketMatch
import com.example.domain.model.MatchDetails
import com.example.domain.result.ApiResult

interface CricketRepository {
    suspend fun getCricketMatchList(): ApiResult<CricketMatch>
    suspend fun getCricketMatchDetails(matchName: String): ApiResult<MatchDetails>
}