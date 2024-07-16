package com.agg.me.newsreader.data.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "source")
data class Source(
    @SerializedName("id")
    val sourceID: String?,
    @SerializedName("name")
    val sourceName: String?
) : Parcelable
