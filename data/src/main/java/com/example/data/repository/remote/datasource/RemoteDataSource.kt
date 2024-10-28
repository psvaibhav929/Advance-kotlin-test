package com.example.data.repository.remote.datasource

import com.example.domain.model.CricketMatch
import com.example.domain.model.MatchDetails
import com.example.domain.result.ApiResult

interface RemoteDataSource {
    suspend fun getCricketMatchList(): ApiResult<CricketMatch>
    suspend fun getCricketMatchDetails(matchName: String): ApiResult<MatchDetails>
}