package com.agg.me.newsreader.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agg.me.newsreader.usecase.GetNewsHeadlinesUseCase

class NewsViewModelFactory(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(getNewsHeadlinesUseCase) as T
    }
}