package com.agg.me.newsreader.data.datasource.local

import androidx.room.TypeConverter
import com.agg.me.newsreader.data.model.Article
import com.agg.me.newsreader.data.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeConvertor {

    @TypeConverter
    fun fromListToString(list: List<*>): String {
        val type = object: TypeToken<List<*>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toData(dataString: String?): Source {
        val type: Type = object : TypeToken<Source>() {}.type
        return Gson().fromJson(dataString, type)
    }
}