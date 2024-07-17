package com.agg.me.newsreader.view.adapter

import android.view.View
import com.agg.me.newsreader.data.model.Article


interface OnFavButtonClick {
    fun onFavButtonClick(isFav : Boolean, article: Article)
}