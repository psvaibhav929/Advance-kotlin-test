package com.example.data.mapper

import com.example.data.dto.CricketMatchDto
import com.example.data.dto.CricketMatchListDto
import com.example.domain.model.CricketMatch
import com.example.domain.model.CricketMatchList
import javax.inject.Inject

class CricketMatchMapper @Inject constructor() {

    // Function to map CricketMatchDto to CricketMatch
    fun toDomain(dto: CricketMatchDto): CricketMatch {
        return CricketMatch(
            apikey = dto.apikey,
            data = dto.data.map { toCricketMatchList(it) }
        )
    }

    // Function to map CricketMatchListDto to CricketMatchList
    private fun toCricketMatchList(dto: CricketMatchListDto): CricketMatchList {
        return CricketMatchList(
            id = dto.id,
            name = dto.name,
            matchType = dto.matchType,
            status = dto.status,
            venue = dto.venue,
            date = dto.date,
            dateTimeGMT = dto.dateTimeGMT,
            teams = dto.teams,
            seriesId = dto.seriesId,
            fantasyEnabled = dto.fantasyEnabled,
            bbbEnabled = dto.bbbEnabled,
            hasSquad = dto.hasSquad,
            matchStarted = dto.matchStarted,
            matchEnded = dto.matchEnded
        )
    }
}