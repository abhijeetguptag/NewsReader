package com.agg.me.newsreader.view.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agg.me.newsreader.data.model.Article
import com.agg.me.newsreader.data.model.News
import com.agg.me.newsreader.data.util.NetworkResponse
import com.agg.me.newsreader.usecase.GetNewsHeadlinesUseCase
import com.agg.me.newsreader.util.DeviceUtils
import com.agg.me.newsreader.util.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModel() {

    private val headlines: MutableLiveData<NetworkResponse<News>> = MutableLiveData()
    val headlinesData: LiveData<NetworkResponse<News>>
        get() = headlines

    private val favArticles: MutableLiveData<NetworkResponse<List<Article>>> = MutableLiveData()
    val favArticlesData: LiveData<NetworkResponse<List<Article>>>
        get() = favArticles

    fun getNewsHeadLines(
        context: Context? = null,
        country: String = DeviceUtils.getLocaleCountryName(),
        category: String,
        page: Int
    ) = viewModelScope.launch(Dispatchers.IO) {

        headlines.postValue(NetworkResponse.Loading())
        try {
            if (NetworkStatus.isNetworkAvailable(context)) {
                val apiResult = getNewsHeadlinesUseCase.execute(country, category, page)
                headlines.postValue(apiResult)
            } else {
                headlines.postValue(NetworkResponse.Error(message = "Internet is not available!"))
            }
        } catch (e: Exception) {
            headlines.postValue(NetworkResponse.Error(message = e.message.toString()))
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        getNewsHeadlinesUseCase.saveNews(article)
    }

    fun unSaveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        getNewsHeadlinesUseCase.unSaveNews(article)
    }

    fun getFavArticles() = viewModelScope.launch(Dispatchers.IO) {
        favArticles.postValue(NetworkResponse.Loading())

        val apiResult = getNewsHeadlinesUseCase.getFavNews()
        favArticles.postValue(apiResult)
    }
}