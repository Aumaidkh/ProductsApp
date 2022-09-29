package com.implecode.productsapp.feature_fetch_products.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Will be using the gson parser for parsing the
 * data*/
class GsonParser(
    private val gson: Gson
): JsonParser {

    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json,type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj)
    }

}