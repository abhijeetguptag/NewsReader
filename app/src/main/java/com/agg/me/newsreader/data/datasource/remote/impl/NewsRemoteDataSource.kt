package com.agg.me.newsreader.data.datasource.remote.impl

import com.agg.me.newsreader.BuildConfig
import com.agg.me.newsreader.data.datasource.remote.NewsRemoteDataSource
import com.agg.me.newsreader.data.model.News
import com.agg.me.newsreader.data.remoteAPI.APIService
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor (
    private val apiService: APIService
) : NewsRemoteDataSource {

    override suspend fun getTopHeadlines(
        country: String,
        category: String,
        page: Int
    ): Response<News> {
        return apiService.getTopHeadlines(country, category, page, apiKey = BuildConfig.API_KEY)
    }
}