package com.implecode.productsapp.feature_fetch_products.data.util

import com.implecode.productsapp.feature_fetch_products.domain.util.UiText

/**
 * Wraps up the response from the api
 * will be used to monitor loading state of the
 * api*/
sealed class Result<out T>{
    class Loading<out T>(val data: T? = null) : Result<T>()
    class Error<out T>(val error: UiText?, val data: T?) : Result<T>()
    data class Success<out T>(val value: T): Result<T>()
}
