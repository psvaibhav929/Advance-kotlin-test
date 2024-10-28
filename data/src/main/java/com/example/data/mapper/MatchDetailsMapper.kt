package com.example.data.mapper

import com.example.data.dto.MatchDetailsDataDto
import com.example.data.dto.MatchDetailsDto
import com.example.data.dto.MatchDetailsInfoDto
import com.example.domain.model.MatchDetails
import com.example.domain.model.MatchDetailsData
import com.example.domain.model.MatchDetailsInfo
import javax.inject.Inject

class MatchDetailsMapper @Inject constructor() {

    // Convert MatchDetailsDto to MatchDetails
    fun toDomain(dto: MatchDetailsDto): MatchDetails {
        return MatchDetails(
            apikey = dto.apikey,
            data = dto.data.map { toMatchDetailsData(it) } as ArrayList<MatchDetailsData>,
            status = dto.status,
            info = dto.info?.let { toMatchDetailsInfo(it) }
        )
    }

    // Convert MatchDetailsDataDto to MatchDetailsData
    private fun toMatchDetailsData(dataDto: MatchDetailsDataDto): MatchDetailsData {
        return MatchDetailsData(
            id = dataDto.id,
            name = dataDto.name,
            shortName = dataDto.shortName,
            startDate = dataDto.startDate,
            endDate = dataDto.endDate,
            odi = dataDto.odi,
            t20 = dataDto.t20,
            test = dataDto.test,
            squads = dataDto.squads,
            matches = dataDto.matches
        )
    }

    // Convert MatchDetailsInfoDto to MatchDetailsInfo
    private fun toMatchDetailsInfo(infoDto: MatchDetailsInfoDto): MatchDetailsInfo {
        return MatchDetailsInfo(
            hitsToday = infoDto.hitsToday,
            hitsUsed = infoDto.hitsUsed,
            hitsLimit = infoDto.hitsLimit,
            credits = infoDto.credits,
            server = infoDto.server,
            offsetRows = infoDto.offsetRows,
            totalRows = infoDto.totalRows,
            queryTime = infoDto.queryTime,
            s = infoDto.s,
            cache = infoDto.cache
        )
    }

}
