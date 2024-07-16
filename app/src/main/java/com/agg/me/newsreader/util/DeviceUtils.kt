package com.agg.me.newsreader.util

import android.os.Build
import android.os.Build.VERSION_CODES
import java.util.Locale


object DeviceUtils {
    fun getLocaleCountryName(): String {
        return Locale.getDefault().country
    }

    fun getDeviceLocale(): String {
        return Locale.getDefault().displayLanguage
    }

    fun getAndroidVersionName(): String {
        val fields = VERSION_CODES::class.java.fields
        var codeName = "UNKNOWN"
        for (field in fields) {
            try {
                if (field.getInt(VERSION_CODES::class.java) == Build.VERSION.SDK_INT) {
                    codeName = field.name
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return codeName
    }
}