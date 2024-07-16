package com.agg.me.newsreader.data.remoteAPI

import com.agg.me.newsreader.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("category")
        category: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String
    ): Response<News>

}