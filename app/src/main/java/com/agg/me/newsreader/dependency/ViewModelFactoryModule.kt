package com.agg.me.newsreader.dependency

import com.agg.me.newsreader.usecase.GetNewsHeadlinesUseCase
import com.agg.me.newsreader.view.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(getNewsHeadlinesUseCase)
    }
}