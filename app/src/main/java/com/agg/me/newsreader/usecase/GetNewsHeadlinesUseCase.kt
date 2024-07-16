package com.agg.me.newsreader.usecase

import com.agg.me.newsreader.data.model.Article
import com.agg.me.newsreader.data.model.News
import com.agg.me.newsreader.data.util.NetworkResponse
import com.agg.me.newsreader.repository.NewsRepository
import javax.inject.Inject

class GetNewsHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, category: String, page: Int): NetworkResponse<News> {
        return newsRepository.getNewsHeadlines(country, category, page)
    }

    suspend fun saveNews(article: Article){
        newsRepository.addArticleToDB(article)
    }

    suspend fun unSaveNews(article: Article){
        newsRepository.deleteArticleFromDB(article)
    }

    suspend fun getFavNews(): NetworkResponse<List<Article>>{
       return newsRepository.getFavArticles()
    }
}