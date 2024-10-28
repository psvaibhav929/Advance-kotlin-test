package com.example.data.di


import com.example.data.repository.CricketRepositoryImpl
import com.example.domain.repository.CricketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindCricketRepository(
        cricketRepositoryImp: CricketRepositoryImpl
    ): CricketRepository
}