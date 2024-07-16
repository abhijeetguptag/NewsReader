package com.agg.me.newsreader.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.agg.me.newsreader.data.model.Article
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article): Long


    @Query("SELECT * FROM articleTable")
    fun getFavArticles(): List<Article>

    @Query("SELECT articleTitle FROM articleTable")
    fun getArticleTitles(): List<String>

    @Delete
    suspend fun deleteArticle(article: Article)
}