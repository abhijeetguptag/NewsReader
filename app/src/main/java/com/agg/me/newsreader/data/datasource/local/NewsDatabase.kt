package com.agg.me.newsreader.data.datasource.local

import androidx.room.Database


import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.agg.me.newsreader.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getArticleFromDao(): NewsDao
}