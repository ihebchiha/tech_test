package com.ihebchiha.tech_test.common.extension

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun <T> String.convertJsonToObject(clazz: Class<T>): T {
    val gson = GsonBuilder().create()
    return gson.fromJson(this, clazz)
}