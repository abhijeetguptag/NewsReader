package com.agg.me.newsreader.repository

import com.agg.me.newsreader.data.model.Article
import com.agg.me.newsreader.data.model.News
import com.agg.me.newsreader.data.util.NetworkResponse


interface NewsRepository {
    suspend fun getNewsHeadlines(
        country: String,
        category: String,
        page: Int
    ): NetworkResponse<News>

    suspend fun addArticleToDB(article: Article): Boolean

    suspend fun deleteArticleFromDB(article: Article)

    suspend fun getFavArticles(): NetworkResponse<List<Article>>
}