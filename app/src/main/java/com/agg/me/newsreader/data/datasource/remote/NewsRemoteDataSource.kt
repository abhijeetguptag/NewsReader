package com.agg.me.newsreader.data.datasource.remote

import com.agg.me.newsreader.data.model.News
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, category: String, page: Int): Response<News>
}