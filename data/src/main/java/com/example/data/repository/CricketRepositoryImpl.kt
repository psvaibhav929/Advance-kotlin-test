package com.example.data.repository

import com.example.data.repository.remote.datasource.RemoteDataSource
import com.example.domain.model.CricketMatch
import com.example.domain.model.MatchDetails
import com.example.domain.repository.CricketRepository
import com.example.domain.result.ApiResult
import javax.inject.Inject


class CricketRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CricketRepository {


    override suspend fun getCricketMatchList(): ApiResult<CricketMatch> {
        return remoteDataSource.getCricketMatchList()
    }

    override suspend fun getCricketMatchDetails(
        matchName: String
    ): ApiResult<MatchDetails> {
        return remoteDataSource.getCricketMatchDetails(matchName)
    }
}
