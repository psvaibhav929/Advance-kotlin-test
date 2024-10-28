package com.example.domain.usecase

import com.example.domain.repository.CricketRepository
import javax.inject.Inject

class GetMatchDetailsUseCase @Inject constructor(
    private val cricketRepository: CricketRepository
) {
    suspend operator fun invoke(matchName: String) =
        cricketRepository.getCricketMatchDetails(matchName)

}