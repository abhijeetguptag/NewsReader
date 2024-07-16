package com.agg.me.newsreader.repository.impl

import com.agg.me.newsreader.data.datasource.local.NewsDao
import com.agg.me.newsreader.data.datasource.remote.NewsRemoteDataSource
import com.agg.me.newsreader.data.model.Article
import com.agg.me.newsreader.data.model.News
import com.agg.me.newsreader.data.util.NetworkResponse
import com.agg.me.newsreader.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val database: NewsDao
) : NewsRepository {

    override suspend fun getNewsHeadlines(
        country: String,
        category: String,
        page: Int
    ): NetworkResponse<News> {
        val apiResult = newsRemoteDataSource.getTopHeadlines(country, category, page)
        if (apiResult.isSuccessful) {
            apiResult.body()?.let {
                return NetworkResponse.Success(it)
            }
        }
        return NetworkResponse.Error(
            code = apiResult.code(),
            message = apiResult.errorBody()?.string()
        )
    }

    override suspend fun addArticleToDB(article : Article): Boolean {
        val articleTitles: List<String>
        withContext(Dispatchers.IO) {
            articleTitles = database.getArticleTitles()
        }
        return if (!articleTitles.contains(article.articleTitle)) {
            database.addArticle(article)
            true
        } else {
            false
        }
    }

    override suspend fun deleteArticleFromDB(article: Article) {
        val articleTitles: List<String>
        withContext(Dispatchers.IO) {
             articleTitles = database.getArticleTitles()
        }
        if (!articleTitles.contains(article.articleTitle)) {
            database.deleteArticle(article)
        }
    }

    override suspend fun getFavArticles(): NetworkResponse<List<Article>> {
      return  NetworkResponse.Success(database.getFavArticles())
    }
}