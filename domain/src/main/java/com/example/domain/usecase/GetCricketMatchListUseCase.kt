package com.example.domain.usecase

import com.example.domain.repository.CricketRepository
import javax.inject.Inject

class GetCricketMatchListUseCase @Inject constructor(
    private val cricketRepository: CricketRepository
) {
    suspend operator fun invoke() = cricketRepository.getCricketMatchList()
}