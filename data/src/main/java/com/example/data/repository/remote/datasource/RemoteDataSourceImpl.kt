package com.example.data.repository.remote.datasource

import com.example.data.constant.Constants
import com.example.data.mapper.CricketMatchMapper
import com.example.data.mapper.MatchDetailsMapper
import com.example.data.network.SafeApiCall
import com.example.data.services.CricketService
import com.example.domain.model.CricketMatch
import com.example.domain.model.MatchDetails
import com.example.domain.result.ApiResult
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: CricketService,
    private val cricketMatchMapper: CricketMatchMapper,
    private val matchDetailsMapper: MatchDetailsMapper
) : RemoteDataSource {
    override suspend fun getCricketMatchList(): ApiResult<CricketMatch> {
        return SafeApiCall.call(
            { service.getAllMatch(Constants.API_KEY) },
            { cricketMatch -> cricketMatchMapper.toDomain(cricketMatch) })
    }

    override suspend fun getCricketMatchDetails(matchName: String): ApiResult<MatchDetails> {
        return SafeApiCall.call(
            { service.getMatchDetails(Constants.API_KEY,matchName) },
            { matchDetailsDto -> matchDetailsMapper.toDomain(matchDetailsDto) })
    }


}