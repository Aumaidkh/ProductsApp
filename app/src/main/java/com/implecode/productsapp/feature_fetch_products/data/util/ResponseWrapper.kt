package com.implecode.productsapp.feature_fetch_products.data.util

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T): ResponseWrapper<T>()
    data class Error(val error: ApiError): ResponseWrapper<Nothing>()
}