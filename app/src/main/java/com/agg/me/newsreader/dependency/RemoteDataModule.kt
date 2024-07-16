package com.agg.me.newsreader.dependency

import com.agg.me.newsreader.data.datasource.remote.NewsRemoteDataSource
import com.agg.me.newsreader.data.datasource.remote.impl.NewsRemoteDataSourceImpl
import com.agg.me.newsreader.data.remoteAPI.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: APIService
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}