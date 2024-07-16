package com.agg.me.newsreader.dependency

import android.content.Context
import androidx.room.Room
import com.agg.me.newsreader.data.datasource.local.NewsDao
import com.agg.me.newsreader.data.datasource.local.NewsDatabase
import com.agg.me.newsreader.data.datasource.remote.NewsRemoteDataSource
import com.agg.me.newsreader.repository.NewsRepository
import com.agg.me.newsreader.repository.impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsDao)
    }
//
    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "newsDatabase")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideArticleDao(articleDb: NewsDatabase): NewsDao {
        return articleDb.getArticleFromDao()
    }
}