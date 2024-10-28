package com.example.data.di

import com.example.data.repository.remote.datasource.RemoteDataSource
import com.example.data.repository.remote.datasource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindDataModule(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}