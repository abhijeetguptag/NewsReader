package com.agg.me.newsreader.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "articleTable")
data class Article(
    @PrimaryKey(autoGenerate = false)
    val articleUUID: String = UUID.randomUUID().toString(),
    @SerializedName("author")
    val articleAuthor: String?,
    @SerializedName("content")
    val articleContent: String?,
    @SerializedName("description")
    val articleDescription: String?,
    @SerializedName("publishedAt")
    val articlePublishedAt: String?,
    @Embedded
    @SerializedName("source")
    val articleSource: Source?,
    @SerializedName("title")
    val articleTitle: String?,
    @SerializedName("url")
    val articleUrl: String?,
    @SerializedName("urlToImage")
    val articleUrlToImage: String?,
    var isFav: Boolean = false
) : Parcelable

