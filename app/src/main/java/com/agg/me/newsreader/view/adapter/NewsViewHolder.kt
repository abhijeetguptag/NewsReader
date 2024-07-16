package com.agg.me.newsreader.view.adapter

import android.graphics.Color
import android.view.View
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.agg.me.newsreader.R
import com.agg.me.newsreader.data.model.Article
import com.agg.me.newsreader.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class NewsViewHolder(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val circularProgressDrawable = CircularProgressDrawable(binding.root.context)

    fun bind(article: Article) {

        circularProgressDrawable.run {
            strokeWidth = 5f
            centerRadius = 50f
            setColorSchemeColors(Color.WHITE)
            this.start()
        }

        binding.apply {
            webView.visibility = View.GONE
            article.articleUrl?.let { url ->
                card.setOnClickListener {
                    //onNewsItemClick?.invoke(article)
                    webView.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = WebViewClient()
                    webView.loadUrl(url)
                }
            }
            favViewThumbnail.setOnClickListener{
                if(article.isFav) {
                    favViewThumbnail.setImageResource(R.drawable.ic_fav_unselected)
                    article.isFav = false
                }
                else{
                    favViewThumbnail.setImageResource(R.drawable.ic_fav_selected)
                    article.isFav = false
                }
            }
            textTitle.text = article.articleTitle
            textDate.text = article.articlePublishedAt!!.split("T")[0]
            textDescription.text = article.articleDescription ?: ""

            setImage(article, imageViewBackground)
            setImage(article, imageViewThumbnail)
        }
    }

    private fun setImage(article: Article, imageView: ImageView) {
        Glide.with(binding.root.context)
            .load(article.articleUrlToImage)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.no_data_svgrepo_com)
            .into(imageView)
    }
}