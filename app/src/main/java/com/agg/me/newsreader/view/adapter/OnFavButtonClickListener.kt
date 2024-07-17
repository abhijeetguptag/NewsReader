package com.agg.me.newsreader.view.adapter

import com.agg.me.newsreader.data.model.Article


interface OnFavButtonClickListener {
    fun onFavButtonClicked(isFav : Boolean, article: Article)
}