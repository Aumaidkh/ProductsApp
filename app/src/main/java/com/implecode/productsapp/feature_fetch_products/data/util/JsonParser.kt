package com.implecode.productsapp.feature_fetch_products.data.util

import java.lang.reflect.Type

/**
 * Creating the abstraction and hiding the actual implementation of
 * the json parser ensuring we don't break the code if we in future
 * decide to change the parser */
interface JsonParser {

    /**
     * Will be used to construct an abject out of the json string
     * */
    fun <T> fromJson(json: String, type: Type): T?

    /**
     * Will be used to convert an object into a json String*/
    fun <T> toJson(obj: T, type: Type): String?
}