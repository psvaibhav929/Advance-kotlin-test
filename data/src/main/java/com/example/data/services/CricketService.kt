package com.example.data.services

import com.example.data.dto.CricketMatchDto
import com.example.data.dto.MatchDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CricketService {
    @GET("v1/currentMatches/")
    suspend fun getAllMatch(
        @Query("apikey") apikey: String,
        @Query("offset") offset: Int = 0
    ): Response<CricketMatchDto>

    @GET("v1/series")
    suspend fun getMatchDetails( @Query("apikey") apikey: String, @Query("search") search: String): Response<MatchDetailsDto>
}
